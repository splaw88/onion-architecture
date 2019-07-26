package pl.splaw.onionarchitecture.consoleapplication.state.main.worker;

import pl.splaw.onionarchitecture.consoleapplication.factories.console.ConsoleManager;
import pl.splaw.onionarchitecture.consoleapplication.factories.console.ConsoleManagerFactory;
import pl.splaw.onionarchitecture.consoleapplication.state.ConsoleState;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class WorkerManageState implements ConsoleState {

    private final ConsoleManager consoleManager;
    private ConsoleState nextState;

    public WorkerManageState() {
        this.consoleManager = ConsoleManagerFactory.getInstance();
    }

    @Override
    public void process() throws Exception {
        System.out.println(String.format("What would you like to do regarding workers?:%n 'create' - create new worker%n 'delete' - delete existing one by login%n 'find' - find by login"));
        String input = consoleManager.readLine();
        if (null == input) {
            System.out.println("Invalid input. Use one from above");
        } else {
            switch (input) {
                case "create":
                    nextState = new CreateWorkerState();
                    consoleManager.clear();
                    break;
                case "delete":
                    nextState = new DeleteWorkerState();
                    consoleManager.clear();
                    break;
                case "find":
                    nextState = new FindWorkerState();
                    consoleManager.clear();
                    break;
                default:
                    System.out.println("Invalid input. Use one from above");
                    break;
            }
        }
    }

    @Override
    public ConsoleState nextState() {
        return nextState;
    }

}

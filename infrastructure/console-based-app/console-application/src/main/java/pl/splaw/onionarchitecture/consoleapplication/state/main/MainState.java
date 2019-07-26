package pl.splaw.onionarchitecture.consoleapplication.state.main;

import pl.splaw.onionarchitecture.consoleapplication.state.main.worklog.WorklogManageState;
import pl.splaw.onionarchitecture.consoleapplication.state.main.worker.WorkerManageState;
import pl.splaw.onionarchitecture.consoleapplication.factories.console.ConsoleManager;
import pl.splaw.onionarchitecture.consoleapplication.factories.console.ConsoleManagerFactory;
import pl.splaw.onionarchitecture.consoleapplication.state.ConsoleState;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class MainState implements ConsoleState {

    private final ConsoleManager consoleManager;
    private ConsoleState nextState;

    public MainState() {
        this.consoleManager = ConsoleManagerFactory.getInstance();
    }

    @Override
    public void process() throws Exception {
        System.out.println(String.format("What would you like to do?:%n 'workers' - manage workers%n 'worklogs' - manage worklogs"));
        String input;
        input = consoleManager.readLine();
        if (null == input) {
            System.out.println("Invalid input. Use one from above");
        } else switch (input) {
            case "workers":
                System.out.println("You chose to manage workes");
                nextState = new WorkerManageState();
                consoleManager.clear();
                break;
            case "worklogs":
                System.out.println("You chose to manage worklogs");
                nextState = new WorklogManageState();
                consoleManager.clear();
                break;
            default:
                System.out.println("Invalid input. Use one from above");
                break;
        }

    }

    @Override
    public ConsoleState nextState() {
        return nextState;
    }

}

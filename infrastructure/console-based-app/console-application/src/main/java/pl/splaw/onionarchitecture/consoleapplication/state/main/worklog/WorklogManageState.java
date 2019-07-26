package pl.splaw.onionarchitecture.consoleapplication.state.main.worklog;

import pl.splaw.onionarchitecture.consoleapplication.factories.console.ConsoleManager;
import pl.splaw.onionarchitecture.consoleapplication.factories.console.ConsoleManagerFactory;
import pl.splaw.onionarchitecture.consoleapplication.state.ConsoleState;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class WorklogManageState implements ConsoleState {

    private final ConsoleManager consoleManager;
    private ConsoleState nextState;

    public WorklogManageState() {
        this.consoleManager = ConsoleManagerFactory.getInstance();
    }

    @Override
    public void process() throws Exception {
         System.out.println(String.format("What would you like to do regarding worklog?:%n 'log' - log time%n 'report' - show all loged work of worker by login"));
        String input = consoleManager.readLine();
        if (null == input) {
            System.out.println("Invalid input. Use one from above");
        } else {
            switch (input) {
                case "log":
                    nextState = new LogWorkState();
                    consoleManager.clear();
                    break;
                case "report":
                    nextState = new ReportWorkerWorkLogState();
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

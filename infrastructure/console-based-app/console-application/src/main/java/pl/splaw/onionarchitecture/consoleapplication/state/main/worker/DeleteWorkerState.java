package pl.splaw.onionarchitecture.consoleapplication.state.main.worker;

import pl.splaw.onionarchitecture.applicationservices.exceptions.worker.WorkerDontExistsException;
import pl.splaw.onionarchitecture.applicationservices.services.WorkerServiceI;
import static pl.splaw.onionarchitecture.consoleapplication.util.InputUtils.isEmpty;
import pl.splaw.onionarchitecture.consoleapplication.factories.console.ConsoleManager;
import pl.splaw.onionarchitecture.consoleapplication.factories.console.ConsoleManagerFactory;
import pl.splaw.onionarchitecture.consoleapplication.factories.worker.WorkerServiceFactory;
import pl.splaw.onionarchitecture.consoleapplication.state.ConsoleState;
import pl.splaw.onionarchitecture.consoleapplication.state.main.MainState;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class DeleteWorkerState implements ConsoleState {

    private final ConsoleManager consoleManager;
    private final WorkerServiceI workerService;
    private ConsoleState nextState;

    public DeleteWorkerState() {
        this.consoleManager = ConsoleManagerFactory.getInstance();
        this.workerService = WorkerServiceFactory.getWorkerService();
        this.nextState = new MainState();
    }

    @Override
    public void process() throws Exception {
        String login = "";
        do {
            System.out.println(String.format("Enter login of user to delete"));
            login = consoleManager.readLine();
        } while (isEmpty("login", login));

        try {
            workerService.deleteWorker(login);
            System.out.println(String.format("Worker deleted"));
            System.out.println(String.format("Delete another one? (y/n)"));
            String yesNo = consoleManager.readLine();
            if (!"y".equalsIgnoreCase(yesNo)) {
                nextState = new MainState();
            }
        } catch (WorkerDontExistsException e) {
            System.out.println(String.format("User with login '%s' cant be found! Try again? (y/n)", login));
            String yesNo = consoleManager.readLine();
            if (!"y".equalsIgnoreCase(yesNo)) {
                nextState = new MainState();
            }
        } finally {
            consoleManager.clear();
        }
    }

    @Override
    public ConsoleState nextState() {
        return nextState;
    }

}

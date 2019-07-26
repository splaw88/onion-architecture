package pl.splaw.onionarchitecture.consoleapplication.state.main.worker;

import pl.splaw.onionarchitecture.applicationservices.exceptions.worker.WorkerDontExistsException;
import pl.splaw.onionarchitecture.applicationservices.services.WorkerServiceI;
import static pl.splaw.onionarchitecture.consoleapplication.util.InputUtils.isEmpty;
import pl.splaw.onionarchitecture.consoleapplication.factories.console.ConsoleManager;
import pl.splaw.onionarchitecture.consoleapplication.factories.console.ConsoleManagerFactory;
import pl.splaw.onionarchitecture.consoleapplication.factories.worker.WorkerServiceFactory;
import pl.splaw.onionarchitecture.consoleapplication.state.ConsoleState;
import pl.splaw.onionarchitecture.consoleapplication.state.main.MainState;
import pl.splaw.onionarchitecture.domain.model.Worker;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class FindWorkerState implements ConsoleState {

    private final ConsoleManager consoleManager;
    private final WorkerServiceI workerService;
    private ConsoleState nextState;

    public FindWorkerState() {
        this.consoleManager = ConsoleManagerFactory.getInstance();
        this.workerService = WorkerServiceFactory.getWorkerService();
        this.nextState = new MainState();
    }

    @Override
    public void process() throws Exception {
        String login = "";
        do {
            System.out.println(String.format("Enter login"));
            login = consoleManager.readLine();
        } while (isEmpty("login", login));

        try {
            Worker foundedWorker = workerService.findWorkerByLogin(login);
            System.out.println(String.format("Worker found. Details:%nLogin: %s%nName: %s%nSurname: %s%nEmail: %s%n%n",
                    foundedWorker.getLogin(),
                    foundedWorker.getName(),
                    foundedWorker.getSurname(),
                    foundedWorker.getEmail()));
            System.out.println(String.format("Edit? (y/n)"));
            String yesNo = consoleManager.readLine();
            if ("y".equalsIgnoreCase(yesNo)) {
                nextState = new EditWorkerState(foundedWorker);
            } else {
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

package pl.splaw.onionarchitecture.consoleapplication.state.main.worker;

import pl.splaw.onionarchitecture.applicationservices.exceptions.worker.WorkerExistsException;
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
public class CreateWorkerState implements ConsoleState {

    private final ConsoleManager consoleManager;
    private final WorkerServiceI workerService;
    private ConsoleState nextState;

    public CreateWorkerState() {
        this.consoleManager = ConsoleManagerFactory.getInstance();
        this.workerService = WorkerServiceFactory.getWorkerService();
        nextState = new MainState();
    }

    @Override
    public void process() throws Exception {
        String login;
        do {
            System.out.println(String.format("Enter login"));
            login = consoleManager.readLine();
        } while (isEmpty("login", login));
        String name;
        do {
            System.out.println(String.format("Enter name"));
            name = consoleManager.readLine();
        } while (isEmpty("name", name));
        String surname;
        do {
            System.out.println(String.format("Enter surname"));
            surname = consoleManager.readLine();
        } while (isEmpty("surname", surname));
        String email;
        do {
            System.out.println(String.format("Enter email"));
            email = consoleManager.readLine();
        } while (isEmpty("email", email));

        try {
            workerService.createWorker(new Worker(login, name, surname, email));
            System.out.println(String.format("Worker created!"));
            consoleManager.readLine();
        } catch (WorkerExistsException e) {
            System.out.println(String.format("Worker already exist! Try to again? (y/n)"));
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

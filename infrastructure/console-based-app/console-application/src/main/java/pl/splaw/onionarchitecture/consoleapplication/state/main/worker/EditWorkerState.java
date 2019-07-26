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
public class EditWorkerState implements ConsoleState {

    private final ConsoleManager consoleManager;
    private final WorkerServiceI workerService;
    private ConsoleState nextState;
    private final String oldLogin;
    private final Worker workerToEdit;

    public EditWorkerState(Worker workerToEdit) {
        this.consoleManager = ConsoleManagerFactory.getInstance();
        this.workerService = WorkerServiceFactory.getWorkerService();
        nextState = new MainState();
        this.workerToEdit = workerToEdit;
        this.oldLogin = workerToEdit.getLogin();
    }

    @Override
    public void process() throws Exception {
        String name;
        do {
            System.out.println(String.format("Change '%s' name to:", workerToEdit.getName()));
            name = consoleManager.readLine();
        } while (isEmpty("name", name));
        String surname;
        do {
            System.out.println(String.format("Change '%s' surname to:", workerToEdit.getSurname()));
            surname = consoleManager.readLine();
        } while (isEmpty("surname", surname));
        String email;
        do {
            System.out.println(String.format("Change '%s' email to:", workerToEdit.getEmail()));
            email = consoleManager.readLine();
        } while (isEmpty("email", email));
        try {
            workerService.editWorker(oldLogin, new Worker(null, name, surname, email));
            System.out.println(String.format("Worker changed!"));
            consoleManager.readLine();
            nextState = new MainState();
        } catch (WorkerDontExistsException e) {
            System.out.println(String.format("Worker dont exist! Try to search again? (y/n)"));
            String yesNo = consoleManager.readLine();
            if ("y".equalsIgnoreCase(yesNo)) {
                nextState = new FindWorkerState();
            } else {
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

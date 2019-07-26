package pl.splaw.onionarchitecture.consoleapplication.state.main.worklog;

import de.vandermeer.asciitable.AsciiTable;
import java.io.IOException;
import java.util.List;
import pl.splaw.onionarchitecture.applicationservices.exceptions.worker.WorkerDontExistsException;
import pl.splaw.onionarchitecture.applicationservices.services.WorkLogServiceI;
import pl.splaw.onionarchitecture.applicationservices.services.WorkerServiceI;
import static pl.splaw.onionarchitecture.consoleapplication.util.InputUtils.isEmpty;
import pl.splaw.onionarchitecture.consoleapplication.factories.console.ConsoleManager;
import pl.splaw.onionarchitecture.consoleapplication.factories.console.ConsoleManagerFactory;
import pl.splaw.onionarchitecture.consoleapplication.factories.worker.WorkerServiceFactory;
import pl.splaw.onionarchitecture.consoleapplication.factories.worklog.WorklogServiceFactory;
import pl.splaw.onionarchitecture.consoleapplication.state.ConsoleState;
import pl.splaw.onionarchitecture.consoleapplication.state.main.MainState;
import pl.splaw.onionarchitecture.domain.model.WorkLog;
import pl.splaw.onionarchitecture.domain.model.Worker;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class ReportWorkerWorkLogState implements ConsoleState {

    private final ConsoleManager consoleManager;
    private ConsoleState nextState;
    private final WorkerServiceI workerService;
    private final WorkLogServiceI workLogServiceI;

    public ReportWorkerWorkLogState() {
        this.consoleManager = ConsoleManagerFactory.getInstance();
        this.workerService = WorkerServiceFactory.getWorkerService();
        this.workLogServiceI = WorklogServiceFactory.getWorkLogService();
    }

    @Override
    public void process() throws Exception {
        Worker worker = searchWorker();

        List<WorkLog> reportData = workLogServiceI.reportWorkerWork(worker);

        if (reportData == null || reportData.isEmpty()) {
            System.out.println("No worklog to how");
            System.out.println("Search again? (y/n)");
            String yesNo = consoleManager.readLine();
            if (!"y".equalsIgnoreCase(yesNo)) {
                nextState = new MainState();
            }
        } else {
            printResultTable(reportData);
            System.out.println("Search again? (y/n)");
            String yesNo = consoleManager.readLine();
            if (!"y".equalsIgnoreCase(yesNo)) {
                nextState = new MainState();
            }
        }
    }

    @Override
    public ConsoleState nextState() {
        return nextState;
    }

    private Worker searchWorker() throws IOException {
        String login = "";
        Worker foundedWorker = null;
        do {
            System.out.println(String.format("Enter login"));
            login = consoleManager.readLine();
        } while (isEmpty("login", login));

        try {
            foundedWorker = workerService.findWorkerByLogin(login);
            System.out.println(String.format("Worker found. Details:%nLogin: %s%nName: %s%nSurname: %s%nEmail: %s%n%n",
                    foundedWorker.getLogin(),
                    foundedWorker.getName(),
                    foundedWorker.getSurname(),
                    foundedWorker.getEmail()));
        } catch (WorkerDontExistsException e) {
            System.out.println(String.format("User with login '%s' cant be found! Try again? (y/n)", login));
            String yesNo = consoleManager.readLine();
            if (!"y".equalsIgnoreCase(yesNo)) {
                nextState = new MainState();
            }
        } finally {
            consoleManager.clear();
            return foundedWorker;
        }
    }

    private void printResultTable(List<WorkLog> reportData) {
        AsciiTable at = new AsciiTable();

        at.addRule();
        at.addRow("ID", "START DATE", "TIME SPEND (SECONDS)", "DESCRIPTION");

        reportData.forEach((worklog) -> {
            at.addRule();
            at.addRow(worklog.getWorkLogId(), worklog.getStartDate(), worklog.getTimeSpentInSeconds(), worklog.getDescription());
        });
        at.addRule();

        System.out.println(at.render());
    }

}

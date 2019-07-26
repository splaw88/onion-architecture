package pl.splaw.onionarchitecture.consoleapplication.state.main.worklog;

import java.io.IOException;
import static java.lang.Integer.valueOf;
import java.math.BigInteger;
import java.time.LocalDate;
import static java.time.LocalDate.parse;
import pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException;
import pl.splaw.onionarchitecture.applicationservices.services.WorkLogServiceI;
import pl.splaw.onionarchitecture.applicationservices.services.WorkerServiceI;
import static pl.splaw.onionarchitecture.consoleapplication.util.InputUtils.isDateFormatCorrect;
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
public class LogWorkState implements ConsoleState {

    private final ConsoleManager consoleManager;
    private ConsoleState nextState;
    private final WorkerServiceI workerService;
    private final WorkLogServiceI workLogServiceI;

    public LogWorkState() {
        this.consoleManager = ConsoleManagerFactory.getInstance();
        this.workerService = WorkerServiceFactory.getWorkerService();
        this.workLogServiceI = WorklogServiceFactory.getWorkLogService();
    }

    @Override
    public void process() throws Exception {
        Worker worker = searchWorker();
        if (worker != null) {

            WorkLog workLog = logWork(worker);
            workLogServiceI.logWork(workLog);
            System.out.println("Logged! Log another? (y/n)");
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
        } catch (BaseException e) {
            System.out.println(String.format("User with login '%s' cant be found! Try again? (y/n)", login));
            String yesNo = consoleManager.readLine();
            if (!"y".equalsIgnoreCase(yesNo)) {
                nextState = new MainState();
            }
        } finally {
            consoleManager.clear();
        }

        return foundedWorker;
    }

    private WorkLog logWork(Worker worker) throws IOException {
        String startDate;
        do {
            System.out.println(String.format("Enter start date in format YYYY-MM-DD"));
            startDate = consoleManager.readLine();
        } while (isEmpty("start date", startDate) || isDateFormatCorrect(startDate));

        LocalDate date = parse(startDate);

        Integer timeInSeconds;
        do {
            System.out.println(String.format("Enter time spend in seconds"));
            timeInSeconds = valueOf(consoleManager.readLine());
        } while (isEmpty("timeInSeconds", timeInSeconds));
        String description;
        do {
            System.out.println(String.format("Enter description"));
            description = consoleManager.readLine();
        } while (isEmpty("description", description));

        return new WorkLog(null, date, BigInteger.valueOf(timeInSeconds), worker, description);
    }
}

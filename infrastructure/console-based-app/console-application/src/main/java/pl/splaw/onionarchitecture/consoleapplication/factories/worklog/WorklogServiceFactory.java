package pl.splaw.onionarchitecture.consoleapplication.factories.worklog;

import pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkLogService;
import pl.splaw.onionarchitecture.applicationservices.services.WorkLogServiceI;
import pl.splaw.onionarchitecture.inmemory.worker.WorkerRepositoryFactory;
import pl.splaw.onionarchitecture.inmemory.worklog.WorklogRepositoryFactory;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class WorklogServiceFactory {

    private static WorkLogServiceI workLogServiceI;

    public static WorkLogServiceI getWorkLogService() {
        if (workLogServiceI == null) {
            workLogServiceI = new WorkLogService(WorklogRepositoryFactory.getWorkLogRepository(), WorkerRepositoryFactory.getWorkerRepository());
        }
        return workLogServiceI;
    }
}

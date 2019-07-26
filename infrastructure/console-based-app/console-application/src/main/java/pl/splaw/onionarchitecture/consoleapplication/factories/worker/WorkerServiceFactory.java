package pl.splaw.onionarchitecture.consoleapplication.factories.worker;

import pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkerService;
import pl.splaw.onionarchitecture.applicationservices.services.WorkerServiceI;
import pl.splaw.onionarchitecture.inmemory.worker.WorkerRepositoryFactory;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class WorkerServiceFactory {

    private static WorkerServiceI workerServiceInstance;

    public static WorkerServiceI getWorkerService() {
        if (workerServiceInstance == null) {
            workerServiceInstance = new WorkerService(WorkerRepositoryFactory.getWorkerRepository());
        }
        return workerServiceInstance;
    }
}

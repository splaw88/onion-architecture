package pl.splaw.onionarchitecture.inmemory.worker;

import pl.splaw.onionarchitecture.inmemory.worker.WorkerInMemoryRepository;
import pl.splaw.onionarchitecture.repositoryinterface.repository.WorkerRepositoryI;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public final class WorkerRepositoryFactory {

    private static WorkerRepositoryI workerRepositoryInstance;

    public static WorkerRepositoryI getWorkerRepository() {
        if (workerRepositoryInstance == null) {
            workerRepositoryInstance = new WorkerInMemoryRepository();
        }
        return workerRepositoryInstance;
    }
}

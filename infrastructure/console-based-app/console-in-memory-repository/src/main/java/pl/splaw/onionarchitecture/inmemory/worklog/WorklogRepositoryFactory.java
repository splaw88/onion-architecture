package pl.splaw.onionarchitecture.inmemory.worklog;

import pl.splaw.onionarchitecture.inmemory.worklog.WorklogInMemoryRepository;
import pl.splaw.onionarchitecture.repositoryinterface.repository.WorkLogRepositoryI;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public final class WorklogRepositoryFactory {

    private static WorkLogRepositoryI workLogRepositoryInstance;

    public static WorkLogRepositoryI getWorkLogRepository() {
        if (workLogRepositoryInstance == null) {
            workLogRepositoryInstance = new WorklogInMemoryRepository();
        }
        return workLogRepositoryInstance;
    }
}

package pl.splaw.onionarchitecture.repositoryinterface.repository;

import java.util.List;
import pl.splaw.onionarchitecture.domain.model.WorkLog;
import pl.splaw.onionarchitecture.domain.model.Worker;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public interface WorkLogRepositoryI {

    WorkLog findWorkLogById(Long id);

    List<WorkLog> findWorkLogsByWorker(Worker worker);

    WorkLog createWorkLog(WorkLog workLog);

    WorkLog updateWorkLog(WorkLog workLog);

    WorkLog deleteWorkLog(WorkLog workLog);

}

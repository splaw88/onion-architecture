package pl.splaw.repositoryinterface.repository;


import pl.splaw.domain.model.WorkLog;
import pl.splaw.domain.model.Worker;

import java.util.List;

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

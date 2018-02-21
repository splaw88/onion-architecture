package pl.splaw.onionarchitecture.applicationservices.services;

import java.util.List;
import pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException;
import pl.splaw.onionarchitecture.domain.model.WorkLog;
import pl.splaw.onionarchitecture.domain.model.Worker;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public interface WorkLogServiceI {

    WorkLog logWork(WorkLog workLog) throws BaseException;

    WorkLog editWorkLog(Long id, WorkLog workLog) throws BaseException;

    WorkLog deleteWorkLog(Long workLogId) throws BaseException;

    List<WorkLog> reportWorkerWork(Worker worker);

    WorkLog findWorkLogById(Long id) throws BaseException;

}

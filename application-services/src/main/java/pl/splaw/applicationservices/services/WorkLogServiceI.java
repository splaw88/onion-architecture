package pl.splaw.applicationservices.services;


import pl.splaw.applicationservices.exceptions.BaseException;
import pl.splaw.domain.model.WorkLog;
import pl.splaw.domain.model.Worker;

import java.util.List;

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

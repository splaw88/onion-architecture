package pl.splaw.applicationservices.services;


import pl.splaw.domain.model.WorkLog;
import pl.splaw.domain.model.Worker;

import java.util.List;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public interface WorkLogServiceI {

    WorkLog logWork(WorkLog workLog) throws pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException;

    WorkLog editWorkLog(Long id, WorkLog workLog) throws pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException;

    WorkLog deleteWorkLog(Long workLogId) throws pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException;

    List<WorkLog> reportWorkerWork(Worker worker);

    WorkLog findWorkLogById(Long id) throws pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException;

}

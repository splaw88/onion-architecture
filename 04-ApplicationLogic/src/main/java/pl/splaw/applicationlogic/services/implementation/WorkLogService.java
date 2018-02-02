package pl.splaw.onionarchitecture.applicationlogic.services.implementation;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;
import pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException;
import pl.splaw.onionarchitecture.applicationservices.exceptions.worker.WorkerDontExistsException;
import pl.splaw.onionarchitecture.applicationservices.exceptions.worklog.WorkLogDontExistException;
import pl.splaw.onionarchitecture.applicationservices.exceptions.worklog.WorkLogStartDateException;
import pl.splaw.onionarchitecture.applicationservices.exceptions.worklog.WorkLogTimeSpentException;
import pl.splaw.onionarchitecture.applicationservices.services.WorkLogServiceI;
import pl.splaw.onionarchitecture.domain.model.WorkLog;
import pl.splaw.onionarchitecture.domain.model.Worker;
import pl.splaw.onionarchitecture.repositoryinterface.repository.WorkLogRepositoryI;
import pl.splaw.onionarchitecture.repositoryinterface.repository.WorkerRepositoryI;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class WorkLogService implements WorkLogServiceI {

    private final WorkLogRepositoryI workLogRepository;
    private final WorkerRepositoryI workerRepository;

    @Inject
    public WorkLogService(WorkLogRepositoryI workLogRepository, WorkerRepositoryI workerRepository) {
        this.workLogRepository = workLogRepository;
        this.workerRepository = workerRepository;
    }

    @Override
    public WorkLog logWork(WorkLog workLog) throws BaseException {
        validateWorkLog(workLog);
        return workLogRepository.createWorkLog(workLog);
    }

    @Override
    public WorkLog editWorkLog(Long id, WorkLog workLog) throws BaseException {
        validateEditWorkLog(workLog);
        WorkLog workLogFromBase = workLogRepository.findWorkLogById(id);
        if (workLogFromBase == null) {
            throw new WorkLogDontExistException("Edited work log dont exists");
        }
        return workLogRepository.updateWorkLog(new WorkLog(id, workLog.getStartDate(), workLog.getTimeSpentInSeconds(), workLogFromBase.getAssociatedWorker(), workLog.getDescription()));
    }

    @Override
    public WorkLog deleteWorkLog(Long workLogId) throws BaseException {
        WorkLog workLog = workLogRepository.findWorkLogById(workLogId);
        if (workLog == null) {
            throw new WorkLogDontExistException("Deleting work log dont exists");
        }
        return workLogRepository.deleteWorkLog(workLog);
    }

    @Override
    public List<WorkLog> reportWorkerWork(Worker worker) {
        return workLogRepository.findWorkLogsByWorker(worker);
    }

    @Override
    public WorkLog findWorkLogById(Long id) throws BaseException {
        WorkLog workLog = workLogRepository.findWorkLogById(id);
        if (workLog == null) {
            throw new WorkLogDontExistException("Deleting work log dont exists");
        }
        return workLog;
    }
    
     private void validateEditWorkLog(WorkLog workLog) throws BaseException {
        if (workLog.getStartDate().isAfter(LocalDate.now())) {
            throw new WorkLogStartDateException("Start date is from future");
        }
        if (workLog.getTimeSpentInSeconds().compareTo(BigInteger.ZERO) <= 0) {
            throw new WorkLogTimeSpentException("Time spent cant be less or equal to 0");
        }
    }
    
    private void validateWorkLog(WorkLog workLog) throws BaseException {
        if (workerRepository.findWorkerByLogin(workLog.getAssociatedWorker().getLogin()) == null) {
            throw new WorkerDontExistsException("Worker dont exist");
        }
        if (workLog.getStartDate().isAfter(LocalDate.now())) {
            throw new WorkLogStartDateException("Start date is from future");
        }
        if (workLog.getTimeSpentInSeconds().compareTo(BigInteger.ZERO) <= 0) {
            throw new WorkLogTimeSpentException("Time spent cant be less or equal to 0");
        }
    }
}

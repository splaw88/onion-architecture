package pl.splaw.applicationlogic.services.impl;


import org.junit.Before;
import org.junit.Test;
import pl.splaw.domain.model.WorkLog;
import pl.splaw.repositoryinterface.repository.WorkLogRepositoryI;
import pl.splaw.repositoryinterface.repository.WorkerRepositoryI;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static pl.splaw.applicationlogic.services.impl.WorkLogRepositoryStub.*;
import static pl.splaw.applicationlogic.services.impl.WorkerRepositoryStub.*;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class WorkLogServiceTest {

    private WorkLogService workLogService;
    private WorkerRepositoryI workerRepositoryI;
    private WorkLogRepositoryI workLogRepositoryI;

    @Before
    public void init() {
        workerRepositoryI = new WorkerRepositoryStub(existingWorker());
        workLogRepositoryI = new WorkLogRepositoryStub(workLogWithExistingWorker(1l));
        workLogService = new WorkLogService(workLogRepositoryI, workerRepositoryI);
    }

    @Test
    public void logWork_Success() throws pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException {

        WorkLog newWorkLog = workLogWithExistingWorker(2l);
        WorkLog result = workLogService.logWork(newWorkLog);

        assertEquals(newWorkLog.getWorkLogId(), result.getWorkLogId());
        assertEquals(newWorkLog.getDescription(), result.getDescription());
        assertEquals(newWorkLog.getStartDate(), result.getStartDate());
        assertEquals(newWorkLog.getTimeSpentInSeconds(), result.getTimeSpentInSeconds());
        assertEquals(newWorkLog.getAssociatedWorker().getLogin(), result.getAssociatedWorker().getLogin());
        assertEquals(2, getWorkLogRepositoryStub().getWorkLogList().size());

    }

    @Test(expected = pl.splaw.onionarchitecture.applicationservices.exceptions.worker.WorkerDontExistsException.class)
    public void logWork_WorkerDontExist() throws pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException {

        WorkLog newWorkLog = workLogWithNonExistingWorker(2l);
        WorkLog result = workLogService.logWork(newWorkLog);

        assertEquals(newWorkLog.getWorkLogId(), result.getWorkLogId());
        assertEquals(newWorkLog.getDescription(), result.getDescription());
        assertEquals(newWorkLog.getStartDate(), result.getStartDate());
        assertEquals(newWorkLog.getTimeSpentInSeconds(), result.getTimeSpentInSeconds());
        assertEquals(newWorkLog.getAssociatedWorker().getLogin(), result.getAssociatedWorker().getLogin());
        assertEquals(2, getWorkLogRepositoryStub().getWorkLogList().size());

    }

    @Test(expected = pl.splaw.onionarchitecture.applicationservices.exceptions.worklog.WorkLogStartDateException.class)
    public void logWork_StartDateFromFuture() throws pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException {

        WorkLog newWorkLog = workLogWithExistingWorker(2l, LocalDate.MAX);
        WorkLog result = workLogService.logWork(newWorkLog);

        assertEquals(newWorkLog.getWorkLogId(), result.getWorkLogId());
        assertEquals(newWorkLog.getDescription(), result.getDescription());
        assertEquals(newWorkLog.getStartDate(), result.getStartDate());
        assertEquals(newWorkLog.getTimeSpentInSeconds(), result.getTimeSpentInSeconds());
        assertEquals(newWorkLog.getAssociatedWorker().getLogin(), result.getAssociatedWorker().getLogin());
        assertEquals(2, getWorkLogRepositoryStub().getWorkLogList().size());

    }

    @Test(expected = pl.splaw.onionarchitecture.applicationservices.exceptions.worklog.WorkLogTimeSpentException.class)
    public void logWork_NegativeTimeSpent() throws pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException {

        WorkLog newWorkLog = workLogWithExistingWorker(2l, -1);
        WorkLog result = workLogService.logWork(newWorkLog);

        assertEquals(newWorkLog.getWorkLogId(), result.getWorkLogId());
        assertEquals(newWorkLog.getDescription(), result.getDescription());
        assertEquals(newWorkLog.getStartDate(), result.getStartDate());
        assertEquals(newWorkLog.getTimeSpentInSeconds(), result.getTimeSpentInSeconds());
        assertEquals(newWorkLog.getAssociatedWorker().getLogin(), result.getAssociatedWorker().getLogin());
        assertEquals(2, getWorkLogRepositoryStub().getWorkLogList().size());

    }

    @Test
    public void editWorkLog_Success() throws pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException {

        WorkLog editedWorkLog = workLogWithExistingWorker(2l);
        WorkLog result = workLogService.editWorkLog(1l, editedWorkLog);

        assertEquals(Long.valueOf(1l), result.getWorkLogId());
        assertEquals(editedWorkLog.getDescription(), result.getDescription());
        assertEquals(editedWorkLog.getStartDate(), result.getStartDate());
        assertEquals(editedWorkLog.getTimeSpentInSeconds(), result.getTimeSpentInSeconds());
        assertEquals(editedWorkLog.getAssociatedWorker().getLogin(), result.getAssociatedWorker().getLogin());
        assertEquals(1, getWorkLogRepositoryStub().getWorkLogList().size());

    }

    @Test(expected = pl.splaw.onionarchitecture.applicationservices.exceptions.worklog.WorkLogDontExistException.class)
    public void editWorkLog_WorkLogDontExist() throws pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException {

        WorkLog editedWorkLog = workLogWithNonExistingWorker(2l);
        WorkLog result = workLogService.editWorkLog(2l, editedWorkLog);

        assertEquals(editedWorkLog.getWorkLogId(), result.getWorkLogId());
        assertEquals(editedWorkLog.getDescription(), result.getDescription());
        assertEquals(editedWorkLog.getStartDate(), result.getStartDate());
        assertEquals(editedWorkLog.getTimeSpentInSeconds(), result.getTimeSpentInSeconds());
        assertEquals(editedWorkLog.getAssociatedWorker().getLogin(), result.getAssociatedWorker().getLogin());
        assertEquals(2, getWorkLogRepositoryStub().getWorkLogList().size());

    }

    @Test(expected = pl.splaw.onionarchitecture.applicationservices.exceptions.worklog.WorkLogStartDateException.class)
    public void editWorkLog_StartDateFromFuture() throws pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException {

        WorkLog editedWorkLog = workLogWithExistingWorker(2l, LocalDate.MAX);
        WorkLog result = workLogService.editWorkLog(1l, editedWorkLog);

        assertEquals(editedWorkLog.getWorkLogId(), result.getWorkLogId());
        assertEquals(editedWorkLog.getDescription(), result.getDescription());
        assertEquals(editedWorkLog.getStartDate(), result.getStartDate());
        assertEquals(editedWorkLog.getTimeSpentInSeconds(), result.getTimeSpentInSeconds());
        assertEquals(editedWorkLog.getAssociatedWorker().getLogin(), result.getAssociatedWorker().getLogin());
        assertEquals(2, getWorkLogRepositoryStub().getWorkLogList().size());

    }

    @Test(expected = pl.splaw.onionarchitecture.applicationservices.exceptions.worklog.WorkLogTimeSpentException.class)
    public void editWorkLog_NegativeTimeSpent() throws pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException {

        WorkLog editedWorkLog = workLogWithExistingWorker(2l, -1);
        WorkLog result = workLogService.editWorkLog(1l, editedWorkLog);

        assertEquals(editedWorkLog.getWorkLogId(), result.getWorkLogId());
        assertEquals(editedWorkLog.getDescription(), result.getDescription());
        assertEquals(editedWorkLog.getStartDate(), result.getStartDate());
        assertEquals(editedWorkLog.getTimeSpentInSeconds(), result.getTimeSpentInSeconds());
        assertEquals(editedWorkLog.getAssociatedWorker().getLogin(), result.getAssociatedWorker().getLogin());
        assertEquals(2, getWorkLogRepositoryStub().getWorkLogList().size());

    }

    @Test
    public void deleteWorkLog_Success() throws pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException {

        WorkLog existingWorkLog = getWorkLogRepositoryStub().getWorkLogList().stream().findAny().get();
        WorkLog result = workLogService.deleteWorkLog(1l);

        assertEquals(existingWorkLog.getWorkLogId(), result.getWorkLogId());
        assertEquals(existingWorkLog.getDescription(), result.getDescription());
        assertEquals(existingWorkLog.getStartDate(), result.getStartDate());
        assertEquals(existingWorkLog.getTimeSpentInSeconds(), result.getTimeSpentInSeconds());
        assertEquals(existingWorkLog.getAssociatedWorker().getLogin(), result.getAssociatedWorker().getLogin());
        assertTrue(getWorkLogRepositoryStub().getWorkLogList().isEmpty());

    }

    @Test(expected = pl.splaw.onionarchitecture.applicationservices.exceptions.worklog.WorkLogDontExistException.class)
    public void deleteWorkLog_WorkLogDontExist() throws pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException {
        workLogService.deleteWorkLog(2l);
    }

    @Test
    public void findWorkLogById_Success() throws pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException {

        WorkLog existingWorkLog = getWorkLogRepositoryStub().getWorkLogList().stream().findAny().get();
        WorkLog result = workLogService.findWorkLogById(1l);

        assertEquals(existingWorkLog.getWorkLogId(), result.getWorkLogId());
        assertEquals(existingWorkLog.getDescription(), result.getDescription());
        assertEquals(existingWorkLog.getStartDate(), result.getStartDate());
        assertEquals(existingWorkLog.getTimeSpentInSeconds(), result.getTimeSpentInSeconds());
        assertEquals(existingWorkLog.getAssociatedWorker().getLogin(), result.getAssociatedWorker().getLogin());
        assertEquals(1, getWorkLogRepositoryStub().getWorkLogList().size());

    }

    @Test(expected = pl.splaw.onionarchitecture.applicationservices.exceptions.worklog.WorkLogDontExistException.class)
    public void findWorkLogById_WorkLogDontExist() throws pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException {
        workLogService.findWorkLogById(2l);
    }

    @Test
    public void reportWorkerWorkLogs_Success() throws pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException {

        WorkLog existingWorkLog = getWorkLogRepositoryStub().getWorkLogList().stream().findAny().get();
        List<WorkLog> resultList = workLogService.reportWorkerWork(existingWorker());

        assertEquals(1, resultList.size());

        WorkLog result = resultList.get(0);

        assertEquals(existingWorkLog.getWorkLogId(), result.getWorkLogId());
        assertEquals(existingWorkLog.getDescription(), result.getDescription());
        assertEquals(existingWorkLog.getStartDate(), result.getStartDate());
        assertEquals(existingWorkLog.getTimeSpentInSeconds(), result.getTimeSpentInSeconds());
        assertEquals(existingWorkLog.getAssociatedWorker().getLogin(), result.getAssociatedWorker().getLogin());
        assertEquals(1, getWorkLogRepositoryStub().getWorkLogList().size());

    }

    @Test
    public void reportWorkerWorkLogs_NoWorkLogs() throws pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException {
        List<WorkLog> resultList = workLogService.reportWorkerWork(nonExistingWorker());
        assertTrue(resultList.isEmpty());
    }

    private WorkLogRepositoryStub getWorkLogRepositoryStub() {
        return (WorkLogRepositoryStub) workLogRepositoryI;
    }

}

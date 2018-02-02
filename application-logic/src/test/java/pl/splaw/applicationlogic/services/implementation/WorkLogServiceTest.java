package pl.splaw.onionarchitecture.applicationlogic.services.implementation;

import java.time.LocalDate;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkerRepositoryStub.exisitngWorker;
import static pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkerRepositoryStub.nonExisitngWorker;
import pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException;
import pl.splaw.onionarchitecture.applicationservices.exceptions.worker.WorkerDontExistsException;
import pl.splaw.onionarchitecture.applicationservices.exceptions.worklog.WorkLogDontExistException;
import pl.splaw.onionarchitecture.applicationservices.exceptions.worklog.WorkLogStartDateException;
import pl.splaw.onionarchitecture.applicationservices.exceptions.worklog.WorkLogTimeSpentException;
import pl.splaw.onionarchitecture.domain.model.WorkLog;
import pl.splaw.onionarchitecture.repositoryinterface.repository.WorkLogRepositoryI;
import pl.splaw.onionarchitecture.repositoryinterface.repository.WorkerRepositoryI;

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
        workerRepositoryI = new WorkerRepositoryStub(exisitngWorker());
        workLogRepositoryI = new WorkLogRepositoryStub(WorkLogRepositoryStub.workLogWithExistingWorker(1l));
        workLogService = new WorkLogService(workLogRepositoryI, workerRepositoryI);
    }

    @Test
    public void logWork_Success() throws BaseException {

        WorkLog newWorkLog = WorkLogRepositoryStub.workLogWithExistingWorker(2l);
        WorkLog result = workLogService.logWork(newWorkLog);

        assertEquals(newWorkLog.getWorkLogId(), result.getWorkLogId());
        assertEquals(newWorkLog.getDescription(), result.getDescription());
        assertEquals(newWorkLog.getStartDate(), result.getStartDate());
        assertEquals(newWorkLog.getTimeSpentInSeconds(), result.getTimeSpentInSeconds());
        assertEquals(newWorkLog.getAssociatedWorker().getLogin(), result.getAssociatedWorker().getLogin());
        assertEquals(2, getWorkLogRepositoryStub().getWorkLogList().size());

    }

    @Test(expected = WorkerDontExistsException.class)
    public void logWork_WorkerDontExist() throws BaseException {

        WorkLog newWorkLog = WorkLogRepositoryStub.workLogWithNonExistingWorker(2l);
        WorkLog result = workLogService.logWork(newWorkLog);

        assertEquals(newWorkLog.getWorkLogId(), result.getWorkLogId());
        assertEquals(newWorkLog.getDescription(), result.getDescription());
        assertEquals(newWorkLog.getStartDate(), result.getStartDate());
        assertEquals(newWorkLog.getTimeSpentInSeconds(), result.getTimeSpentInSeconds());
        assertEquals(newWorkLog.getAssociatedWorker().getLogin(), result.getAssociatedWorker().getLogin());
        assertEquals(2, getWorkLogRepositoryStub().getWorkLogList().size());

    }

    @Test(expected = WorkLogStartDateException.class)
    public void logWork_StartDateFromFuture() throws BaseException {

        WorkLog newWorkLog = WorkLogRepositoryStub.workLogWithExistingWorker(2l, LocalDate.MAX);
        WorkLog result = workLogService.logWork(newWorkLog);

        assertEquals(newWorkLog.getWorkLogId(), result.getWorkLogId());
        assertEquals(newWorkLog.getDescription(), result.getDescription());
        assertEquals(newWorkLog.getStartDate(), result.getStartDate());
        assertEquals(newWorkLog.getTimeSpentInSeconds(), result.getTimeSpentInSeconds());
        assertEquals(newWorkLog.getAssociatedWorker().getLogin(), result.getAssociatedWorker().getLogin());
        assertEquals(2, getWorkLogRepositoryStub().getWorkLogList().size());

    }

    @Test(expected = WorkLogTimeSpentException.class)
    public void logWork_NegativeTimeSpent() throws BaseException {

        WorkLog newWorkLog = WorkLogRepositoryStub.workLogWithExistingWorker(2l, -1);
        WorkLog result = workLogService.logWork(newWorkLog);

        assertEquals(newWorkLog.getWorkLogId(), result.getWorkLogId());
        assertEquals(newWorkLog.getDescription(), result.getDescription());
        assertEquals(newWorkLog.getStartDate(), result.getStartDate());
        assertEquals(newWorkLog.getTimeSpentInSeconds(), result.getTimeSpentInSeconds());
        assertEquals(newWorkLog.getAssociatedWorker().getLogin(), result.getAssociatedWorker().getLogin());
        assertEquals(2, getWorkLogRepositoryStub().getWorkLogList().size());

    }

    @Test
    public void editWorkLog_Success() throws BaseException {

        WorkLog editedWorkLog = WorkLogRepositoryStub.workLogWithExistingWorker(2l);
        WorkLog result = workLogService.editWorkLog(1l, editedWorkLog);

        assertEquals(Long.valueOf(1l), result.getWorkLogId());
        assertEquals(editedWorkLog.getDescription(), result.getDescription());
        assertEquals(editedWorkLog.getStartDate(), result.getStartDate());
        assertEquals(editedWorkLog.getTimeSpentInSeconds(), result.getTimeSpentInSeconds());
        assertEquals(editedWorkLog.getAssociatedWorker().getLogin(), result.getAssociatedWorker().getLogin());
        assertEquals(1, getWorkLogRepositoryStub().getWorkLogList().size());

    }

    @Test(expected = WorkLogDontExistException.class)
    public void editWorkLog_WorkLogDontExist() throws BaseException {

        WorkLog editedWorkLog = WorkLogRepositoryStub.workLogWithNonExistingWorker(2l);
        WorkLog result = workLogService.editWorkLog(2l, editedWorkLog);

        assertEquals(editedWorkLog.getWorkLogId(), result.getWorkLogId());
        assertEquals(editedWorkLog.getDescription(), result.getDescription());
        assertEquals(editedWorkLog.getStartDate(), result.getStartDate());
        assertEquals(editedWorkLog.getTimeSpentInSeconds(), result.getTimeSpentInSeconds());
        assertEquals(editedWorkLog.getAssociatedWorker().getLogin(), result.getAssociatedWorker().getLogin());
        assertEquals(2, getWorkLogRepositoryStub().getWorkLogList().size());

    }

    @Test(expected = WorkLogStartDateException.class)
    public void editWorkLog_StartDateFromFuture() throws BaseException {

        WorkLog editedWorkLog = WorkLogRepositoryStub.workLogWithExistingWorker(2l, LocalDate.MAX);
        WorkLog result = workLogService.editWorkLog(1l, editedWorkLog);

        assertEquals(editedWorkLog.getWorkLogId(), result.getWorkLogId());
        assertEquals(editedWorkLog.getDescription(), result.getDescription());
        assertEquals(editedWorkLog.getStartDate(), result.getStartDate());
        assertEquals(editedWorkLog.getTimeSpentInSeconds(), result.getTimeSpentInSeconds());
        assertEquals(editedWorkLog.getAssociatedWorker().getLogin(), result.getAssociatedWorker().getLogin());
        assertEquals(2, getWorkLogRepositoryStub().getWorkLogList().size());

    }

    @Test(expected = WorkLogTimeSpentException.class)
    public void editWorkLog_NegativeTimeSpent() throws BaseException {

        WorkLog editedWorkLog = WorkLogRepositoryStub.workLogWithExistingWorker(2l, -1);
        WorkLog result = workLogService.editWorkLog(1l, editedWorkLog);

        assertEquals(editedWorkLog.getWorkLogId(), result.getWorkLogId());
        assertEquals(editedWorkLog.getDescription(), result.getDescription());
        assertEquals(editedWorkLog.getStartDate(), result.getStartDate());
        assertEquals(editedWorkLog.getTimeSpentInSeconds(), result.getTimeSpentInSeconds());
        assertEquals(editedWorkLog.getAssociatedWorker().getLogin(), result.getAssociatedWorker().getLogin());
        assertEquals(2, getWorkLogRepositoryStub().getWorkLogList().size());

    }

    @Test
    public void deleteWorkLog_Success() throws BaseException {

        WorkLog existingWorkLog = getWorkLogRepositoryStub().getWorkLogList().stream().findAny().get();
        WorkLog result = workLogService.deleteWorkLog(1l);

        assertEquals(existingWorkLog.getWorkLogId(), result.getWorkLogId());
        assertEquals(existingWorkLog.getDescription(), result.getDescription());
        assertEquals(existingWorkLog.getStartDate(), result.getStartDate());
        assertEquals(existingWorkLog.getTimeSpentInSeconds(), result.getTimeSpentInSeconds());
        assertEquals(existingWorkLog.getAssociatedWorker().getLogin(), result.getAssociatedWorker().getLogin());
        assertTrue(getWorkLogRepositoryStub().getWorkLogList().isEmpty());

    }

    @Test(expected = WorkLogDontExistException.class)
    public void deleteWorkLog_WorkLogDontExist() throws BaseException {
        workLogService.deleteWorkLog(2l);
    }

    @Test
    public void findWorkLogById_Success() throws BaseException {

        WorkLog existingWorkLog = getWorkLogRepositoryStub().getWorkLogList().stream().findAny().get();
        WorkLog result = workLogService.findWorkLogById(1l);

        assertEquals(existingWorkLog.getWorkLogId(), result.getWorkLogId());
        assertEquals(existingWorkLog.getDescription(), result.getDescription());
        assertEquals(existingWorkLog.getStartDate(), result.getStartDate());
        assertEquals(existingWorkLog.getTimeSpentInSeconds(), result.getTimeSpentInSeconds());
        assertEquals(existingWorkLog.getAssociatedWorker().getLogin(), result.getAssociatedWorker().getLogin());
        assertEquals(1, getWorkLogRepositoryStub().getWorkLogList().size());

    }

    @Test(expected = WorkLogDontExistException.class)
    public void findWorkLogById_WorkLogDontExist() throws BaseException {
        workLogService.findWorkLogById(2l);
    }

    @Test
    public void reportWorkerWorkLogs_Success() throws BaseException {

        WorkLog existingWorkLog = getWorkLogRepositoryStub().getWorkLogList().stream().findAny().get();
        List<WorkLog> resultList = workLogService.reportWorkerWork(exisitngWorker());

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
    public void reportWorkerWorkLogs_NoWorkLogs() throws BaseException {
        List<WorkLog> resultList = workLogService.reportWorkerWork(nonExisitngWorker());
        assertTrue(resultList.isEmpty());
    }

    private WorkLogRepositoryStub getWorkLogRepositoryStub() {
        return (WorkLogRepositoryStub) workLogRepositoryI;
    }

}

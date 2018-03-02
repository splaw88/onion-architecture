package pl.splaw.applicationlogic.services.impl;

import lombok.Getter;
import pl.splaw.domain.model.WorkLog;
import pl.splaw.domain.model.Worker;
import pl.splaw.repositoryinterface.repository.WorkLogRepositoryI;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static pl.splaw.applicationlogic.services.impl.WorkerRepositoryStub.existingWorker;
import static pl.splaw.applicationlogic.services.impl.WorkerRepositoryStub.nonExistingWorker;


/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class WorkLogRepositoryStub implements WorkLogRepositoryI {

    @Getter
    private final List<WorkLog> workLogList;

    public static WorkLog workLogWithExistingWorker(Long id) {
        return newWroklog(id, LocalDate.now(), BigInteger.valueOf(1000l), existingWorker());
    }
    
    public static WorkLog workLogWithExistingWorker(Long id, LocalDate startDate) {
        return newWroklog(id, startDate, BigInteger.valueOf(1000l), existingWorker());
    }
    
    public static WorkLog workLogWithExistingWorker(Long id, Integer timeSpent) {
        return newWroklog(id, LocalDate.now(), BigInteger.valueOf(timeSpent.longValue()), existingWorker());
    }

    public static WorkLog workLogWithNonExistingWorker(Long id) {
        return newWroklog(id, LocalDate.now(), BigInteger.TEN, nonExistingWorker());
    }

    private static WorkLog newWroklog(Long id, LocalDate startDate, BigInteger timeSpent, Worker worker) {
        return new WorkLog(id, startDate, timeSpent, worker, "descript");
    }

    public WorkLogRepositoryStub(WorkLog... workLogs) {
        workLogList = new ArrayList<>();
        workLogList.addAll(Arrays.asList(workLogs));
    }

    @Override
    public WorkLog findWorkLogById(Long id) {
        return workLogList.stream().filter((worker) -> worker.getWorkLogId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<WorkLog> findWorkLogsByWorker(Worker worker) {
        return workLogList.stream().filter((workLog) -> workLog.getAssociatedWorker().getLogin().equals(worker.getLogin())).collect(Collectors.toList());
    }

    @Override
    public WorkLog createWorkLog(WorkLog workLog) {
        workLogList.add(workLog);
        return workLog;
    }

    @Override
    public WorkLog updateWorkLog(WorkLog workLog) {
        List<WorkLog> tempList = workLogList.stream().filter((workerInList) -> !workerInList.getWorkLogId().equals(workLog.getWorkLogId())).collect(Collectors.toList());
        workLogList.clear();
        workLogList.addAll(tempList);
        workLogList.add(workLog);
        return workLog;
    }

    @Override
    public WorkLog deleteWorkLog(WorkLog workLog) {
        List<WorkLog> tempList = workLogList.stream().filter((workerInList) -> !workerInList.getWorkLogId().equals(workLog.getWorkLogId())).collect(Collectors.toList());
        workLogList.clear();
        workLogList.addAll(tempList);
        return workLog;
    }

}

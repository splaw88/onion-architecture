package pl.splaw.databaserepository.mapper;

import pl.splaw.databaserepository.model.WorkLogEntity;
import pl.splaw.databaserepository.model.WorkerEntity;
import pl.splaw.domain.model.WorkLog;
import pl.splaw.domain.model.Worker;

import java.time.ZoneId;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class DomainToEntityMapper {

    public WorkLogEntity workLogToWorkLogEntity(WorkLog workLog) {
        return new WorkLogEntity(workLog.getStartDate(), workLog.getTimeSpentInSeconds(), workLog.getDescription(), workerToWorkerEntity(workLog.getAssociatedWorker()));
    }

    public WorkLogEntity workLogToWorkLogEntityWithId(WorkLog workLog) {
        return new WorkLogEntity(workLog.getWorkLogId(), workLog.getStartDate(), workLog.getTimeSpentInSeconds(), workLog.getDescription(), workerToWorkerEntity(workLog.getAssociatedWorker()));
    }

    public WorkLog workLogEntityToWorkLog(WorkLogEntity workLogEntity) {
        return new WorkLog(workLogEntity.getId(), workLogEntity.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), workLogEntity.getTimeSpentInSeconds(), workerEntityToWorker(workLogEntity.getAssociatedWorker()), workLogEntity.getDescription());
    }

    public WorkerEntity workerToWorkerEntity(Worker worker) {
        return new WorkerEntity(worker.getLogin(), worker.getName(), worker.getSurname(), worker.getEmail());
    }

    public Worker workerEntityToWorker(WorkerEntity workerEntity) {
        return new Worker(workerEntity.getLogin(), workerEntity.getName(), workerEntity.getSurname(), workerEntity.getEmail());
    }
}

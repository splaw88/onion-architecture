package pl.splaw.onionarchitecture.databaserepository.mapper;

import java.time.ZoneId;
import pl.splaw.onionarchitecture.domain.model.WorkLog;
import pl.splaw.onionarchitecture.domain.model.Worker;
import pl.splaw.onionarchitecture.databaserepository.model.WorkLogEntity;
import pl.splaw.onionarchitecture.databaserepository.model.WorkerEntity;

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

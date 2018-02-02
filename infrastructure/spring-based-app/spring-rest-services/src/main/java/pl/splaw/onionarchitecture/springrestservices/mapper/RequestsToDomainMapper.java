package pl.splaw.onionarchitecture.springrestservices.mapper;

import pl.splaw.onionarchitecture.domain.model.WorkLog;
import pl.splaw.onionarchitecture.domain.model.Worker;
import pl.splaw.onionarchitecture.springrestservices.dto.worker.EditWorkerRequest;
import pl.splaw.onionarchitecture.springrestservices.dto.worker.NewWorkerRequest;
import pl.splaw.onionarchitecture.springrestservices.dto.worklog.NewWorkLogRequest;
import pl.splaw.onionarchitecture.springrestservices.dto.worklog.UpdateWorkLogRequest;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class RequestsToDomainMapper {

    public WorkLog newWorkLogRequestToWorkLog(NewWorkLogRequest newWorkLogRequest, Worker worker) {
        return new WorkLog(null, newWorkLogRequest.getStartDate(), newWorkLogRequest.getTimeSpentInSeconds(), worker, newWorkLogRequest.getDescription());
    }

    public WorkLog updateWorkLogRequestToWorkLog(UpdateWorkLogRequest updateWorkLogRequest) {
        return new WorkLog(null, updateWorkLogRequest.getStartDate(), updateWorkLogRequest.getTimeSpentInSeconds(), null, updateWorkLogRequest.getDescription());
    }
    public Worker newWorkerRequestToWorker(NewWorkerRequest newWorkerRequest) {
        return new Worker(newWorkerRequest.getLogin(), newWorkerRequest.getName(), newWorkerRequest.getSurname(), newWorkerRequest.getEmail());
    }

    public Worker editWorkerRequestToWorker(EditWorkerRequest editWorkerRequest) {
        return new Worker(null, editWorkerRequest.getName(), editWorkerRequest.getSurname(), editWorkerRequest.getEmail());
    }
}

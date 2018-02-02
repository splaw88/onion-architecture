package pl.splaw.onionarchitecture.restservices.controller;

import java.util.List;
import javax.inject.Inject;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException;
import pl.splaw.onionarchitecture.applicationservices.services.WorkLogServiceI;
import pl.splaw.onionarchitecture.applicationservices.services.WorkerServiceI;
import pl.splaw.onionarchitecture.domain.model.Worker;
import pl.splaw.onionarchitecture.restservices.dto.worklog.NewWorkLogRequest;
import pl.splaw.onionarchitecture.restservices.dto.worklog.UpdateWorkLogRequest;
import pl.splaw.onionarchitecture.restservices.dto.worklog.WorkLogResponse;
import pl.splaw.onionarchitecture.restservices.mapper.DomainToResponseMapper;
import pl.splaw.onionarchitecture.restservices.mapper.RequestsToDomainMapper;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
@RestController
@RequestMapping("/work-log")
@AllArgsConstructor(onConstructor = @__(@Inject))
public class WorkLogController {

    private final WorkLogServiceI workLogService;
    private final WorkerServiceI workerService;
    private final DomainToResponseMapper domainToResponseMapper;
    private final RequestsToDomainMapper requestsToDomainMapper;

    @PutMapping
    public WorkLogResponse createNewWorkLog(@RequestBody NewWorkLogRequest request) throws BaseException {
        Worker worker = workerService.findWorkerByLogin(request.getAssociatedWorkerLogin());
        return domainToResponseMapper.mapToWorkLogResponse(workLogService.logWork(requestsToDomainMapper.newWorkLogRequestToWorkLog(request, worker)));
    }

    @PostMapping(path = "/{id}")
    public WorkLogResponse editWorkLog(@PathVariable(name = "id") Long workLogId, @RequestBody UpdateWorkLogRequest request) throws BaseException{
        return domainToResponseMapper.mapToWorkLogResponse(workLogService.editWorkLog(workLogId, requestsToDomainMapper.updateWorkLogRequestToWorkLog(request)));
    }

    @DeleteMapping(path = "/{id}")
    public WorkLogResponse delete(@PathVariable(name = "id") Long workLogId) throws BaseException {
        return domainToResponseMapper.mapToWorkLogResponse(workLogService.deleteWorkLog(workLogId));
    }

    @GetMapping(path = "/{login}")
    public List<WorkLogResponse> listWorkLogForWorkerLogin(@PathVariable(name = "login") String workerLogin) throws BaseException {
        Worker worker = workerService.findWorkerByLogin(workerLogin);
        return domainToResponseMapper.mapToWorkLogResponseList(workLogService.reportWorkerWork(worker));
    }
}

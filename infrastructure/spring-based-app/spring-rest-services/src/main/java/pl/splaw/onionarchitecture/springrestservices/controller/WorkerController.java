package pl.splaw.onionarchitecture.springrestservices.controller;

import org.springframework.web.bind.annotation.*;
import pl.splaw.applicationservices.exceptions.BaseException;
import pl.splaw.applicationservices.services.WorkerServiceI;
import pl.splaw.domain.model.Worker;
import pl.splaw.onionarchitecture.springrestservices.dto.worker.EditWorkerRequest;
import pl.splaw.onionarchitecture.springrestservices.dto.worker.NewWorkerRequest;
import pl.splaw.onionarchitecture.springrestservices.mapper.RequestsToDomainMapper;

import javax.inject.Inject;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
@RestController
@RequestMapping(path = "/worker")
public class WorkerController {

    @Inject
    private WorkerServiceI workerService;
    @Inject
    private RequestsToDomainMapper requestsToDomainMapper;

    @PutMapping
    public Worker createNewWorker(@RequestBody NewWorkerRequest request) throws BaseException {
        return workerService.createWorker(requestsToDomainMapper.newWorkerRequestToWorker(request));
    }

    @PostMapping(path = {"/{login}"})
    public Worker editWorker(@PathVariable(name = "login") String workerLogin, @RequestBody EditWorkerRequest request) throws BaseException {
        return workerService.editWorker(workerLogin, requestsToDomainMapper.editWorkerRequestToWorker(request));
    }

    @DeleteMapping(path = "/{login}")
    public Worker delete(@PathVariable(name = "login") String workerLogin) throws BaseException {
        return workerService.deleteWorker(workerLogin);
    }

    @GetMapping(path = "/{login}")
    public Worker findByLogin(@PathVariable(name = "login") String workerLogin) throws BaseException {
        return workerService.findWorkerByLogin(workerLogin);
    }
}

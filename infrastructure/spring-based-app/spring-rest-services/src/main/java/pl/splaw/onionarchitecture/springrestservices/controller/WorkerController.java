package pl.splaw.onionarchitecture.springrestservices.controller;

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
import pl.splaw.applicationservices.services.WorkerServiceI;
import pl.splaw.domain.model.Worker;
import pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException;

import pl.splaw.onionarchitecture.springrestservices.dto.worker.EditWorkerRequest;
import pl.splaw.onionarchitecture.springrestservices.dto.worker.NewWorkerRequest;
import pl.splaw.onionarchitecture.springrestservices.mapper.RequestsToDomainMapper;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
@RestController
@RequestMapping(path = "/worker")
@AllArgsConstructor(onConstructor = @__(
        @Inject))
public class WorkerController {

    private final WorkerServiceI workerService;
    private final RequestsToDomainMapper requestsToDomainMapper;

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

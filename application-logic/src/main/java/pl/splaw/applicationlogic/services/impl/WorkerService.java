package pl.splaw.applicationlogic.services.impl;



import pl.splaw.applicationservices.exceptions.BaseException;
import pl.splaw.applicationservices.exceptions.worker.WorkerDontExistsException;
import pl.splaw.applicationservices.exceptions.worker.WorkerExistsException;
import pl.splaw.applicationservices.services.WorkerServiceI;
import pl.splaw.domain.model.Worker;
import pl.splaw.repositoryinterface.repository.WorkerRepositoryI;

import javax.inject.Inject;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class WorkerService implements WorkerServiceI {

    private final WorkerRepositoryI workerRepository;

    @Inject
    public WorkerService(WorkerRepositoryI workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public Worker createWorker(Worker worker) throws BaseException {
        if (workerRepository.findWorkerByLogin(worker.getLogin()) != null) {
            throw new WorkerExistsException("Worker already exists");
        }
        return workerRepository.saveWorker(worker);
    }

    @Override
    public Worker editWorker(String login, Worker editedWorker) throws BaseException {
        Worker worker = workerRepository.findWorkerByLogin(login);
        if (worker == null) {
            throw new WorkerDontExistsException("Edited worker dont exists");
        }
        return workerRepository.updateWorker(new Worker(login, editedWorker.getName(), editedWorker.getSurname(), editedWorker.getEmail()));
    }

    @Override
    public Worker deleteWorker(String login) throws BaseException {
        Worker worker = workerRepository.findWorkerByLogin(login);
        if (worker == null) {
            throw new WorkerDontExistsException("Issued worker dont exists");
        }
        return workerRepository.deleteWorker(worker);
    }

    @Override
    public Worker findWorkerByLogin(String login) throws BaseException {
        Worker worker = workerRepository.findWorkerByLogin(login);
        if (worker == null) {
            throw new WorkerDontExistsException("Issued worker dont exists");
        }
        return worker;
    }

}

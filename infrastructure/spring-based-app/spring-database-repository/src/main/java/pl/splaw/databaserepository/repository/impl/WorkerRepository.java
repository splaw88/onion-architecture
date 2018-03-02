package pl.splaw.databaserepository.repository.impl;

import javax.inject.Inject;
import org.springframework.transaction.annotation.Transactional;
import pl.splaw.databaserepository.mapper.DomainToEntityMapper;
import pl.splaw.databaserepository.model.WorkerEntity;
import pl.splaw.databaserepository.repository.WorkLogEntityRepository;
import pl.splaw.databaserepository.repository.WorkerEntityRepository;
import pl.splaw.domain.model.Worker;
import pl.splaw.repositoryinterface.repository.WorkerRepositoryI;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class WorkerRepository implements WorkerRepositoryI {

    private final DomainToEntityMapper domainToEntityMapper;
    private final WorkerEntityRepository workerEntityRepository;
    private final WorkLogEntityRepository workLogEntityRepository;

    @Inject
    public WorkerRepository(DomainToEntityMapper domainToEntityMapper, WorkerEntityRepository workerEntityRepository, WorkLogEntityRepository workLogEntityRepository) {
        this.domainToEntityMapper = domainToEntityMapper;
        this.workerEntityRepository = workerEntityRepository;
        this.workLogEntityRepository = workLogEntityRepository;
    }

    @Override
    public Worker findWorkerByLogin(String login) {
        WorkerEntity entity = workerEntityRepository.findWorkerEntityByLogin(login);
        if (entity != null) {
            return domainToEntityMapper.workerEntityToWorker(entity);
        }
        return null;
    }

    @Override
    public Worker saveWorker(Worker worker) {
        return domainToEntityMapper.workerEntityToWorker(workerEntityRepository.save(domainToEntityMapper.workerToWorkerEntity(worker)));
    }

    @Override
    public Worker updateWorker(Worker worker) {
        return saveWorker(worker);
    }

    @Override
    @Transactional
    public Worker deleteWorker(Worker worker) {
        workLogEntityRepository.deleteByAssociatedWorker_Login(worker.getLogin());
        workerEntityRepository.delete(domainToEntityMapper.workerToWorkerEntity(worker));
        return worker;
    }

}

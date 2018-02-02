package pl.splaw.onionarchitecture.databaserepository.repository.impl;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import pl.splaw.onionarchitecture.domain.model.WorkLog;
import pl.splaw.onionarchitecture.domain.model.Worker;
import pl.splaw.onionarchitecture.databaserepository.mapper.DomainToEntityMapper;
import pl.splaw.onionarchitecture.databaserepository.repository.WorkLogEntityRepository;
import pl.splaw.onionarchitecture.repositoryinterface.repository.WorkLogRepositoryI;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class WorkLogRepository implements WorkLogRepositoryI {

    private final DomainToEntityMapper domainToEntityMapper;
    private final WorkLogEntityRepository workLogEntityRepository;

    @Inject
    public WorkLogRepository(DomainToEntityMapper domainToEntityMapper, WorkLogEntityRepository workLogEntityRepository) {
        this.domainToEntityMapper = domainToEntityMapper;
        this.workLogEntityRepository = workLogEntityRepository;
    }

    @Override
    public WorkLog findWorkLogById(Long id) {
        return domainToEntityMapper.workLogEntityToWorkLog(workLogEntityRepository.findOne(id));

    }

    @Override
    public List<WorkLog> findWorkLogsByWorker(Worker worker) {
        return workLogEntityRepository.findWorkLogEntityByAssociatedWorker(domainToEntityMapper.workerToWorkerEntity(worker)).stream()
                .map((workLog) -> domainToEntityMapper.workLogEntityToWorkLog(workLog))
                .collect(Collectors.toList());
    }

    @Override
    public WorkLog createWorkLog(WorkLog workLog) {
        return domainToEntityMapper.workLogEntityToWorkLog(workLogEntityRepository.save(domainToEntityMapper.workLogToWorkLogEntity(workLog)));
    }

    @Override
    public WorkLog updateWorkLog(WorkLog workLog) {
        return domainToEntityMapper.workLogEntityToWorkLog(workLogEntityRepository.save(domainToEntityMapper.workLogToWorkLogEntityWithId(workLog)));
    }

    @Override
    public WorkLog deleteWorkLog(WorkLog workLog) {
        workLogEntityRepository.delete(domainToEntityMapper.workLogToWorkLogEntityWithId(workLog));
        return workLog;
    }

}

package pl.splaw.onionarchitecture.springdependencyinjection.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.splaw.onionarchitecture.databaserepository.mapper.DomainToEntityMapper;
import pl.splaw.onionarchitecture.databaserepository.repository.WorkLogEntityRepository;
import pl.splaw.onionarchitecture.databaserepository.repository.WorkerEntityRepository;
import pl.splaw.onionarchitecture.databaserepository.repository.impl.WorkLogRepository;
import pl.splaw.onionarchitecture.databaserepository.repository.impl.WorkerRepository;
import pl.splaw.onionarchitecture.repositoryinterface.repository.WorkLogRepositoryI;
import pl.splaw.onionarchitecture.repositoryinterface.repository.WorkerRepositoryI;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
@Configuration
public class RepositoryFactory {

    @Bean
    public WorkerRepositoryI workerRepository(DomainToEntityMapper domainToEntityMapper, WorkerEntityRepository workerEntityRepository, WorkLogEntityRepository workLogEntityRepository) {
        return new WorkerRepository(domainToEntityMapper, workerEntityRepository, workLogEntityRepository);
    }
    
    @Bean
    public WorkLogRepositoryI workLogRepository(DomainToEntityMapper domainToEntityMapper, WorkLogEntityRepository workLogEntityRepository) {
        return new WorkLogRepository(domainToEntityMapper, workLogEntityRepository);
    }
}

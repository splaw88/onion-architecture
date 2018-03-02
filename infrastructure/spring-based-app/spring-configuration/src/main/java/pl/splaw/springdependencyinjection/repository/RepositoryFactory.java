package pl.splaw.springdependencyinjection.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.splaw.databaserepository.mapper.DomainToEntityMapper;
import pl.splaw.databaserepository.repository.WorkLogEntityRepository;
import pl.splaw.databaserepository.repository.WorkerEntityRepository;
import pl.splaw.databaserepository.repository.impl.WorkLogRepository;
import pl.splaw.databaserepository.repository.impl.WorkerRepository;
import pl.splaw.repositoryinterface.repository.WorkLogRepositoryI;
import pl.splaw.repositoryinterface.repository.WorkerRepositoryI;


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

package pl.splaw.springdependencyinjection.service;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.splaw.applicationlogic.services.impl.WorkLogService;
import pl.splaw.applicationlogic.services.impl.WorkerService;
import pl.splaw.applicationservices.services.WorkLogServiceI;
import pl.splaw.applicationservices.services.WorkerServiceI;
import pl.splaw.repositoryinterface.repository.WorkLogRepositoryI;
import pl.splaw.repositoryinterface.repository.WorkerRepositoryI;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
@Configuration
public class ApplicationLogicFactory {

    @Bean
    public WorkLogServiceI workLogService(WorkLogRepositoryI workLogRepositoryI, WorkerRepositoryI workerRepositoryI) {
        return new WorkLogService(workLogRepositoryI, workerRepositoryI);
    }
    @Bean
    public WorkerServiceI workerService(WorkerRepositoryI workerRepositoryI) {
        return new WorkerService(workerRepositoryI);
    }
}

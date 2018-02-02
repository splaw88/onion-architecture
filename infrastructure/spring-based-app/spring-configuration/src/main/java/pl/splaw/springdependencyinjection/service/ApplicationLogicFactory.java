package pl.splaw.onionarchitecture.springdependencyinjection.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkLogService;
import pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkerService;
import pl.splaw.onionarchitecture.applicationservices.services.WorkLogServiceI;
import pl.splaw.onionarchitecture.applicationservices.services.WorkerServiceI;
import pl.splaw.onionarchitecture.repositoryinterface.repository.WorkLogRepositoryI;
import pl.splaw.onionarchitecture.repositoryinterface.repository.WorkerRepositoryI;

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

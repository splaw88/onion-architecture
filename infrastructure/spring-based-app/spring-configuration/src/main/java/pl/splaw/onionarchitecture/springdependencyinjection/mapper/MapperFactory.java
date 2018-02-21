package pl.splaw.onionarchitecture.springdependencyinjection.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.splaw.onionarchitecture.databaserepository.mapper.DomainToEntityMapper;
import pl.splaw.onionarchitecture.springrestservices.mapper.DomainToResponseMapper;
import pl.splaw.onionarchitecture.springrestservices.mapper.RequestsToDomainMapper;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
@Configuration
public class MapperFactory {

    @Bean
    public DomainToEntityMapper domainToEntityMapper() {
        return new DomainToEntityMapper();
    }

    @Bean
    public DomainToResponseMapper domainToResponseMapper() {
        return new DomainToResponseMapper();
    }

    @Bean
    public RequestsToDomainMapper requestsToDomainMapper() {
        return new RequestsToDomainMapper();
    }

}

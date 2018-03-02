package pl.splaw.databaserepository.config;

import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import pl.splaw.databaserepository.model.EntityScanMarker;
import pl.splaw.databaserepository.repository.RepositoryScanMarker;


/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
@Configuration
@EntityScan(basePackageClasses = EntityScanMarker.class )
@EnableJpaRepositories(basePackageClasses = RepositoryScanMarker.class)
public class DatabaseConfiguration {

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.H2)
                .build();
        return db;
    }
}

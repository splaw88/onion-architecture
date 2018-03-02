package pl.splaw.databaserepository.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.splaw.databaserepository.model.WorkerEntity;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public interface WorkerEntityRepository extends JpaRepository<WorkerEntity, Long> {

    WorkerEntity findWorkerEntityByLogin(String login);
}

package pl.splaw.onionarchitecture.databaserepository.repository;

import java.util.List;
import pl.splaw.onionarchitecture.databaserepository.model.WorkLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import pl.splaw.onionarchitecture.databaserepository.model.WorkerEntity;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public interface WorkLogEntityRepository extends JpaRepository<WorkLogEntity, Long> {

    WorkLogEntity findWorkLogEntityById(Long id);

    List<WorkLogEntity> findWorkLogEntityByAssociatedWorker(WorkerEntity associatedWorker);
    
    List<WorkLogEntity> deleteByAssociatedWorker_Login(@Param("login") String login);
}

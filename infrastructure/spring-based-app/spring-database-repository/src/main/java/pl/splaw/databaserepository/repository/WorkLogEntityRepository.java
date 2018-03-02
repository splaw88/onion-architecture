package pl.splaw.databaserepository.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import pl.splaw.databaserepository.model.WorkLogEntity;
import pl.splaw.databaserepository.model.WorkerEntity;

import java.util.List;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public interface WorkLogEntityRepository extends JpaRepository<WorkLogEntity, Long> {

    WorkLogEntity findWorkLogEntityById(Long id);

    List<WorkLogEntity> findWorkLogEntityByAssociatedWorker(WorkerEntity associatedWorker);
    
    List<WorkLogEntity> deleteByAssociatedWorker_Login(@Param("login") String login);
}

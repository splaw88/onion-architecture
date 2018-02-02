package pl.splaw.onionarchitecture.databaserepository.model;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
@Entity
@Table(name = "WORK_LOG")
@NoArgsConstructor
@Getter
@Setter
public class WorkLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "work_log_id")
    private Long id;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "time_spent_in_seconds")
    private BigInteger timeSpentInSeconds;
    @Column(name = "description")
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "worker_login", referencedColumnName = "login")
    private WorkerEntity associatedWorker;

    public WorkLogEntity(LocalDate startDate, BigInteger timeSpentInSeconds, String description, WorkerEntity associatedWorker) {
        this.startDate = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.timeSpentInSeconds = timeSpentInSeconds;
        this.description = description;
        this.associatedWorker = associatedWorker;
    }
    
    public WorkLogEntity(Long id, LocalDate startDate, BigInteger timeSpentInSeconds, String description, WorkerEntity associatedWorker) {
        this(startDate, timeSpentInSeconds, description, associatedWorker);
        this.setId(id);
    }

    
}

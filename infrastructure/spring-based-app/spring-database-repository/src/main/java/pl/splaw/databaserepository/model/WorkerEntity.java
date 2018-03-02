package pl.splaw.databaserepository.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
@Entity
@Table(name = "WORKER")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkerEntity {

    @Id
    @Column(name = "login")
    private String login;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;

}

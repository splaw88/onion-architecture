package pl.splaw.onionarchitecture.springrestservices.dto.worker;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
@Getter
@Setter
public class NewWorkerRequest {

    private String login;
    private String name;
    private String surname;
    private String email;
}

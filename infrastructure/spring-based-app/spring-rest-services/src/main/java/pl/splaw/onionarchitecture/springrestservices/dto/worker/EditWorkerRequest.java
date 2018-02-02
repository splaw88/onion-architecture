package pl.splaw.onionarchitecture.springrestservices.dto.worker;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
@Getter
@Setter
public class EditWorkerRequest {

    private String name;
    private String surname;
    private String email;
}

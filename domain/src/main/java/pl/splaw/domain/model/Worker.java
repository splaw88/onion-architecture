package pl.splaw.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
@Getter
@AllArgsConstructor
public final class Worker {

    private final String login;
    private final String name;
    private final String surname;
    private final String email;

}

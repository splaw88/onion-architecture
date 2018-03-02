package pl.splaw.domain.model;

import java.math.BigInteger;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
@Getter
@AllArgsConstructor
public class WorkLog {

    private final Long workLogId;
    private final LocalDate startDate;
    private final BigInteger timeSpentInSeconds;
    private final Worker associatedWorker;
    private final String description;

}

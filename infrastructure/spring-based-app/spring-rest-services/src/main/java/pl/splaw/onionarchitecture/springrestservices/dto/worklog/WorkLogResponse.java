package pl.splaw.onionarchitecture.springrestservices.dto.worklog;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.math.BigInteger;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.splaw.domain.model.Worker;
import pl.splaw.onionarchitecture.springrestservices.serializer.LocalDateSerializer;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkLogResponse {

    private Long workLogId;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate;
    private BigInteger timeSpentInSeconds;
    private Worker associatedWorker;
    private String description;
}

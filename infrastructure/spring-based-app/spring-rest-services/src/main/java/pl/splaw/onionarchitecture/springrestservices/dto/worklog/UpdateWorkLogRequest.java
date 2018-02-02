package pl.splaw.onionarchitecture.springrestservices.dto.worklog;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.math.BigInteger;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import pl.splaw.onionarchitecture.springrestservices.serializer.LocalDateDeserializer;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
@Getter
@Setter
public class UpdateWorkLogRequest {

    @JsonDeserialize(using = LocalDateDeserializer.class) 
    private LocalDate startDate;
    private BigInteger timeSpentInSeconds;
    private String description;
}

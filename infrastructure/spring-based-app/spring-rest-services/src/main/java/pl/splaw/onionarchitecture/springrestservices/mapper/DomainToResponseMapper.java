package pl.splaw.onionarchitecture.springrestservices.mapper;

import java.util.List;
import java.util.stream.Collectors;
import pl.splaw.onionarchitecture.domain.model.WorkLog;
import pl.splaw.onionarchitecture.springrestservices.dto.worklog.WorkLogResponse;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class DomainToResponseMapper {

    public WorkLogResponse mapToWorkLogResponse(WorkLog workLog) {
        return new WorkLogResponse(workLog.getWorkLogId(), workLog.getStartDate(), workLog.getTimeSpentInSeconds(), workLog.getAssociatedWorker(), workLog.getDescription());
    }

    public List<WorkLogResponse> mapToWorkLogResponseList(List<WorkLog> workLogList) {
        return workLogList.stream().map((workLog) -> mapToWorkLogResponse(workLog)).collect(Collectors.toList());
    }
}

package pl.splaw.onionarchitecture.inmemory.worklog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pl.splaw.onionarchitecture.domain.model.WorkLog;
import pl.splaw.onionarchitecture.domain.model.Worker;
import pl.splaw.onionarchitecture.repositoryinterface.repository.WorkLogRepositoryI;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class WorklogInMemoryRepository implements WorkLogRepositoryI {

    private final Map<String, List<WorkLog>> memory = new HashMap<>();

    @Override
    public WorkLog findWorkLogById(Long id) {
         return memory.values().stream().flatMap(List::stream).filter((worklog)->worklog.getWorkLogId().equals(id)).findFirst().get();
    }

    @Override
    public List<WorkLog> findWorkLogsByWorker(Worker worker) {
        return memory.get(worker.getLogin());
    }

    @Override
    public WorkLog createWorkLog(WorkLog workLog) {
        return put(workLog);
    }

    @Override
    public WorkLog updateWorkLog(WorkLog workLog) {
        return put(workLog);
    }

    @Override
    public WorkLog deleteWorkLog(WorkLog workLog) {
        throw new UnsupportedOperationException("Not supported");
    }

    private WorkLog put(WorkLog workLog) {
        memory.putIfAbsent(workLog.getAssociatedWorker().getLogin(), new ArrayList<>());
        List<WorkLog> valueList = memory.get(workLog.getAssociatedWorker().getLogin());
        valueList.add(workLog);
        memory.put(workLog.getAssociatedWorker().getLogin(), valueList);
        return workLog;
    }

}

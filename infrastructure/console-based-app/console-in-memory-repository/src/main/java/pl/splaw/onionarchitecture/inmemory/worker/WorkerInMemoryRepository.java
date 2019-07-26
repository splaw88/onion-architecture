package pl.splaw.onionarchitecture.inmemory.worker;

import java.util.HashMap;
import java.util.Map;
import pl.splaw.onionarchitecture.domain.model.Worker;
import pl.splaw.onionarchitecture.repositoryinterface.repository.WorkerRepositoryI;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class WorkerInMemoryRepository implements WorkerRepositoryI {

    private final Map<String, Worker> memory = new HashMap<>();

    @Override
    public Worker findWorkerByLogin(String login) {
        return memory.get(login);
    }

    @Override
    public Worker saveWorker(Worker worker) {
        return memory.put(worker.getLogin(), worker);
    }

    @Override
    public Worker updateWorker(Worker worker) {
        return memory.put(worker.getLogin(), worker);
    }

    @Override
    public Worker deleteWorker(Worker worker) {
        return memory.remove(worker.getLogin());
    }

}

package pl.splaw.repositoryinterface.repository;


import pl.splaw.domain.model.Worker;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public interface WorkerRepositoryI {

    Worker findWorkerByLogin(String login);

    Worker saveWorker(Worker worker);

    Worker updateWorker(Worker worker);

    Worker deleteWorker(Worker worker);


}

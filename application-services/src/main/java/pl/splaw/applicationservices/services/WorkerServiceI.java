package pl.splaw.applicationservices.services;

import pl.splaw.domain.model.Worker;
import pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public interface WorkerServiceI {

    Worker createWorker(Worker worker) throws BaseException;

    Worker editWorker(String login, Worker worker) throws BaseException;

    Worker deleteWorker(String login) throws BaseException;

    Worker findWorkerByLogin(String login) throws BaseException;
}

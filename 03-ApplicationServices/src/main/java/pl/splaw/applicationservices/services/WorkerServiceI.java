package pl.splaw.onionarchitecture.applicationservices.services;

import pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException;
import pl.splaw.onionarchitecture.domain.model.Worker;

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

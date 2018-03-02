package pl.splaw.applicationlogic.services.impl;


import org.junit.Before;
import org.junit.Test;
import pl.splaw.domain.model.Worker;
import pl.splaw.repositoryinterface.repository.WorkerRepositoryI;

import static org.junit.Assert.*;
import static pl.splaw.applicationlogic.services.impl.WorkerRepositoryStub.*;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class WorkerServiceTest {

    private WorkerService workerService;
    private WorkerRepositoryI workerRepositoryI;

    @Before
    public void init() {
        workerRepositoryI = new WorkerRepositoryStub(existingWorker());
        workerService = new WorkerService(workerRepositoryI);
    }

    @Test
    public void findWorker_Success() {
        try {
            Worker result = workerService.findWorkerByLogin(EXISTING_LOGIN);
            assertEquals(EXISTING_LOGIN, result.getLogin());
            assertEquals(NAME, result.getName());
            assertEquals(SURNAME, result.getSurname());
            assertEquals(EMAIL, result.getEmail());

        } catch (Exception e) {
            fail(String.format("Error in deleting worker: %s", e.getMessage()));
        }
    }

    @Test(expected = pl.splaw.onionarchitecture.applicationservices.exceptions.worker.WorkerDontExistsException.class)
    public void findWorker_WorkerDontExist() throws pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException {
        workerService.findWorkerByLogin(NON_EXISTING_LOGIN);
    }

    @Test
    public void createWorker_Success() {
        try {
            Worker newWorker = nonExistingWorker();
            Worker result = workerService.createWorker(newWorker);
            assertEquals(newWorker.getLogin(), result.getLogin());
            assertEquals(newWorker.getName(), result.getName());
            assertEquals(newWorker.getSurname(), result.getSurname());
            assertEquals(newWorker.getEmail(), result.getEmail());
            assertEquals(2, getWorkerRepositoryStub().getWorkerList().size());
        } catch (Exception e) {
            fail(String.format("Error in creating worker: %s", e.getMessage()));
        }
    }

    @Test(expected = pl.splaw.onionarchitecture.applicationservices.exceptions.worker.WorkerExistsException.class)
    public void createWorker_WorkerExist() throws pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException {
        workerService.createWorker(existingWorker());
    }

    @Test
    public void editWorker_Success() {
        try {
            Worker editedWorker = nonExistingWorker();
            Worker result = workerService.editWorker(EXISTING_LOGIN, editedWorker);
            assertEquals(EXISTING_LOGIN, result.getLogin());
            assertEquals(OTHER_NAME, result.getName());
            assertEquals(OTHER_SURNAME, result.getSurname());
            assertEquals(OTHER_EMAIL, result.getEmail());
            assertEquals(1, getWorkerRepositoryStub().getWorkerList().size());
        } catch (Exception e) {
            fail(String.format("Error in editing worker: %s", e.getMessage()));
        }
    }

    @Test(expected = pl.splaw.onionarchitecture.applicationservices.exceptions.worker.WorkerDontExistsException.class)
    public void editWorker_WorkerDontExist() throws pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException {
        workerService.editWorker(NON_EXISTING_LOGIN, null);
    }

    @Test
    public void deleteWorker_Success() {
        try {
            Worker result = workerService.deleteWorker(EXISTING_LOGIN);
            assertEquals(EXISTING_LOGIN, result.getLogin());
            assertEquals(NAME, result.getName());
            assertEquals(SURNAME, result.getSurname());
            assertEquals(EMAIL, result.getEmail());
            assertTrue(getWorkerRepositoryStub().getWorkerList().isEmpty());

        } catch (Exception e) {
            fail(String.format("Error in deleting worker: %s", e.getMessage()));
        }
    }

    @Test(expected = pl.splaw.onionarchitecture.applicationservices.exceptions.worker.WorkerDontExistsException.class)
    public void deleteWorker_WorkerDontExist() throws pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException {
        workerService.deleteWorker(NON_EXISTING_LOGIN);
    }

    private WorkerRepositoryStub getWorkerRepositoryStub() {
        return (WorkerRepositoryStub) workerRepositoryI;
    }

}

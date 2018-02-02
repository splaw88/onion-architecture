package pl.splaw.onionarchitecture.applicationlogic.services.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import static pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkerRepositoryStub.EMAIL;
import static pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkerRepositoryStub.EXISTING_LOGIN;
import static pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkerRepositoryStub.NAME;
import static pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkerRepositoryStub.NON_EXISTING_LOGIN;
import static pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkerRepositoryStub.OTHER_EMAIL;
import static pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkerRepositoryStub.OTHER_NAME;
import static pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkerRepositoryStub.OTHER_SURNAME;
import static pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkerRepositoryStub.SURNAME;
import static pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkerRepositoryStub.exisitngWorker;
import static pl.splaw.onionarchitecture.applicationlogic.services.implementation.WorkerRepositoryStub.nonExisitngWorker;
import pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException;
import pl.splaw.onionarchitecture.applicationservices.exceptions.worker.WorkerDontExistsException;
import pl.splaw.onionarchitecture.applicationservices.exceptions.worker.WorkerExistsException;
import pl.splaw.onionarchitecture.domain.model.Worker;
import pl.splaw.onionarchitecture.repositoryinterface.repository.WorkerRepositoryI;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class WorkerServiceTest {

    private WorkerService workerService;
    private WorkerRepositoryI workerRepositoryI;

    @Before
    public void init() {
        workerRepositoryI = new WorkerRepositoryStub(exisitngWorker());
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

    @Test(expected = WorkerDontExistsException.class)
    public void findWorker_WorkerDontExist() throws BaseException {
        workerService.findWorkerByLogin(NON_EXISTING_LOGIN);
    }

    @Test
    public void createWorker_Success() {
        try {
            Worker newWorker = nonExisitngWorker();
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

    @Test(expected = WorkerExistsException.class)
    public void createWorker_WorkerExist() throws BaseException {
        workerService.createWorker(exisitngWorker());
    }

    @Test
    public void editWorker_Success() {
        try {
            Worker editedWorker = nonExisitngWorker();
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

    @Test(expected = WorkerDontExistsException.class)
    public void editWorker_WorkerDontExist() throws BaseException {
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

    @Test(expected = WorkerDontExistsException.class)
    public void deleteWorker_WorkerDontExist() throws BaseException {
        workerService.deleteWorker(NON_EXISTING_LOGIN);
    }

    private WorkerRepositoryStub getWorkerRepositoryStub() {
        return (WorkerRepositoryStub) workerRepositoryI;
    }

}

package pl.splaw.onionarchitecture.applicationservices.exceptions.worker;

import pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class WorkerExistsException extends BaseException {

    public WorkerExistsException(String string) {
        super(string);
    }
}

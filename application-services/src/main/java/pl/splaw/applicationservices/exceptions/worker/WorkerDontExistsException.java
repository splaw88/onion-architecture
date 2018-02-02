package pl.splaw.onionarchitecture.applicationservices.exceptions.worker;

import pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class WorkerDontExistsException extends BaseException {

    public WorkerDontExistsException(String string) {
        super(string);
    }

}

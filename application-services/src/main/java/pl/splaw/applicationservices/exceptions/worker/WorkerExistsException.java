package pl.splaw.applicationservices.exceptions.worker;


import pl.splaw.applicationservices.exceptions.BaseException;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class WorkerExistsException extends BaseException {

    public WorkerExistsException(String string) {
        super(string);
    }
}

package pl.splaw.applicationservices.exceptions.worker;


import pl.splaw.applicationservices.exceptions.BaseException;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class WorkerDontExistsException extends BaseException {

    public WorkerDontExistsException(String string) {
        super(string);
    }

}

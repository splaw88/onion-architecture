package pl.splaw.onionarchitecture.applicationservices.exceptions.worklog;

import pl.splaw.onionarchitecture.applicationservices.exceptions.BaseException;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class WorkLogDontExistException extends BaseException {

    public WorkLogDontExistException(String string) {
        super(string);
    }

}

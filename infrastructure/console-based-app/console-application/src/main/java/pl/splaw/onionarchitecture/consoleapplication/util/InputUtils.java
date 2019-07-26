package pl.splaw.onionarchitecture.consoleapplication.util;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public final class InputUtils {

    public static boolean isEmpty(String propertyName, String value) {
        boolean isEmpty = StringUtils.isBlank(value);
        if (isEmpty) {
            System.out.println(String.format("Invalid %s - cannot be empty", propertyName));
        }
        return isEmpty;
    }

    public static boolean isEmpty(String propertyName, Object value) {
        boolean isEmpty = value == null;
        if (isEmpty) {
            System.out.println(String.format("Invalid %s - cannot be empty", propertyName));
        }
        return isEmpty;
    }

    public static boolean isDateFormatCorrect(String dateString) {
        return !dateString.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$");
    }
}

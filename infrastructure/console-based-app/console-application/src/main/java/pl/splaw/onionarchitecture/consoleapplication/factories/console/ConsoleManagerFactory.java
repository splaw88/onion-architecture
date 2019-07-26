package pl.splaw.onionarchitecture.consoleapplication.factories.console;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public final class ConsoleManagerFactory {

    private static ConsoleManager consoleManager = null;

    public static ConsoleManager getInstance() {
        if (consoleManager == null) {
            consoleManager = new ConsoleManager();
        }
        return consoleManager;
    }
}

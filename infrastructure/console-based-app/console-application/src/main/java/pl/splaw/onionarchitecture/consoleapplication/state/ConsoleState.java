package pl.splaw.onionarchitecture.consoleapplication.state;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public interface ConsoleState {
    void process() throws Exception;
    ConsoleState nextState();
}

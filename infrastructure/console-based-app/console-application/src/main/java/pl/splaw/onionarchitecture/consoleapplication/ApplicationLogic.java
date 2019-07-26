package pl.splaw.onionarchitecture.consoleapplication;

import pl.splaw.onionarchitecture.consoleapplication.state.ConsoleState;
import pl.splaw.onionarchitecture.consoleapplication.state.main.MainState;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public class ApplicationLogic {

    private ConsoleState currentState;

    public ApplicationLogic() {
        this.currentState = new MainState(); //initial state
    }

    public void process() {
        while (true) {
            try {
                currentState.process();
                if (currentState.nextState() != null) {
                    ConsoleState nextState = currentState.nextState();
                    currentState = nextState;
                }
            } catch (Exception e) {
                System.err.println("Problem with input: " + e.getMessage());
                System.exit(1);
            }
        }
    }
}

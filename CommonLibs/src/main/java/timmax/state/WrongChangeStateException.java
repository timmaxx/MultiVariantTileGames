package timmax.state;

public class WrongChangeStateException extends RuntimeException {
    public WrongChangeStateException(State state1, State state2) {
        super("You cannot change state from '" + state1 + "' to '" + state2 + "'!");
    }
}

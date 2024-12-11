package timmax.state;

public class WrongMethodInvokeForCurrentStateException extends RuntimeException {
    public WrongMethodInvokeForCurrentStateException(State state) {
        super("Current state is '" + state.toString() + "'. " +
                "In this state you cannot use method '" + Thread.currentThread().getStackTrace()[3].getMethodName() + "'.");
    }
}

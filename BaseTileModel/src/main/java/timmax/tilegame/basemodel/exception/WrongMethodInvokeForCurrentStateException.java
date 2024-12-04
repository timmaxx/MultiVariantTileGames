package timmax.tilegame.basemodel.exception;

import timmax.tilegame.basemodel.protocol.server_client.IClientState99;

public class WrongMethodInvokeForCurrentStateException extends RuntimeException {
    public WrongMethodInvokeForCurrentStateException(IClientState99 state) {
        super("Current state is '" + state.toString() + "'. " +
                "In this state you cannot use method '" + Thread.currentThread().getStackTrace()[3].getMethodName() + "'.");
    }
}

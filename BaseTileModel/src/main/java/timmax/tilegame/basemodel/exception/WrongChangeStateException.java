package timmax.tilegame.basemodel.exception;

import timmax.tilegame.basemodel.protocol.server_client.IClientState99;

public class WrongChangeStateException extends RuntimeException {
    //  Warning:(8, 38) Raw use of parameterized class 'IClientState99'
    //  Warning:(8, 61) Raw use of parameterized class 'IClientState99'
    public WrongChangeStateException(IClientState99 state1, IClientState99 state2) {
        super("You cannot change state from '" + state1 + "' to '" + state2 + "'!");
    }
}

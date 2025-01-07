package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

//  Событие клиента об установлении соединения и передаче перечня типов игр.
public class EventOfClient11ConnectWithoutUserIdentify extends EventOfClient {
    @Override
    public void executeOnServer(RemoteClientStateAutomaton remoteClientStateAutomaton) {
        remoteClientStateAutomaton.connect();
    }

    @Override
    public String toString() {
        return
                EventOfClient11ConnectWithoutUserIdentify.class.getSimpleName()
                        // getClass().getSimpleName()
                        + "{" +
                        (super.toString().equals(getClass().getName() + "@" + Integer.toHexString(hashCode()))
                                ? ""
                                : ("{" + super.toString() + "}, ")
                        ) +
                        "}";
    }
}

package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

//  Событие клиента, сообщающее, что клиент как-бы заново выбрал тип игры (т.е. обнулил выбор матча).
public class EventOfClient62ResetGameType extends EventOfClient {
    @Override
    public void executeOnServer(RemoteClientStateAutomaton remoteClientStateAutomaton) {
        remoteClientStateAutomaton.resetGameType();
    }

    @Override
    public String toString() {
        return
                EventOfClient62ResetGameType.class.getSimpleName()
                        // getClass().getSimpleName()
                        + "{" +
                        (super.toString().equals(getClass().getName() + "@" + Integer.toHexString(hashCode()))
                                ? ""
                                : ("{" + super.toString() + "}, ")
                        ) +
                        "}";
    }
}

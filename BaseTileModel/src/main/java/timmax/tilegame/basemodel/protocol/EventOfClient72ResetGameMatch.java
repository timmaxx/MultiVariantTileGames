package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

//  Событие клиента, сообщающее, что клиент как-бы заново выбрал матч (т.е. перевел состояние матча на "не стартовал").
public class EventOfClient72ResetGameMatch extends EventOfClient {
    @Override
    public void executeOnServer(RemoteClientStateAutomaton remoteClientStateAutomaton) {
        remoteClientStateAutomaton.resetGameMatch();
    }

    @Override
    public String toString() {
        return
                EventOfClient72ResetGameMatch.class.getSimpleName()
                        // getClass().getSimpleName()
                        + "{" +
                        (super.toString().equals(getClass().getName() + "@" + Integer.toHexString(hashCode()))
                                ? ""
                                : ("{" + super.toString() + "}, ")
                        ) +
                        "}";
    }
}

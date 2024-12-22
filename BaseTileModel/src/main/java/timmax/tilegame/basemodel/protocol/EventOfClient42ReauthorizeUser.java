package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

//  Событие клиента, сообщающее, что клиент как-бы заново авторизовался (т.е. обнулил выбор типа игры (и в т.ч. матча)).
public class EventOfClient42ReauthorizeUser extends EventOfClient {
    @Override
    public void executeOnServer(RemoteClientStateAutomaton remoteClientStateAutomaton) {
        remoteClientStateAutomaton.reauthorizeUser();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                '}';
    }
}

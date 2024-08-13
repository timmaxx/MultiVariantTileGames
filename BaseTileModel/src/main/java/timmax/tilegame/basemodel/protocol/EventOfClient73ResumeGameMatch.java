package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient73ResumeGameMatch extends EventOfClient {
    public EventOfClient73ResumeGameMatch() {
        super();
    }

    // ToDo: Этот класс (и все последующие и некоторые предыдущие) уже может работать не только с
    //       RemoteClientStateAutomaton, но и с экземпляром GameMatch.
    //       Нужно пересмотреть архитектуру и передавать сюда GameMatch.
    // class EventOfClient
    @Override
    public void executeOnServer(RemoteClientStateAutomaton remoteClientStateAutomaton) {
        remoteClientStateAutomaton.resumeGameMatch();
    }

    // class Object
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                '}';
    }
}

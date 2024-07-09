package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.EventOfServer;
import timmax.tilegame.basemodel.protocol.EventOfServer92GameEvent;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.transport.TransportOfServer;

import java.util.HashSet;
import java.util.Set;

public class RemoteClientStateAutomaton<ClientId> extends ClientStateAutomaton<IGameMatch> {
    // ToDo: Удалить TransportOfServer<ClientId> multiGameWebSocketServer.
    //       Вместо того, чтобы хранить переменную типа TransportOfServer здесь, нужно рассмотреть вариант по передаче
    //       её как параметра в те методы, где она нужна.
    // ToDo: Удалить TransportOfServer<ClientId> multiGameWebSocketServer.
    //       1. TransportOfClient - LocalClientStateAutomaton:
    //         - В MultiGameWebSocketClientManyTimesUse есть переменная LocalClientStateAutomaton.
    //         - В LocalClientStateAutomaton нет переменной TransportOfClient (или MultiGameWebSocketClientManyTimesUse).
    //           И как-то без этого обходится.
    //       2. TransportOfServer - Map<WebSocket, RemoteClientStateAutomaton<WebSocket>>
    //         - В MultiGameWebSocketServer есть мапа WebSocket -> RemoteClientStateAutomaton
    //         - В RemoteClientStateAutomaton есть переменная TransportOfServer.
    //           Почему-же здесь нужна эта переменная?
    private final TransportOfServer<ClientId> multiGameWebSocketServer;

    // ToDo: Пересмотреть архитектуру и вероятно исключить отсюда Set<String> viewNameSet
    //       - перенести его в GameMatch:
    //       Основания те-же, что и для IGameMatch iGameMatch.
    private final Set<String> viewNameSet;

    // ToDo: Пересмотреть архитектуру и вероятно исключить отсюда IGameMatch:
    //       iGameMatch инициализируется значительно позже чем создаётся экземпляр
    //       RemoteClientStateAutomaton,
    //       Поэтому он:
    //       1. инициализируется через сеттер.
    //       2. не может быть final
    //       3. но возможно, что этому параметру вообще здесь не место!
    private IGameMatch iGameMatch;

    public RemoteClientStateAutomaton(
            IFabricOfRemoteClientStates<ClientId> fabricOfClientStatesForServer,
            TransportOfServer<ClientId> multiGameWebSocketServer) {
        super(fabricOfClientStatesForServer);
        this.multiGameWebSocketServer = multiGameWebSocketServer;
        this.viewNameSet = new HashSet<>();
    }

    public IGameMatch getGameMatch() {
        return iGameMatch;
    }

    public void setGameMatch(IGameMatch iGameMatch) {
        this.iGameMatch = iGameMatch;
    }

    void sendEventOfServer(ClientId clientId, EventOfServer transportPackageOfServer) {
        multiGameWebSocketServer.sendEventOfServer(clientId, transportPackageOfServer);
    }

    // Посылает игровое событие всем выборкам.
    void sendGameEventToAllViews(ClientId clientId, GameEvent gameEvent) {
        // ToDo: Пробовал сразу написать так:
        //       for (String viewName : remoteClientState.getSetOfViewName())
        //       Но такой вариант даже не компилировался.
        //       Разобраться!
        for (String viewName : viewNameSet) {
            EventOfServer eventOfServer = new EventOfServer92GameEvent(viewName, gameEvent);
            sendEventOfServer(clientId, eventOfServer);
        }
    }

    void viewNameSetClear() {
        viewNameSet.clear();
    }

    boolean viewNameSetAdd(String viewName) {
        return viewNameSet.add(viewName);
    }
}

package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.EventOfServer;
import timmax.tilegame.basemodel.protocol.EventOfServer11ConnectWithoutUserIdentify;
import timmax.tilegame.basemodel.protocol.EventOfServer92GameEvent;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.baseview.View;
import timmax.tilegame.transport.TransportOfServer;

import java.util.Map;

//  Автомат состояний клиента, работающий на сервере и учитывающий состояния удалённых клиентов.
public class RemoteClientStateAutomaton<ClientId> extends ClientStateAutomaton<IGameMatch> {
    private final ClientId clientId;

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
    // ToDo: Привести к единому виду взаимоиспользование:
    //       - на клиенте переменных типов TransportOfClient и LocalClientStateAutomaton,
    //       - на сервере переменных типов TransportOfServer и RemoteClientStateAutomaton.
    //       Переменные типов TransportOfClient и LocalClientStateAutomaton на клиенте на одном уровне.
    //       А для сервера переменная типа TransportOfServer входит в состав RemoteClientStateAutomaton.
    private final TransportOfServer multiGameWebSocketServer;

    public RemoteClientStateAutomaton(
            ClientId clientId,
            IFabricOfRemoteClientStates<ClientId> fabricOfClientStatesForServer,
            TransportOfServer multiGameWebSocketServer) {
        super(fabricOfClientStatesForServer);
        this.clientId = clientId;
        this.multiGameWebSocketServer = multiGameWebSocketServer;
        changeStateFrom01To02_();
    }

    public ClientId getClientId() {
        return clientId;
    }

    void sendEventOfServer(ClientId clientId, EventOfServer eventOfServer) {
        multiGameWebSocketServer.sendEventOfServer(clientId, eventOfServer);
    }

    public void sendGameEventToAllViews(
            GameEvent gameEvent,
            Map<String, Class<? extends View>> viewName_ViewClassMap) {
        for (String viewName : viewName_ViewClassMap.keySet()) {
            EventOfServer eventOfServer = new EventOfServer92GameEvent(viewName, gameEvent);
            sendEventOfServer(getClientId(), eventOfServer);
        }
    }

    @Override
    protected final void changeStateFrom01To02_() {
        super.changeStateFrom01To02_();
        //  ToDo:   Переместить весь код ниже в doAfterTurnOn() в серверный класс.
        setGameTypeSet(GameTypeFabric.GAME_TYPE_SET);
        sendEventOfServer(
                clientId,
                new EventOfServer11ConnectWithoutUserIdentify(getGameTypeSet())
        );
    }
}

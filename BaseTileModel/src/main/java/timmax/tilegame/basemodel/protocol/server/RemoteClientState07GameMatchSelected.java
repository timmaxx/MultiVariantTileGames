package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState07GameMatchSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.baseview.View;
import timmax.tilegame.transport.TransportOfServer;

import java.lang.reflect.Constructor;

public class RemoteClientState07GameMatchSelected<ClientId> extends ClientState07GameMatchSelected<IModelOfServer, ClientId> {
    public RemoteClientState07GameMatchSelected(ClientStateAutomaton<IModelOfServer, ClientId> clientStateAutomaton, TransportOfServer<ClientId> transportOfServer, ClientId clientId) {
        super(clientStateAutomaton, transportOfServer, clientId);
    }

    // ---- 6 Конкретная партия игры
    @Override
    public void forgetServerBaseModel() {
        super.forgetServerBaseModel();
        getTransportOfServer().sendEventOfServer(getClientId(), new EventOfServer60ForgetGameMatch());
    }

    // ---- 7
    @Override
    public void setGameIsPlaying(Boolean gameIsPlaying) {
        super.setGameIsPlaying(gameIsPlaying);
        getTransportOfServer().sendEventOfServer(getClientId(), new EventOfServer71GameMatchIsPlaying());
        // ToDo: Вызов этого метода может быть как для модели:
        //       - для которой ранее ещё не было вызвано createNewGame()
        //       - так и для той, у которой был вызов createNewGame(), но потом она была поставлена на паузу.
        serverBaseModel.createNewGame();
    }

    @Override
    public Constructor<? extends View> getViewConstructor(Class<? extends View> classOfView) {
        throw new RuntimeException("Not available for this class!");
    }
}

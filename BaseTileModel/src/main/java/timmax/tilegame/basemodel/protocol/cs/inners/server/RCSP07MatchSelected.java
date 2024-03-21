package timmax.tilegame.basemodel.protocol.cs.inners.server;

import timmax.tilegame.basemodel.protocol.EventOfServer60ForgetGameMatch;
import timmax.tilegame.basemodel.protocol.EventOfServer71GameMatchIsPlaying;
import timmax.tilegame.basemodel.protocol.cs.inners.CSP07MatchSelected;
import timmax.tilegame.basemodel.protocol.cs.inners.ClientState;
import timmax.tilegame.basemodel.protocol.server.IModelOfServer;
import timmax.tilegame.transport.TransportOfServer;

public class RCSP07MatchSelected<ClientId> extends CSP07MatchSelected<IModelOfServer> {
    private final ClientId clientId;
    private final TransportOfServer<ClientId> transportOfServer;

    public RCSP07MatchSelected(ClientState<IModelOfServer> clientState, ClientId clientId, TransportOfServer<ClientId> transportOfServer) {
        super(clientState);
        this.clientId = clientId;
        this.transportOfServer = transportOfServer;
    }

    @Override
    public void forgetServerBaseModel() {
        super.forgetServerBaseModel();
        transportOfServer.sendEventOfServer(clientId, new EventOfServer60ForgetGameMatch());
    }

    @Override
    public void setGameIsPlaying(Boolean gameIsPlaying) {
        super.setGameIsPlaying(gameIsPlaying);
        transportOfServer.sendEventOfServer(clientId, new EventOfServer71GameMatchIsPlaying());
        // ToDo: Вызов этого метода может быть как для модели:
        //       - для которой ранее ещё не было вызвано createNewGame()
        //       - так и для той, у которой был вызов createNewGame(), но потом она была поставлена на паузу.
        // serverBaseModel.createNewGame();
        clientState.getServerBaseModel().createNewGame();
    }
}

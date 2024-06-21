package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState07GameMatchSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

public class RemoteClientState07GameMatchSelected<ClientId> extends ClientState07GameMatchSelected<IModelOfServer> {
    public RemoteClientState07GameMatchSelected(ClientStateAutomaton<IModelOfServer> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // class AbstractClientState
    // ---- 6 Конкретная партия игры
    @Override
    public void forgetServerBaseModel() {
        super.forgetServerBaseModel();
        getClientStateAutomaton().getTransportOfServer().sendEventOfServer(
                getClientStateAutomaton().getClientId(),
                new EventOfServer60ForgetGameMatch()
        );
    }

    // ---- 7
    @Override
    public void setGameIsPlaying(Boolean gameIsPlaying) {
        super.setGameIsPlaying(gameIsPlaying);
        getClientStateAutomaton().getTransportOfServer().sendEventOfServer(
                getClientStateAutomaton().getClientId(),
                new EventOfServer71GameMatchIsPlaying()
        );
        // ToDo: Вызов этого метода может быть как для модели:
        //       - для которой ранее ещё не было вызвано createNewGame()
        //       - так и для той, у которой был вызов createNewGame(), но потом она была поставлена на паузу.
        serverBaseModel.createNewGame();
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton<ClientId> getClientStateAutomaton() {
        return (RemoteClientStateAutomaton<ClientId>) (super.getClientStateAutomaton());
    }
}

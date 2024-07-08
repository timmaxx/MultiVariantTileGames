package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState07GameMatchSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

public class RemoteClientState07GameMatchSelected<ClientId> extends ClientState07GameMatchSelected<IGameMatch> {
    private final ClientId clientId;

    public RemoteClientState07GameMatchSelected(ClientStateAutomaton<IGameMatch> clientStateAutomaton, ClientId clientId) {
        super(clientStateAutomaton);
        this.clientId = clientId;
    }

    // class ClientState07GameMatchSelected
    // ---- 6 Конкретная партия игры
    @Override
    public void forgetGameMatchX() {
        super.forgetGameMatchX();
        getClientStateAutomaton().getTransportOfServer().sendEventOfServer(
                clientId,
                new EventOfServer60ForgetGameMatch()
        );
    }

    // ---- 7
    @Override
    public void setGameMatchPlaying(Boolean gameIsPlaying) {
        super.setGameMatchPlaying(gameIsPlaying);
        getClientStateAutomaton().getTransportOfServer().sendEventOfServer(
                clientId,
                new EventOfServer71SetGameMatchPlaying()
        );
        // ToDo: Вызов этого метода может быть как для модели:
        //       - для которой ранее ещё не было вызвано createNewGame()
        //       - так и для той, у которой был вызов createNewGame(), но потом она была поставлена на паузу.
        // ToDo: Не предполагалось, что обращение к getClientStateAutomaton().getServerBaseModel0()
        //       понадобиться где-то, кроме ClientState0X..., но здесь понадобилось.
        //       Пришлось сделать public, но нужно вернуть package-private.
        getClientStateAutomaton().getGameMatchX0().createNewGame();
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton<ClientId> getClientStateAutomaton() {
        return (RemoteClientStateAutomaton<ClientId>) (super.getClientStateAutomaton());
    }
}

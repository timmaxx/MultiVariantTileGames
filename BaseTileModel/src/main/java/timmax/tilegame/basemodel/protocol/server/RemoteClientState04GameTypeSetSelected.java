package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState04GameTypeSetSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

public class RemoteClientState04GameTypeSetSelected<ClientId> extends ClientState04GameTypeSetSelected<IGameMatch> {
    private final ClientId clientId;

    public RemoteClientState04GameTypeSetSelected(ClientStateAutomaton<IGameMatch> clientStateAutomaton, ClientId clientId) {
        super(clientStateAutomaton);
        this.clientId = clientId;
    }

    // class ClientState04GameTypeSetSelected
    // ---- 3 (Список типов игр)
    @Override
    public void forgetGameTypeSet() {
        super.forgetGameTypeSet();
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer30ForgetGameTypeSet()
        );
    }

    // ---- 4 (Конкретный тип игры)
    @Override
    public void setGameType(GameType gameType) {
        super.setGameType(gameType);
        if (gameType == null) {
            getClientStateAutomaton().sendEventOfServer(
                    clientId,
                    new EventOfServer40ForgetGameType()
            );
            return;
        }
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer41SetGameType(gameType.getGameName())
        );
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton getClientStateAutomaton() {
        return (RemoteClientStateAutomaton) (super.getClientStateAutomaton());
    }
}

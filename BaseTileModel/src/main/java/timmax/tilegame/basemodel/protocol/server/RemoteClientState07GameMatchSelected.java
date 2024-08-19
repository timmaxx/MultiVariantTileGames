package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState07GameMatchSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchDto;

public class RemoteClientState07GameMatchSelected<ClientId> extends ClientState07GameMatchSelected<IGameMatch> {
    private final ClientId clientId;

    public RemoteClientState07GameMatchSelected(ClientStateAutomaton<IGameMatch> clientStateAutomaton, ClientId clientId) {
        super(clientStateAutomaton);
        this.clientId = clientId;
    }

    @Override
    public void setGameMatchX(IGameMatch gameMatchX) {
        super.setGameMatchX(gameMatchX);
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer61SetGameMatch(
                        new GameMatchDto(
                                gameMatchX.getId(),
                                gameMatchX.isPlaying(),
                                gameMatchX.getParamsOfModelValueMap()
                        )
                )
        );
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton getClientStateAutomaton() {
        return (RemoteClientStateAutomaton) (super.getClientStateAutomaton());
    }
}

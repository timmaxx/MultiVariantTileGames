package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState07GameMatchWasSet;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchDto;

public class RemoteClientState07GameMatchWasSet<ClientId> extends ClientState07GameMatchWasSet<IGameMatch> {
    public RemoteClientState07GameMatchWasSet(ClientStateAutomaton<IGameMatch> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    @Override
    public void doAfterTurnOn() {
        getClientStateAutomaton().sendEventOfServer(
                getClientStateAutomaton().getClientId(),
                new EventOfServer61SetGameMatch(
                        new GameMatchDto(
                                getClientStateAutomaton().getGameMatchX().getId(),
                                getClientStateAutomaton().getGameMatchX().getStatus(),
                                getClientStateAutomaton().getGameMatchX().getParamsOfModelValueMap()
                        )
                )
        );
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton<ClientId> getClientStateAutomaton() {
        return (RemoteClientStateAutomaton<ClientId>) (super.getClientStateAutomaton());
    }
}

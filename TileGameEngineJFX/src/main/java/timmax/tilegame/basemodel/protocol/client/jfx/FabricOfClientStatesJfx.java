package timmax.tilegame.basemodel.protocol.client.jfx;

import timmax.tilegame.basemodel.protocol.client.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

public class FabricOfClientStatesJfx implements IFabricOfClientStates<GameMatchDto> {
    @Override
    public LocalClientState01NoConnect getClientState01NoConnect(ClientStateAutomaton<GameMatchDto> clientStateAutomaton) {
        return new LocalClientState01NoConnect(clientStateAutomaton);
    }

    @Override
    public LocalClientState02ConnectNonIdent getClientState02ConnectNonIdent(ClientStateAutomaton<GameMatchDto> clientStateAutomaton) {
        return new LocalClientState02ConnectNonIdent(clientStateAutomaton);
    }

    @Override
    public LocalClientState04UserWasAuthorized getClientState04UserWasAuthorized(ClientStateAutomaton<GameMatchDto> clientStateAutomaton) {
        return new LocalClientState04UserWasAuthorized(clientStateAutomaton);
    }

    @Override
    public LocalClientState06GameTypeWasSet getClientState06GameTypeWasSet(ClientStateAutomaton<GameMatchDto> clientStateAutomaton) {
        return new LocalClientState06GameTypeWasSet(clientStateAutomaton);
    }

    @Override
    public LocalClientState07GameMatchWasSet getClientState07GameMatchSelected(ClientStateAutomaton<GameMatchDto> clientStateAutomaton) {
        return new LocalClientState07GameMatchWasSet(clientStateAutomaton);
    }

    @Override
    public LocalClientState08GameMatchIsPlaying getClientState08GameMatchIsPlaying(ClientStateAutomaton<GameMatchDto> clientStateAutomaton) {
        return new LocalClientState08GameMatchIsPlaying(clientStateAutomaton);
    }
}

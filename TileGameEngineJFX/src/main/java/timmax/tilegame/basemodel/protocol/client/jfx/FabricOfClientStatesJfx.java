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
    public LocalClientState04UserAuthorized getClientState04UserAuthorized(ClientStateAutomaton<GameMatchDto> clientStateAutomaton) {
        return new LocalClientState04UserAuthorized(clientStateAutomaton);
    }

    @Override
    public LocalClientState06GameMatchSetSelected getClientState06GameMatchSetSelected(ClientStateAutomaton<GameMatchDto> clientStateAutomaton) {
        return new LocalClientState06GameMatchSetSelected(clientStateAutomaton);
    }

    @Override
    public LocalClientState07GameMatchSelected getClientState07GameMatchSelected(ClientStateAutomaton<GameMatchDto> clientStateAutomaton) {
        return new LocalClientState07GameMatchSelected(clientStateAutomaton);
    }

    @Override
    public LocalClientState08GameMatchIsPlaying getClientState08GameMatchIsPlaying(ClientStateAutomaton<GameMatchDto> clientStateAutomaton) {
        return new LocalClientState08GameMatchIsPlaying(clientStateAutomaton);
    }
}

package timmax.tilegame.basemodel.protocol.client.jfx;

import timmax.tilegame.basemodel.protocol.client.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

public class FabricOfClientStatesJfx implements IFabricOfClientStates<GameMatchId> {
    @Override
    public LocalClientState01NoConnect getClientState01NoConnect(ClientStateAutomaton<GameMatchId> clientStateAutomaton) {
        return new LocalClientState01NoConnect(clientStateAutomaton);
    }

    @Override
    public LocalClientState02ConnectNonIdent getClientState02ConnectNonIdent(ClientStateAutomaton<GameMatchId> clientStateAutomaton) {
        return new LocalClientState02ConnectNonIdent(clientStateAutomaton);
    }

    @Override
    public LocalClientState03ConnectAuthorized getClientState03ConnectAuthorized(ClientStateAutomaton<GameMatchId> clientStateAutomaton) {
        return new LocalClientState03ConnectAuthorized(clientStateAutomaton);
    }

    @Override
    public LocalClientState04GameTypeSetSelected getClientState04GameTypeSetSelected(ClientStateAutomaton<GameMatchId> clientStateAutomaton) {
        return new LocalClientState04GameTypeSetSelected(clientStateAutomaton);
    }

    @Override
    public LocalClientState05GameTypeSelected getClientState05GameTypeSelected(ClientStateAutomaton<GameMatchId> clientStateAutomaton) {
        return new LocalClientState05GameTypeSelected(clientStateAutomaton);
    }

    @Override
    public LocalClientState06GameMatchSetSelected getClientState06GameMatchSetSelected(ClientStateAutomaton<GameMatchId> clientStateAutomaton) {
        return new LocalClientState06GameMatchSetSelected(clientStateAutomaton);
    }

    @Override
    public LocalClientState07GameMatchSelected getClientState07GameMatchSelected(ClientStateAutomaton<GameMatchId> clientStateAutomaton) {
        return new LocalClientState07GameMatchSelected(clientStateAutomaton);
    }

    @Override
    public LocalClientState08GameMatchPlaying getClientState08GameMatchPlaying(ClientStateAutomaton<GameMatchId> clientStateAutomaton) {
        return new LocalClientState08GameMatchPlaying(clientStateAutomaton);
    }
}

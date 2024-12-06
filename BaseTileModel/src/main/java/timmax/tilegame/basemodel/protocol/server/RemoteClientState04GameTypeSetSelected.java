package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState04GameTypeSetSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

public class RemoteClientState04GameTypeSetSelected<ClientId> extends ClientState04GameTypeSetSelected<IGameMatch> {
    public RemoteClientState04GameTypeSetSelected(ClientStateAutomaton<IGameMatch> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // class AbstractClientState
    @Override
    public void doAfterTurnOn() {
        getClientStateAutomaton().sendEventOfServer(
                getClientStateAutomaton().getClientId(),
                new EventOfServer21IdentifyAuthenticateAuthorizeUser(getClientStateAutomaton().getUser().getUserName())
        );
    }

    @Override
    public RemoteClientStateAutomaton<ClientId> getClientStateAutomaton() {
        return (RemoteClientStateAutomaton<ClientId>) (super.getClientStateAutomaton());
    }
}

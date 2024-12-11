package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

public class RemoteClientState02ConnectNonIdent<ClientId> extends ClientState02ConnectNonIdent<IGameMatch> {
    public RemoteClientState02ConnectNonIdent(ClientStateAutomaton<IGameMatch> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // class AbstractClientState
    @Override
    public void doAfterTurnOn() {
        getBaseStateAutomaton().setGameTypeSet(GameTypeFabric.GAME_TYPE_SET);
        getBaseStateAutomaton().sendEventOfServer(
                getBaseStateAutomaton().getClientId(),
                new EventOfServer11ConnectWithoutUserIdentify(getBaseStateAutomaton().getGameTypeSet())
        );
    }

    @Override
    public RemoteClientStateAutomaton<ClientId> getBaseStateAutomaton() {
        //  Warning:(23, 16) Unchecked cast: 'timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton' to 'timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton<ClientId>'
        return (RemoteClientStateAutomaton<ClientId>)(super.getBaseStateAutomaton());
    }
}

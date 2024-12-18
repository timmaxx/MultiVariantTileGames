package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

public class RemoteClientState02ConnectWithoutServerInfo<ClientId> extends ClientState02ConnectWithoutServerInfo<IGameMatch> {
    public RemoteClientState02ConnectWithoutServerInfo(ClientStateAutomaton<IGameMatch> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    //  class State
    @Override
    public void doAfterTurnOn() {
        getBaseStateAutomaton().setGameTypeSet(GameTypeFabric.GAME_TYPE_SET);
        getBaseStateAutomaton().getTransportOfServer().sendEventOfServer(
                getBaseStateAutomaton().getClientId(),
                new EventOfServer11ConnectWithoutUserIdentify(getBaseStateAutomaton().getGameTypeSet())
        );
    }

    //  class ClientState
    @Override
    public RemoteClientStateAutomaton<ClientId> getBaseStateAutomaton() {
        //  Warning:(23, 16) Unchecked cast: 'timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton' to 'timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton<ClientId>'
        return (RemoteClientStateAutomaton<ClientId>)(super.getBaseStateAutomaton());
    }
}

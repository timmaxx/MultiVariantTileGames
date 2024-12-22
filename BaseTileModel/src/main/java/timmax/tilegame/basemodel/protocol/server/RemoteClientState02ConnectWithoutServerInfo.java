package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

public class RemoteClientState02ConnectWithoutServerInfo extends ClientState02ConnectWithoutServerInfo {
    public RemoteClientState02ConnectWithoutServerInfo(ClientStateAutomaton clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    //  class State
    @Override
    public void doAfterTurnOn() {
        getBaseStateAutomaton().setGameTypeSet(GameTypeFabric.GAME_TYPE_SET);
        getBaseStateAutomaton().getSenderOfEventOfServer().sendEventOfServer(
                getBaseStateAutomaton().getWebSocket(),
                new EventOfServer11ConnectWithoutUserIdentify(getBaseStateAutomaton().getGameTypeSet())
        );
    }

    //  class ClientState
    @Override
    public RemoteClientStateAutomaton getBaseStateAutomaton() {
        return (RemoteClientStateAutomaton)(super.getBaseStateAutomaton());
    }
}

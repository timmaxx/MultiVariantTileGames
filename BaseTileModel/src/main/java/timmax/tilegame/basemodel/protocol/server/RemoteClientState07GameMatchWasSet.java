package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState07GameMatchWasSet;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchDto;

public class RemoteClientState07GameMatchWasSet extends ClientState07GameMatchWasSet {
    public RemoteClientState07GameMatchWasSet(ClientStateAutomaton clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    //  class State
    @Override
    public void doAfterTurnOn() {
        getBaseStateAutomaton().getSenderOfEventOfServer().sendEventOfServer(
                getBaseStateAutomaton().getWebSocket(),
                new EventOfServer61SetGameMatch(
                        new GameMatchDto(
                                getBaseStateAutomaton().getGameMatchX().getId(),
                                getBaseStateAutomaton().getGameMatchX().getStatus(),
                                getBaseStateAutomaton().getGameMatchX().getParamsOfModelValueMap()
                        )
                )
        );
    }

    //  class ClientState
    @Override
    public RemoteClientStateAutomaton getBaseStateAutomaton() {
        return (RemoteClientStateAutomaton) (super.getBaseStateAutomaton());
    }
}

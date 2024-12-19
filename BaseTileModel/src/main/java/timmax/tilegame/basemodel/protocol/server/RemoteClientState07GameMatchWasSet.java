package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState07GameMatchWasSet;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchDto;

public class RemoteClientState07GameMatchWasSet<ClientId> extends ClientState07GameMatchWasSet<IGameMatch> {
    public RemoteClientState07GameMatchWasSet(ClientStateAutomaton<IGameMatch> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    //  class State
    @Override
    public void doAfterTurnOn() {
        getBaseStateAutomaton().getTransportOfServer().sendEventOfServer(
                getBaseStateAutomaton().getClientId(),
                new EventOfServer61SetGameMatch(
                        new GameMatchDto(
                                getBaseStateAutomaton().getGameMatchX().getId(),
                                getBaseStateAutomaton().getGameMatchX().getStatus(),
                                //  Warning:(21, 33) Unchecked assignment: 'java.util.Map' to 'java.util.Map<java.lang.String,java.lang.Integer>'. Reason: 'getBaseStateAutomaton().getGameMatchX()' has raw type, so result of getParamsOfModelValueMap is erased
                                getBaseStateAutomaton().getGameMatchX().getParamsOfModelValueMap()
                        )
                )
        );
    }

    //  class ClientState
    @Override
    public RemoteClientStateAutomaton getBaseStateAutomaton() {
        //  Warning:(30, 16) Unchecked cast: 'timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton' to 'timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton<ClientId>'
        return (RemoteClientStateAutomaton) (super.getBaseStateAutomaton());
    }
}

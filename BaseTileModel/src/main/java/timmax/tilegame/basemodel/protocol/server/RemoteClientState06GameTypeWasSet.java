package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState06GameTypeWasSet;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

import java.util.stream.Collectors;

public class RemoteClientState06GameTypeWasSet extends ClientState06GameTypeWasSet {
    public RemoteClientState06GameTypeWasSet(ClientStateAutomaton clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    //  class State
    @Override
    public void doAfterTurnOn() {
        getBaseStateAutomaton().getSenderOfEventOfServer().sendEventOfServer(
                getBaseStateAutomaton().getWebSocket(),
                new EventOfServer41SetGameType(
                        getBaseStateAutomaton().getGameType().getId(),
                        getBaseStateAutomaton().getGameType().getGameMatchXSet()
                                .stream()
                                //  ToDo:   Избавиться от приведения типа.
                                .map(x -> ((GameMatch) x).getGameMatchDto())
                                .collect(Collectors.toSet())
                )
        );
    }

    //  class ClientState
    @Override
    public RemoteClientStateAutomaton getBaseStateAutomaton() {
        return (RemoteClientStateAutomaton) (super.getBaseStateAutomaton());
    }
}

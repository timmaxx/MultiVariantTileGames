package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState06GameTypeWasSet;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchDto;

import java.util.Set;
import java.util.stream.Collectors;

public class RemoteClientState06GameTypeWasSet<ClientId> extends ClientState06GameTypeWasSet<IGameMatch> {
    public RemoteClientState06GameTypeWasSet(ClientStateAutomaton<IGameMatch> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // class AbstractClientState
    @Override
    public void doAfterTurnOn() {
        getClientStateAutomaton().sendEventOfServer(
                getClientStateAutomaton().getClientId(),
                new EventOfServer41SetGameType(
                        getClientStateAutomaton().getGameType().getId(),
                        //  ToDo:   Избавиться от приведения типа.
                        (Set<GameMatchDto>) getClientStateAutomaton().getGameType().getGameMatchXSet()
                                .stream()
                                //  ToDo:   Избавиться от приведения типа.
                                .map(x -> ((GameMatch) x).getGameMatchDto())
                                .collect(Collectors.toSet())
                )
        );
    }

    @Override
    public RemoteClientStateAutomaton<ClientId> getClientStateAutomaton() {
        return (RemoteClientStateAutomaton<ClientId>) (super.getClientStateAutomaton());
    }
}

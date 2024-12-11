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
        getBaseStateAutomaton().sendEventOfServer(
                getBaseStateAutomaton().getClientId(),
                new EventOfServer41SetGameType(
                        getBaseStateAutomaton().getGameType().getId(),
                        //  ToDo:   Избавиться от приведения типа.
                        //  <GameMatchX extends IGameMatchX>
                        //  public Set<GameMatchX> getGameMatchXSet()
                        (Set<GameMatchDto>) getBaseStateAutomaton().getGameType().getGameMatchXSet()
                                .stream()
                                //  ToDo:   Избавиться от приведения типа.
                                .map(x -> ((GameMatch) x).getGameMatchDto())
                                .collect(Collectors.toSet())
                )
        );
    }

    @Override
    public RemoteClientStateAutomaton<ClientId> getBaseStateAutomaton() {
        //  Warning:(37, 16) Unchecked cast: 'timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton' to 'timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton<ClientId>'
        return (RemoteClientStateAutomaton<ClientId>) (super.getBaseStateAutomaton());
    }
}

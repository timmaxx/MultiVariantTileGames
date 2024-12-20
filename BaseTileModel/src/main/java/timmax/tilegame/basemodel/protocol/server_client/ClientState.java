package timmax.tilegame.basemodel.protocol.server_client;

import timmax.state.State;
import timmax.state.WrongMethodInvokeForCurrentStateException;
import timmax.tilegame.basemodel.GameMatchStatus;
import timmax.tilegame.basemodel.protocol.server.GameType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public abstract class ClientState<GameMatchX extends IGameMatchX>
        extends State implements IClientState99<GameMatchX> {
    protected static final Logger logger = LoggerFactory.getLogger(ClientState.class);

    public ClientState(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    public ClientStateAutomaton<GameMatchX> getBaseStateAutomaton() {
        //  Warning:(22, 16) Unchecked cast: 'timmax.state.StateAutomaton' to 'timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton<GameMatchX>'
        return (ClientStateAutomaton<GameMatchX>) super.getBaseStateAutomaton();
    }

    // interface IClientState01NoConnect
    @Override
    public void connect() {
        throw new WrongMethodInvokeForCurrentStateException(getBaseStateAutomaton().getCurrentState());
    }

    // interface IClientState02ConnectNonIdent
    @Override
    public void close() {
        // Нет кода, т.к. переход в состояние 01NoConnect пусть будет доступен всегда
        // (в т.ч. и из 01NoConnect)
    }

    @Override
    public void setGameTypeSet(Set<GameType> gameTypeSet) {
        throw new WrongMethodInvokeForCurrentStateException(getBaseStateAutomaton().getCurrentState());
    }

    @Override
    public void authorizeUser(String userName) {
        throw new WrongMethodInvokeForCurrentStateException(getBaseStateAutomaton().getCurrentState());
    }

    @Override
    public void reauthorizeUser() {
        throw new WrongMethodInvokeForCurrentStateException(getBaseStateAutomaton().getCurrentState());
    }

    // interface IClientState04GameTypeSetSelected
    @Override
    public Set<GameType> getGameTypeSet() {
        throw new WrongMethodInvokeForCurrentStateException(getBaseStateAutomaton().getCurrentState());
    }

    @Override
    public void setGameType(GameType gameType) {
        throw new WrongMethodInvokeForCurrentStateException(getBaseStateAutomaton().getCurrentState());
    }

    // interface IClientState06GameMatchSetSelected
    @Override
    public GameType getGameType() {
        throw new WrongMethodInvokeForCurrentStateException(getBaseStateAutomaton().getCurrentState());
    }

    @Override
    public void resetGameType() {
        throw new WrongMethodInvokeForCurrentStateException(getBaseStateAutomaton().getCurrentState());
    }

    @Override
    public Set<GameMatchX> getGameMatchXSet() {
        throw new WrongMethodInvokeForCurrentStateException(getBaseStateAutomaton().getCurrentState());
    }

    @Override
    public void setGameMatchX(GameMatchX gameMatchX) {
        throw new WrongMethodInvokeForCurrentStateException(getBaseStateAutomaton().getCurrentState());
    }

    // interface IClientState07GameMatchSelected
    @Override
    public void resetGameMatch() {
        throw new WrongMethodInvokeForCurrentStateException(getBaseStateAutomaton().getCurrentState());
    }

    @Override
    public GameMatchX getGameMatchX() {
        throw new WrongMethodInvokeForCurrentStateException(getBaseStateAutomaton().getCurrentState());
    }

    @Override
    public void startGameMatch(GameMatchExtendedDto gameMatchExtendedDto) {
        throw new WrongMethodInvokeForCurrentStateException(getBaseStateAutomaton().getCurrentState());
    }

    // interface IClientState08GameMatchPlaying
    @Override
    public GameMatchStatus getGameMatchStatus() {
        throw new WrongMethodInvokeForCurrentStateException(getBaseStateAutomaton().getCurrentState());
    }

    // class Object
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientState<?> that = (ClientState<?>) o;

        return getBaseStateAutomaton().equals(that.getBaseStateAutomaton());
    }

    @Override
    public int hashCode() {
        return getBaseStateAutomaton().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}

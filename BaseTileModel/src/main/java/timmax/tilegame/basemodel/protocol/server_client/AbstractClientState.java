package timmax.tilegame.basemodel.protocol.server_client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timmax.tilegame.basemodel.GameMatchStatus;
import timmax.tilegame.basemodel.exception.WrongMethodInvokeForCurrentStateException;
import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

public abstract class AbstractClientState<GameMatchX extends IGameMatchX> implements IClientState99<GameMatchX> {
    protected static final Logger logger = LoggerFactory.getLogger(AbstractClientState.class);

    private final ClientStateAutomaton<GameMatchX> clientStateAutomaton;

    public AbstractClientState(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        this.clientStateAutomaton = clientStateAutomaton;
    }

    protected ClientStateAutomaton<GameMatchX> getClientStateAutomaton() {
        return clientStateAutomaton;
    }

    @Override
    public void doBeforeTurnOff() {
    }

    @Override
    public void doAfterTurnOn() {
    }

    // interface IClientState01NoConnect
    @Override
    public void connect() {
        throw new WrongMethodInvokeForCurrentStateException(getClientStateAutomaton().getCurrentState());
    }

    // interface IClientState02ConnectNonIdent
    @Override
    public void close() {
        // Нет кода, т.к. переход в состояние 01NoConnect пусть будет доступен всегда
        // (в т.ч. и из 01NoConnect)
    }

    @Override
    public void authorizeUser(String userName) {
        throw new WrongMethodInvokeForCurrentStateException(getClientStateAutomaton().getCurrentState());
    }

    @Override
    public void reauthorizeUser() {
        throw new WrongMethodInvokeForCurrentStateException(getClientStateAutomaton().getCurrentState());
    }

    // interface IClientState04GameTypeSetSelected
    @Override
    public Set<GameType> getGameTypeSet() {
        throw new WrongMethodInvokeForCurrentStateException(getClientStateAutomaton().getCurrentState());
    }

    @Override
    public void setGameType(GameType gameType) {
        throw new WrongMethodInvokeForCurrentStateException(getClientStateAutomaton().getCurrentState());
    }

    // interface IClientState06GameMatchSetSelected
    @Override
    public GameType getGameType() {
        throw new WrongMethodInvokeForCurrentStateException(getClientStateAutomaton().getCurrentState());
    }

    @Override
    public void reselectGameType() {
        throw new WrongMethodInvokeForCurrentStateException(getClientStateAutomaton().getCurrentState());
    }

    @Override
    public Set<GameMatchX> getGameMatchXSet() {
        throw new WrongMethodInvokeForCurrentStateException(getClientStateAutomaton().getCurrentState());
    }

    @Override
    public void selectGameMatchX(GameMatchX gameMatchX) {
        throw new WrongMethodInvokeForCurrentStateException(getClientStateAutomaton().getCurrentState());
    }

    // interface IClientState07GameMatchSelected
    @Override
    public void reselectGameMatch() {
        throw new WrongMethodInvokeForCurrentStateException(getClientStateAutomaton().getCurrentState());
    }

    @Override
    public GameMatchX getGameMatchX() {
        throw new WrongMethodInvokeForCurrentStateException(getClientStateAutomaton().getCurrentState());
    }

    @Override
    public GameMatchExtendedDto startGameMatch(GameMatchExtendedDto gameMatchExtendedDto) {
        throw new WrongMethodInvokeForCurrentStateException(getClientStateAutomaton().getCurrentState());
    }

    // interface IClientState08GameMatchPlaying
    @Override
    public GameMatchStatus getGameMatchStatus() {
        throw new WrongMethodInvokeForCurrentStateException(getClientStateAutomaton().getCurrentState());
    }

    // class Object
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractClientState<?> that = (AbstractClientState<?>) o;

        return clientStateAutomaton.equals(that.clientStateAutomaton) /*&& getClass().equals(that.getClass())*/;
    }

    @Override
    public int hashCode() {
        return clientStateAutomaton.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}

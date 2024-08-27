package timmax.tilegame.basemodel.protocol.server_client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timmax.tilegame.basemodel.GameMatchStatus;
import timmax.tilegame.basemodel.protocol.server.GameMatch;
import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

public abstract class AbstractClientState implements IClientState99 {
    protected static final Logger logger = LoggerFactory.getLogger(AbstractClientState.class);

    private final ClientStateAutomaton clientStateAutomaton;

    public AbstractClientState(ClientStateAutomaton clientStateAutomaton) {
        this.clientStateAutomaton = clientStateAutomaton;
    }

    protected ClientStateAutomaton getClientStateAutomaton() {
        return clientStateAutomaton;
    }

    private void wrongCallInStateAutomaton() {
        logger.error("Current state is '{}'", getClientStateAutomaton().toString());
        logger.error("  In this state you cannot use method '{}()'!",
                Thread.currentThread().getStackTrace()[3].getMethodName()
        );
        throw new RuntimeException("Wrong call in state automaton (see log.error)!");
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
        wrongCallInStateAutomaton();
    }

    // interface IClientState02ConnectNonIdent
    @Override
    public void close() {
        // Нет кода, т.к. переход в состояние 01NoConnect пусть будет доступен всегда
        // (в т.ч. и из 01NoConnect)
    }

    @Override
    public void authorizeUser(String userName, Set<GameType> gameTypeSet) {
        wrongCallInStateAutomaton();
    }

    @Override
    public void reauthorizeUser() {
        wrongCallInStateAutomaton();
    }

    // interface IClientState04GameTypeSetSelected
    @Override
    public Set<GameType> getGameTypeSet() {
        wrongCallInStateAutomaton();
        return null;
    }

    @Override
    public void selectGameType(GameType gameType) {
        wrongCallInStateAutomaton();
    }

    // interface IClientState06GameMatchSetSelected
    @Override
    public GameType getGameType() {
        wrongCallInStateAutomaton();
        return null;
    }

    @Override
    public void reselectGameType() {
        wrongCallInStateAutomaton();
    }

    @Override
    public Set<GameMatch> getGameMatchSet() {
        wrongCallInStateAutomaton();
        return null;
    }

    @Override
    public void selectGameMatch(GameMatch gameMatch) {
        wrongCallInStateAutomaton();
    }

    // interface IClientState07GameMatchSelected
    @Override
    public void reselectGameMatch() {
        wrongCallInStateAutomaton();
    }

    @Override
    public GameMatch getGameMatch() {
        wrongCallInStateAutomaton();
        return null;
    }

    @Override
    public GameMatch startGameMatch(GameMatch gameMatch) {
        wrongCallInStateAutomaton();
        return null;
    }

    @Override
    public void resumeGameMatch() {
        wrongCallInStateAutomaton();
    }

    // interface IClientState08GameMatchPlaying
    @Override
    public GameMatchStatus getGameMatchStatus() {
        wrongCallInStateAutomaton();
        return null;
    }

    // class Object
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractClientState that = (AbstractClientState) o;

        return clientStateAutomaton.equals(that.clientStateAutomaton) /*&& getClass().equals(that.getClass())*/;
    }

    @Override
    public int hashCode() {
        return clientStateAutomaton.hashCode() /*+ getClass().hashCode()*/;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}

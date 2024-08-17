package timmax.tilegame.basemodel.protocol.server_client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Map;
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
    public void openConnectWithoutUserIdentify() {
        wrongCallInStateAutomaton();
    }

    // interface IClientState02ConnectNonIdent
    @Override
    public void closeConnect() {
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
    public void setGameType(GameType gameType, Set<GameMatchX> gameMatchXSet) {
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
    public Set<GameMatchX> getGameMatchXSet() {
        wrongCallInStateAutomaton();
        return null;
    }

    @Override
    public void setGameMatchX(GameMatchX gameMatchX) {
        wrongCallInStateAutomaton();
    }

    // interface IClientState07GameMatchSelected
    @Override
    public void reselectGameMatch() {
        wrongCallInStateAutomaton();
    }

    @Override
    public GameMatchX getGameMatchX() {
        wrongCallInStateAutomaton();
        return null;
    }

    @Override
    public void startGameMatch(int width, int height, Map<String, Integer> paramsOfModelValueMap) {
        wrongCallInStateAutomaton();
    }

    @Override
    public void resumeGameMatch() {
        wrongCallInStateAutomaton();
    }

    // interface IClientState08GameMatchPlaying
    @Override
    public Boolean getGameMatchIsPlaying() {
        wrongCallInStateAutomaton();
        return false;
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
        return clientStateAutomaton.hashCode() /*+ getClass().hashCode()*/;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}

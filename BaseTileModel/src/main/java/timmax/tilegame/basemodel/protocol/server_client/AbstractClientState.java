package timmax.tilegame.basemodel.protocol.server_client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server.ParamOfModelDescription;
import timmax.tilegame.baseview.View;

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
        logger.error("Current state is '{}'", getClientStateAutomaton().getCurrentState());
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
    public void changeStateTo02ConnectNonIdent() {
        wrongCallInStateAutomaton();
    }

    // interface IClientState02ConnectNonIdent
    @Override
    public void changeStateTo01NoConnect() {
        // Нет кода, т.к. переход в состояние 01NoConnect пусть будет доступен всегда
        // (в т.ч. и из 01NoConnect)
    }

    @Override
    public void identifyAuthenticateAuthorizeUser(String userName, Set<GameType> gameTypeSet) {
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
    public void setGameType(GameType gameType, Set<GameMatchX> gameMatchXES) {
        wrongCallInStateAutomaton();
    }

    // interface IClientState06GameMatchSetSelected
    @Override
    public Map<String, Class<? extends View>> getViewName_ViewClassMap() {
        wrongCallInStateAutomaton();
        return null;
    }

    @Override
    public String getGameTypeName() {
        wrongCallInStateAutomaton();
        return null;
    }

    @Override
    public Map<String, ParamOfModelDescription> getParamName_paramModelDescriptionMap() {
        wrongCallInStateAutomaton();
        return null;
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
    public GameMatchX getGameMatchX() {
        wrongCallInStateAutomaton();
        return null;
    }

    @Override
    public void forgetGameMatchX() {
        wrongCallInStateAutomaton();
    }

    @Override
    public void setGameMatchPlaying(Boolean gameIsPlaying) {
        wrongCallInStateAutomaton();
    }

    // interface IClientState08GameMatchPlaying
    @Override
    public Boolean getGameIsPlaying() {
        wrongCallInStateAutomaton();
        return false;
    }

    @Override
    public void forgetGameMatchPlaying() {
        wrongCallInStateAutomaton();
    }
}

package timmax.tilegame.basemodel.protocol.server_client;

import timmax.state.State;
import timmax.state.WrongMethodInvokeForCurrentStateException;
import timmax.tilegame.basemodel.GameMatchStatus;
import timmax.tilegame.basemodel.dto.BaseDto;
import timmax.tilegame.basemodel.dto.GameMatchExtendedDto;
import timmax.tilegame.basemodel.protocol.server.GameType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public abstract class ClientState extends State implements IClientState99 {
    protected static final Logger logger = LoggerFactory.getLogger(ClientState.class);

    public ClientState(ClientStateAutomaton clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    public ClientStateAutomaton getBaseStateAutomaton() {
        return (ClientStateAutomaton) super.getBaseStateAutomaton();
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
    public void authorizeUser(BaseDto userDtoId) {
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
    public Set<IGameMatchX> getGameMatchXSet() {
        throw new WrongMethodInvokeForCurrentStateException(getBaseStateAutomaton().getCurrentState());
    }

    @Override
    public void setGameMatchX(IGameMatchX gameMatchX) {
        throw new WrongMethodInvokeForCurrentStateException(getBaseStateAutomaton().getCurrentState());
    }

    // interface IClientState07GameMatchSelected
    @Override
    public void resetGameMatch() {
        throw new WrongMethodInvokeForCurrentStateException(getBaseStateAutomaton().getCurrentState());
    }

    @Override
    public IGameMatchX getGameMatchX() {
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

        ClientState that = (ClientState) o;

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

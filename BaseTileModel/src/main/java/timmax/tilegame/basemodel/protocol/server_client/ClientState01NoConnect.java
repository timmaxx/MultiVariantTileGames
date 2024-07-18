package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server.ParamOfModelDescription;
import timmax.tilegame.baseview.View;

import java.util.Map;
import java.util.Set;

public abstract class ClientState01NoConnect<GameMatchX extends IGameMatchX> extends AbstractClientState<GameMatchX> implements IClientState99<GameMatchX> {
    public ClientState01NoConnect(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    @Override
    public void setUser(String userName, Set<GameType> gameTypeSet) {

    }

    @Override
    public Set<GameType> getGameTypeSet() {
        return null;
    }

    @Override
    public void forgetUser() {

    }

    @Override
    public void setGameType(GameType gameType, Set<GameMatchX> gameMatchXES) {

    }

    @Override
    public Map<String, Class<? extends View>> getViewName_ViewClassMap() {
        return null;
    }

    @Override
    public String getGameTypeName() {
        return null;
    }

    @Override
    public Map<String, ParamOfModelDescription> getParamName_paramModelDescriptionMap() {
        return null;
    }

    @Override
    public Set<GameMatchX> getGameMatchXSet() {
        return null;
    }

    @Override
    public void forgetGameType() {

    }

    @Override
    public void setGameMatchX(GameMatchX gameMatchX) {

    }

    @Override
    public GameMatchX getGameMatchX() {
        return null;
    }

    @Override
    public void forgetGameMatchX() {

    }

    @Override
    public void setGameMatchPlaying(Boolean gameIsPlaying) {

    }

    @Override
    public Boolean getGameIsPlaying() {
        return null;
    }

    @Override
    public void forgetGameMatchPlaying() {

    }
}

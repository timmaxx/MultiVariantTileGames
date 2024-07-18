package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.ParamOfModelDescription;
import timmax.tilegame.baseview.View;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class ClientState06GameMatchSetSelected<GameMatchX extends IGameMatchX> extends ClientState04GameTypeSetSelected<GameMatchX> {
    public ClientState06GameMatchSetSelected(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState06GameMatchSetSelected
    @Override
    public Map<String, Class<? extends View>> getViewName_ViewClassMap() {
        return getClientStateAutomaton().getViewName_ViewClassMap();
    }

    @Override
    public String getGameTypeName() {
        return getClientStateAutomaton().getGameTypeName();
    }

    @Override
    public Map<String, ParamOfModelDescription> getParamName_paramModelDescriptionMap() {
        return getClientStateAutomaton().getParamName_paramModelDescriptionMap();
    }

    @Override
    public Set<GameMatchX> getGameMatchXSet() {
        return getClientStateAutomaton().getGameMatchXSet_();
    }

    @Override
    public void forgetGameType() {
        getClientStateAutomaton().setGameMatchXSet_(new HashSet<>());
    }

    @Override
    public void setGameMatchX(GameMatchX gameMatchX) {
        getClientStateAutomaton().setGameMatchX_(gameMatchX);
    }
}

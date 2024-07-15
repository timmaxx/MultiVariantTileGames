package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.ParamOfModelDescription;
import timmax.tilegame.baseview.View;

import java.util.Map;
import java.util.Set;

//  Выбран перечень партий (доигрываем ранее созданную или играем новую).
public interface IClientState06GameMatchSetSelected<GameMatchX extends IGameMatchX> extends IClientState00 {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:

    // ---- 6 (MatchSetSelected)
    Map<String, Class<? extends View>> getViewName_ViewClassMap();
    // ToDo: Переименовать в getGameTypeName()
    String getGameName();
    Map<String, ParamOfModelDescription> getParamName_paramModelDescriptionMap();

    Set<GameMatchX> getGameMatchXSet();

    void forgetGameMatchXSet();

    void setGameMatchX(GameMatchX gameMatchX);
}

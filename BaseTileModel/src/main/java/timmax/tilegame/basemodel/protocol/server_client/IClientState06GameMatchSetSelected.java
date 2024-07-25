package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.ParamOfModelDescription;

import java.util.Map;
import java.util.Set;

//  Выбран перечень партий (доигрываем ранее созданную или играем новую).
public interface IClientState06GameMatchSetSelected<GameMatchX extends IGameMatchX> extends IClientState00 {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:

    // ---- 6 (MatchSetSelected)
    String getGameTypeName();
    Map<String, ParamOfModelDescription> getParamName_paramModelDescriptionMap();

    void resetGameType();
    Set<GameMatchX> getGameMatchXSet();
    void setGameMatchX(GameMatchX gameMatchX);
}

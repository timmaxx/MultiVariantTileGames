package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

//  Выбран перечень партий (доигрываем ранее созданную или играем новую).
public interface IClientState06GameMatchSetSelected<GameMatchX extends IGameMatchX> extends IClientState00 {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:

    // ---- 6 (MatchSetSelected)
    // ToDo: Метод был добавлен, что-бы добраться до описания типа,
    //       но если нужно только описание, то м.б. другой метод сделать?
    GameType getGameType();

    void reselectGameType();
    Set<GameMatchX> getGameMatchXSet();
    void setGameMatchX(GameMatchX gameMatchX);
}

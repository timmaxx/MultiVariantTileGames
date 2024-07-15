package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

//  Выбран перечень партий (доигрываем ранее созданную или играем новую).
public interface IClientState06GameMatchSetSelected<Model> extends IClientState00 {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:

    // ---- 6 (MatchSetSelected)
    // Перенесён из IClientState05GameTypeSelected
    // ToDo: Проверить нужность этого метода. Ведь информация о типе игры идёт от клиента на сервер,
    //       а сервер хоть и возвращает тип игры, но клиент использует ведь перечень матчей.
    //       Ну и дополнительный аргумент: в процессе идентификации-аутентификации-авторизации от клиента также идёт
    //       информация о пользователе, сервер возвращает имя пользователя и перечень типов игр,
    //       который потом и используется (а не имя пользователя).
    GameType getGameType();
    Set<Model> getGameMatchXSet();

    void forgetGameMatchXSet();

    void setGameMatchX(Model gameMatchX);
}

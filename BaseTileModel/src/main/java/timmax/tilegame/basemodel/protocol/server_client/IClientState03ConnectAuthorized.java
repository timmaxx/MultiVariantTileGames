package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

//  Сервер идентифицировал, аутентифицировал и авторизовал пользователя.
public interface IClientState03ConnectAuthorized extends IClientState00 {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:

    // ---- 3 ConnectAuthorized
    String getUserName();

    void forgetUser(); // logOut

    void setGameTypeSet(Set<GameType> gameTypeSet);
}

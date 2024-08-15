package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

//  Подключение есть, но сервер не идентифицировал, не аутентифицировал и не авторизовал пользователя.
public interface IClientState02ConnectNonIdent extends IClientState00 {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:

    // ---- 2 ConnectNonIdent
    void authorizeUser(String userName, Set<GameType> gameTypeSet);
    //  interface TransportOfClient
    //      void identifyAuthenticateAuthorizeUser(String userName, String password);

    void closeConnect();
    //  interface TransportOfClient
    //      void close();
}

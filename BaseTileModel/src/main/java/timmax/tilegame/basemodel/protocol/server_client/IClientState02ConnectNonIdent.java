package timmax.tilegame.basemodel.protocol.server_client;

//  Подключение есть, но сервер не идентифицировал, не аутентифицировал и не авторизовал пользователя.
public interface IClientState02ConnectNonIdent extends IClientState00 {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:

    // ---- 2 ConnectNonIdent
    void authorizeUser(String userName);
    //  interface TransportOfClient
    //      void identifyAuthenticateAuthorizeUser(String userName, String password);

    void close();
    //  interface TransportOfClient
    //      void close();
}

package timmax.tilegame.basemodel.protocol.server_client;

//  Подключение есть, но сервер не идентифицировал, не аутентифицировал и не авторизовал пользователя.
public interface IClientState02ConnectNonIdent extends IClientState00 {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:

    // ---- 2 ConnectNonIdent
    void setUser(String userName); // logIn
    // void close();
}

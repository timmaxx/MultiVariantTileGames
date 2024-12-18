package timmax.tilegame.basemodel.protocol.server_client;

//  Подключение есть, сервер предоставил информацию о перечне типов игр.
//  Но сервер не идентифицировал, не аутентифицировал и не авторизовал пользователя.
public interface IClientState03ConnectWithServerInfo extends IClientState00 {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:

    // ---- 3 ConnectNonIdent
    void authorizeUser(String userName);
}

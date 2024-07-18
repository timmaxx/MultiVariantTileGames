package timmax.tilegame.transport;

import java.net.URI;
import java.util.Map;

import timmax.tilegame.basemodel.protocol.EventOfClient;
import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchId;

public interface TransportOfClient {
    void close();
    void connect();

    //  setURI(URI uriFromControls) Нужен в обоих классах, но в
    //  class MultiGameWebSocketClient
    //  т.к. он
    //  extends org.java_websocket.client.WebSocketClient
    //  это не получается. Также смотри комментарии к MultiGameWebSocketClient.
    void setURI(URI uriFromControls);

    void sendEventOfClient(EventOfClient eventOfClient);

    LocalClientStateAutomaton getLocalClientStateAutomaton();

    // Методы ниже я перевёл сюда из интерфейса IModelOfClient (уже удалён).
    // Методы этого интерфейса возвращают void т.к. все они исполняются на стороне клиента и не ждут синхронного ответа от сервера.
    // В правой колонке, в комментариях, даны соответствующие методы интерфейсов IClientState0Х...
    // ---- 2 ConnectNonIdent
    void setUser(String userName, String password);                             //  22  //  2 -> 3  void setUser(String userName);

    // ---- 4 (GameTypeSetSelected)
    void forgetUser();                                                          //  40  //  4 -> 2  void forgetUser();
    void setGameType(GameType gameType);                                        //  41  //  4 -> 5  void setGameType(GameType gameType);

    // ---- 6 (MatchSetSelected)
    // ToDo: Переименовать метод в forgetGameType?
    void forgetGameMatchSet();                                                  //  60  //  6 -> 5  void forgetGameMatchSet();
    void setGameMatch(GameMatchId gameMatchId);                                 //  61  //  6 -> 7  void setGameMatch(Model serverBaseModel);

    // ---- 7 (MatchSelected)
    void forgetGameMatch();                                                     //  70  //  7 -> 6  void forgetGameMatch();
    void setGameMatchPlaying(Map<String, Integer> mapOfParamsOfModelValue);     //  71  //  7 -> 8  void setGameMatchPlaying(Boolean gameIsPlaying);

    // ---- 8 (GameIsPlaying)
    void forgetGameMatchPlaying();                                              //  80  //  8 -> 7  void forgetGameMatchPlaying();
}

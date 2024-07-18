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

    // Методы этого интерфейса возвращают void т.к. все они исполняются на стороне клиента и не ждут синхронного ответа от сервера.
    // В правой колонке, в комментариях, даны соответствующие методы интерфейсов IClientState0Х...
    // ToDo: Поскольку есть совпадение имён методов и х назначений, возможно свести их в единую систему.
    // ---- 2 ConnectNonIdent
    void setUser(String userName, String password);                             //  2 -> 4  void setUser(String userName, Set<GameType> gameTypeSet);

    // ---- 4 (GameTypeSetSelected)
    void forgetUser();                                                          //  4 -> 2  void forgetUser();
    void setGameType(GameType gameType);                                        //  4 -> 5  void setGameType(GameType gameType, Set<GameMatchX> gameMatchXSet);

    // ---- 6 (MatchSetSelected)
    void forgetGameType();                                                      //  6 -> 5  void forgetGameType();
    void setGameMatch(GameMatchId gameMatchId);                                 //  6 -> 7  void setGameMatchX(GameMatchX gameMatchX);

    // ---- 7 (MatchSelected)
    void forgetGameMatch();                                                     //  7 -> 6  void forgetGameMatchX();
    void setGameMatchPlaying(Map<String, Integer> mapOfParamsOfModelValue);     //  7 -> 8  void setGameMatchPlaying(Boolean gameIsPlaying);

    // ---- 8 (GameIsPlaying)
    void forgetGameMatchPlaying();                                              //  8 -> 7  void forgetGameMatchPlaying();
}

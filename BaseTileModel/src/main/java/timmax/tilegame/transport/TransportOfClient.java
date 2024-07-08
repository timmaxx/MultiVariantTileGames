package timmax.tilegame.transport;

import java.net.URI;
import java.util.Map;

import timmax.tilegame.basemodel.protocol.EventOfClient;
import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchId;

// ToDo: Непонятно, почему класс параметризирован ClientId? Ведь он используется на клиенте. В частности
//       sendEventOfClient(...)
//       отправляет событие от клиента серверу и событие не содержит идентификатора клиента.
//       Но сервер, приняв событие от клиента, знает идентификатор клиента и как-то по нему будет у себя понимать,
//       сообщение пришло от того или иного клиента.
public interface TransportOfClient<ClientId> {
    void close();
    void connect();

    //  setURI(URI uriFromControls) Нужен в обоих классах, но в
    //  class MultiGameWebSocketClient
    //  т.к. он
    //  extends org.java_websocket.client.WebSocketClient
    //  это не получается. Также смотри кооментарии к MultiGameWebSocketClient.
    void setURI(URI uriFromControls);

    void sendEventOfClient(EventOfClient<ClientId> eventOfClient);

    LocalClientStateAutomaton getLocalClientStateAutomaton();

    // Методы ниже я перевёл сюда из интерфейса IModelOfClient (уже удалён).
    // Методы этого интерфейса возвращают void т.к. все они исполняются на стороне клиента и не ждут синхронного ответа от сервера.
    // В правой колонке, в комментариях, даны соответствующие методы интерфейсов IClientState0Х...
    // ---- 2 ConnectNonIdent
    void setUser(String userName, String password);                             //  22  //  2 -> 3  void setUser(String userName);

    // ---- 3 ConnectAuthorized
    // ???                                                                              //  3       String getUserName();
    void forgetUser();                                                          //  21  //  3 -> 2  void forgetUser();
    void setGameTypeSet();                                                      //  32  //  3 -> 4  void setGameTypeSet(Set<GameType> gameTypeSet);

    // ---- 4 (GameTypeSetSelected)
    // ???                                                                              //  4       Set<GameType> getGameTypeSet();
    void forgetGameTypeSet();                                                   //  31  //  4 -> 3  void forgetGameTypeSet();
    void setGameType(GameType gameType);                                        //  42  //  4 -> 5  void setGameType(GameType gameType);

    // ---- 5 (GameTypeSelected)
    // ???                                                                              //  5       GameType getGameType();
    void forgetGameType();                                                      //  41  //  5 -> 4  void forgetGameType();
    void setGameMatchSet();                                                     //  52  //  5 -> 6  void setGameMatchSet(Set<Model> setOfServerBaseModel);

    // ---- 6 (MatchSetSelected)
    // ???                                                                              //  6       Set<Model> getGameMatchSet();
    void forgetGameMatchSet();                                                  //  51  //  6 -> 5  void forgetGameMatchSet();
    void setGameMatch(GameMatchId gameMatchId);                                 //  62  //  6 -> 7  void setGameMatch(Model serverBaseModel);

    // ---- 7 (MatchSelected)
    // ???                                                                              //  7       Model getServerBaseModel();
    void forgetGameMatch();                                                     //  61  //  7 -> 6  void forgetGameMatch();
    void setGameMatchPlaying(Map<String, Integer> mapOfParamsOfModelValue);     //  72  //  7 -> 8  void setGameMatchPlaying(Boolean gameIsPlaying);

    // ---- 8 (GameIsPlaying)
    // ???                                                                              //  8       Boolean getGameIsPlaying();
    void forgetGameMatchPlaying();                                              //  71  //  8 -> 7  void forgetGameMatchPlaying();
}

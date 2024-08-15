package timmax.tilegame.transport;

import java.net.URI;
import java.util.Map;

import timmax.tilegame.basemodel.protocol.EventOfClient;
import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchDto;

public interface TransportOfClient {
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
    // ToDo: Поскольку есть совпадение имён методов и их назначений, возможно, свести их в единую систему.
    // 01NoConnect
    void connect();                                                             //  1 -> 2  void changeStateTo02ConnectNonIdent();
    
    // 02ConnectNonIdent
    void connectWithoutUserIdentify();          /* logOff */                    //  2 -> 2  void forgetUser();
    void close();                                                               //  2 -> 1  void changeStateTo01NoConnect();
    void identifyAuthenticateAuthorizeUser(String userName, String password); /* logIn */   //  2 -> 4  void setUser(String userName, Set<GameType> gameTypeSet);

    // 04GameTypeSetSelected
    void reauthorizeUser();                                                     //  4 -> 4
    void setGameType(GameType gameType);                                        //  4 -> 5  void setGameType(GameType gameType, Set<GameMatchX> gameMatchXSet);

    // 06GameMatchSetSelected
    void reselectGameType();                                                    //  6 -> 6  void reselectGameType();
    void setGameMatch(GameMatchDto gameMatchDto);                               //  6 -> 7  void setGameMatchX(GameMatchX gameMatchX);

    // 07GameMatchSelected
    // ToDo: Переименовать в reselectGameMatch()
    void resetGameMatch();                                                      //  7 -> 7  void forgetGameMatchPlaying();
    void startGameMatch(Map<String, Integer> mapOfParamsOfModelValue);          //  7 -> 8  void setGameMatchPlaying();
    void resumeGameMatch();                                                     //  7 -> 8  void setGameMatchPlaying();

    // 08GameMatchIsPlaying
}

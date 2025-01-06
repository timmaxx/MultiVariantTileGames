package timmax.tilegame.transport;

import java.net.URI;
import java.util.Map;

import timmax.tilegame.basemodel.dto.UserDtoPassword;
import timmax.tilegame.basemodel.protocol.EventOfClient92GameCommand;
import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.dto.GameMatchDto;

public interface ISenderOfEventOfClient {
    void setURI(URI uriFromControls);

    LocalClientStateAutomaton getLocalClientStateAutomaton();

    // ToDo: Поскольку есть соответствие методов интерфейса TransportOfClient и методов интерфейсов IClientState0Х...
    //       в части их имён, назначений, параметров, возможно, свести их в единую систему.
    //       Методы этого интерфейса возвращают void т.к. все они исполняются на стороне клиента и не ждут синхронного ответа от сервера.
    //       В правой колонке, в комментариях, даны соответствующие методы интерфейсов IClientState0Х...

    // 01NoConnect
    void connect();                                                             //  1 -> 2  нет соответствия

    // 02ConnectNonIdent
    void connectWithoutUserIdentify();                                          //  2 -> 2  void openConnectWithoutUserIdentify();
    void close();                                                               //  2 -> 1  void close();
    void identifyAuthenticateAuthorizeUser(UserDtoPassword userDtoPassword);    //  2 -> 4  void authorizeUser(String userId, Set<GameType> gameTypeSet);

    // 04GameTypeSetSelected
    void reauthorizeUser();                                                     //  4 -> 4  void reauthorizeUser();
    void setGameType(GameType gameType);                                        //  4 -> 5  void setGameType(GameType gameType, Set<GameMatchX> gameMatchXSet);

    // 06GameMatchSetSelected
    void resetGameType();                                                       //  6 -> 6  void resetGameType();
    void setGameMatch(GameMatchDto gameMatchDto);                               //  6 -> 7  void setGameMatchX(GameMatchX gameMatchX);

    // 07GameMatchSelected
    void resetGameMatch();                                                      //  7 -> 7  void resetGameMatch();
    void startGameMatch(Map<String, Integer> mapOfParamsOfModelValue);          //  7 -> 8  void startGameMatch(Map<String, Integer> mapOfParamsOfModelValue);

    // 08GameMatchIsPlaying
    void sendEventOfClient92GameCommand(EventOfClient92GameCommand eventOfClient92GameCommand); //  8 -> 8  нет соответствия
}

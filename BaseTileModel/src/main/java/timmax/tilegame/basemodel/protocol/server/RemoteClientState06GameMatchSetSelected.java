package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState06GameMatchSetSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchId;

import java.util.Set;
import java.util.stream.Collectors;

public class RemoteClientState06GameMatchSetSelected<ClientId> extends ClientState06GameMatchSetSelected<IGameMatch> {
    private final ClientId clientId;

    public RemoteClientState06GameMatchSetSelected(ClientStateAutomaton<IGameMatch> clientStateAutomaton, ClientId clientId) {
        super(clientStateAutomaton);
        this.clientId = clientId;
    }

    // ToDo: Устранить дублирование кода.
    //       Этот класс является наследником ClientState06GameMatchSetSelected,
    //       но код который хотелось-бы иметь как void identifyAuthenticateAuthorizeUser(),
    //       находится в RemoteClientState02ConnectNonIdent.
    //       Поэтому пришлось сделать здесь точную копию.
    //       - Копия метода из RemoteClientState02ConnectNonIdent:
    @Override
    public void identifyAuthenticateAuthorizeUser(String userName, Set<GameType> gameTypeSet) {
        super.identifyAuthenticateAuthorizeUser(userName, gameTypeSet);
        getClientStateAutomaton().sendEventOfServer(clientId, new EventOfServer21IdentifyAuthenticateAuthorizeUser(userName, gameTypeSet));
    }

    // ToDo: Устранить дублирование кода.
    //       Этот класс является наследником ClientState06GameMatchSetSelected,
    //       но код который хотелось-бы иметь как void openConnectWithoutUserIdentify(),
    //       находится в RemoteClientState04GameTypeSetSelected.
    //       Поэтому пришлось сделать здесь точную копию.
    //       - Копия метода из RemoteClientState04GameTypeSetSelected:
    @Override
    public void openConnectWithoutUserIdentify() {
        super.openConnectWithoutUserIdentify();
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer11ConnectWithoutUserIdentify()
        );
    }

    @Override
    public void reauthorizeUser() {
        String userName = getClientStateAutomaton().getUserName();
        Set<GameType> gameTypeSet = getClientStateAutomaton().getGameTypeSet();
        identifyAuthenticateAuthorizeUser(userName, gameTypeSet);
    }

    // ---- 6 Конкретная партия игры
    @Override
    public void resetGameType() {
        GameType gameType = getClientStateAutomaton().getGameType();
        Set<IGameMatch> gameMatchXSet = getClientStateAutomaton().getGameMatchXSet();
        super.setGameType(gameType, gameMatchXSet);
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer41SetGameType(
                        gameType.getGameTypeName(),
                        gameMatchXSet
                                .stream()
                                .map(x -> new GameMatchId(x.toString()))
                                .collect(Collectors.toSet())
                )
        );
    }

    @Override
    public void setGameMatchX(IGameMatch gameMatchX) {
        super.setGameMatchX(gameMatchX);
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer61SetGameMatch(
                        new GameMatchId(gameMatchX.toString())
                )
        );
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton getClientStateAutomaton() {
        return (RemoteClientStateAutomaton) (super.getClientStateAutomaton());
    }
}

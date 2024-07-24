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
    public void authorizeUser(String userName, Set<GameType> gameTypeSet) {
        super.authorizeUser(userName, gameTypeSet);
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
        authorizeUser(userName, gameTypeSet);
    }

    // ToDo: Устранить дублирование кода.
    //       Этот класс является наследником ClientState06GameMatchSetSelected,
    //       но код который хотелось-бы иметь как void setGameType(),
    //       находится в RemoteClientState04GameTypeSetSelected.
    //       Поэтому пришлось сделать здесь точную копию.
    //       - Копия метода из RemoteClientState04GameTypeSetSelected:
    @Override
    public void setGameType(GameType gameType, Set<IGameMatch> gameMatchXSet) {
        if (gameType == null) {
            getClientStateAutomaton().sendEventOfServer(
                    clientId,
                    new EventOfServer11ConnectWithoutUserIdentify()
            );
            return;
        }

        IGameMatch iGameMatch = null;
        Constructor<? extends IGameMatch> GameMatchConstructor = gameType.getGameMatchConstructor();

        try {
            iGameMatch = GameMatchConstructor.newInstance(getClientStateAutomaton(), clientId);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            logger.error("Server cannot create object of model for {} with GameMatchConstructor with specific parameters.", gameType, e);
            System.exit(1);
        }
        gameMatchXSet.add(iGameMatch);

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

    // ---- 6 Конкретная партия игры
    // ToDo: Также см. копия кода в RemoteClientState07, 08.
    @Override
    public void resetGameType() {
        GameType gameType = getClientStateAutomaton().getGameType();
        Set<IGameMatch> gameMatchXSet = getClientStateAutomaton().getGameMatchXSet();

        setGameType(gameType, gameMatchXSet);
    }

    // ToDo: Также см. копия кода в RemoteClientState07, 08.
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

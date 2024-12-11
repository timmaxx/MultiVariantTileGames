package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState04UserWasAuthorized;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

public class RemoteClientState04UserWasAuthorized<ClientId> extends ClientState04UserWasAuthorized<IGameMatch> {
    public RemoteClientState04UserWasAuthorized(ClientStateAutomaton<IGameMatch> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    @Override
    //  Warning:(17, 32) Raw use of parameterized class 'GameType'
    public void setGameType(GameType gameType) {
        //  ToDo:   Не использовать здесь initGameMatchXSet(...), т.к. множество матчей должно поступать из:
        //          - свойств пользователя (т.е. все неоконченные матчи, участником которых он был),
        //          - новый матч для текущего типа игры (но возможно, его лучше отдельно создавать).
        //          Вызов initGameMatchXSet(...) делает инициализацию внутри gameType множества матчей, добавляя туда
        //          только один единственный матч - новый.
        //          Если удалить отсюда вызов initGameMatchXSet(...), то и всю реализацию setGameType(...)
        //          тоже можно удалять в этом классе.
        //  Warning:(22, 9) Unchecked call to 'initGameMatchXSet(RemoteClientStateAutomaton<ClientId>)' as a member of raw type 'timmax.tilegame.basemodel.protocol.server.GameType'
        gameType.initGameMatchXSet(getBaseStateAutomaton());

        super.setGameType(gameType);
    }

    // class AbstractClientState
    @Override
    public void doAfterTurnOn() {
        getBaseStateAutomaton().sendEventOfServer(
                getBaseStateAutomaton().getClientId(),
                new EventOfServer21IdentifyAuthenticateAuthorizeUser(getBaseStateAutomaton().getUser().getUserName())
        );
    }

    @Override
    public RemoteClientStateAutomaton<ClientId> getBaseStateAutomaton() {
        //  Warning:(40, 16) Unchecked cast: 'timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton' to 'timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton<ClientId>'
        return (RemoteClientStateAutomaton<ClientId>) (super.getBaseStateAutomaton());
    }
}

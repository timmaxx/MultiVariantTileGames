package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState04UserWasAuthorized;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.util.BaseUtil;

public class RemoteClientState04UserWasAuthorized extends ClientState04UserWasAuthorized {
    public RemoteClientState04UserWasAuthorized(ClientStateAutomaton clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    @Override
    public void setGameType(GameType gameType) {
        //  ToDo:   Не использовать здесь initGameMatchXSet(...), т.к. множество матчей должно поступать из:
        //          - свойств пользователя (т.е. все неоконченные матчи, участником которых он был),
        //          - новый матч для текущего типа игры (но возможно, его лучше отдельно создавать).
        //          Вызов initGameMatchXSet(...) делает инициализацию внутри gameType множества матчей, добавляя туда
        //          только один единственный матч - новый.
        //          Если удалить отсюда вызов initGameMatchXSet(...), то и всю реализацию setGameType(...)
        //          тоже можно удалять в этом классе.
        gameType.initGameMatchXSet(getBaseStateAutomaton());

        super.setGameType(gameType);
    }

    //  class State
    @Override
    public void doAfterTurnOn() {
        getBaseStateAutomaton().getSenderOfEventOfServer().sendEventOfServer(
                getBaseStateAutomaton().getWebSocket(),
                new EventOfServer21IdentifyAuthenticateAuthorizeUser(
                        BaseUtil.createBaseDto(getBaseStateAutomaton().getUser())
                )
        );
    }

    //  class ClientState
    @Override
    public RemoteClientStateAutomaton getBaseStateAutomaton() {
        return (RemoteClientStateAutomaton) (super.getBaseStateAutomaton());
    }
}

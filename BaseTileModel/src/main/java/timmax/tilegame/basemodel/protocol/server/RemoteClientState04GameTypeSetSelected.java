package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState04GameTypeSetSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

public class RemoteClientState04GameTypeSetSelected<ClientId> extends ClientState04GameTypeSetSelected<IGameMatch> {
    public RemoteClientState04GameTypeSetSelected(ClientStateAutomaton<IGameMatch> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    @Override
    //  Warning:(17, 32) Raw use of parameterized class 'GameType'
    public void selectGameType(GameType gameType) {
        //  ToDo:   Не использовать здесь initGameMatchXSet(...), т.к. множество матчей должно поступать из:
        //          - свойств пользователя (т.е. все неоконченные матчи, участником которых он был),
        //          - новый матч для текущего типа игры (но возможно, его лучше отдельно создавать).
        //          Вызов initGameMatchXSet(...) делает инициализацию внутри gameType множества матчей, добавляя туда
        //          только один единственный матч - новый.
        //          Если удалить отсюда вызов initGameMatchXSet(...), то и всю реализацию selectGameType(...)
        //          тоже можно удалять в этом классе.

        //  ToDo:   Сформировать список матчей, фильтруя из общего списка матчей на сервере (пока такого нет).
        //          Список матчей должен накапливаться при работе сервера (даже без БД, а с БД и подавно).
        //          Но вообще-то, вместо пустого списка (new HashSet<>()), нужно возвращать перечень моделей,
        //          которые соответствуют выбранному типу игр, и к которым ещё можно присоединиться.
        //          Т.е. удовлетворяющих условиям:
        //              1. Игра для 2-х и более игроков.
        //              2. Есть хотя-бы одна не занятая роль.
        gameType.initGameMatchXSet(getClientStateAutomaton());

        super.selectGameType(gameType);
    }

    // class AbstractClientState
    @Override
    public void doAfterTurnOn() {
        getClientStateAutomaton().sendEventOfServer(
                getClientStateAutomaton().getClientId(),
                new EventOfServer21IdentifyAuthenticateAuthorizeUser(getClientStateAutomaton().getUser().getUserName())
        );
    }

    @Override
    public RemoteClientStateAutomaton<ClientId> getClientStateAutomaton() {
        return (RemoteClientStateAutomaton<ClientId>) (super.getClientStateAutomaton());
    }
}

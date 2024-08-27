package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState06GameMatchSetSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchDto;

import java.util.Set;
import java.util.stream.Collectors;

public class RemoteClientState06GameMatchSetSelected<ClientId> extends ClientState06GameMatchSetSelected<IGameMatch> {
    public RemoteClientState06GameMatchSetSelected(ClientStateAutomaton<IGameMatch> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    @Override
    //  Warning:(17, 32) Raw use of parameterized class 'GameType'
    public void selectGameType(GameType gameType) {
        if (gameType == null) {
            getClientStateAutomaton().sendEventOfServer(
                    getClientStateAutomaton().getClientId(),
                    new EventOfServer11ConnectWithoutUserIdentify()
            );
            return;
        }

        gameType.initGameMatchXSet(getClientStateAutomaton());

        //  ToDo:   Требует переделки, т.к. второй параметр (gameMatchXSet):
        //          - в принципе не должен поступать в этот метод, т.к. это зависимое значение.
        //          - и соответственно определаять его нужно до вызова этого метода внутри gameType.
        //          Как пример (в части не вычисления внутри метода), видно, что в
        //          RemoteClientState04GameTypeSetSelected :: authorizeUser(String userName, Set<GameType> gameTypeSet)
        //          второй параметр не вычисляется внутри authorizeUser(...). Но он всё-же передаётся в него...
        super.selectGameType(gameType);

        //  ToDo:   Ниже, использовать входящий параметр (здесь это gameMatchX) не рекомендуется, т.к.
        //          в методе super он может быть не принят полностью или в какой-то части, но в целевом экземпляре
        //          (здесь это GameMatchXSet) будет либо принят, либо сформирован свой (здесь это gameMatchX).
        //          Вот его и нужно упаковать в EventOfServer (здесь это EventOfServer61SelectGameMatch) и
        //          отправить клиенту.
        getClientStateAutomaton().sendEventOfServer(
                getClientStateAutomaton().getClientId(),
                new EventOfServer41SelectGameType(
                        gameType.getId(),
                        //  ToDo:   Приведение типа? Два раза?
                        (Set<GameMatchDto>) gameType.getGameMatchXSet()
                                .stream()
                                .map(x -> ((GameMatch) x).getGameMatchDto())
                                .collect(Collectors.toSet())
                )
        );
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton<ClientId> getClientStateAutomaton() {
        return (RemoteClientStateAutomaton<ClientId>) (super.getClientStateAutomaton());
    }
}

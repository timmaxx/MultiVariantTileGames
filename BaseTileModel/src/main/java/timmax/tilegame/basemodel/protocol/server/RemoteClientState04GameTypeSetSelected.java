package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState04GameTypeSetSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

import java.util.Set;

public class RemoteClientState04GameTypeSetSelected<ClientId> extends ClientState04GameTypeSetSelected<IGameMatch> {
    private final ClientId clientId;

    public RemoteClientState04GameTypeSetSelected(ClientStateAutomaton<IGameMatch> clientStateAutomaton, ClientId clientId) {
        super(clientStateAutomaton);
        this.clientId = clientId;
    }

    @Override
    public void authorizeUser(String userName, Set<GameType> gameTypeSet) {
        //  ToDo:   Требует переделки, т.к. второй параметр (gameTypeSet):
        //          - в принципе не должен поступать в этот метод, т.к. это зависимое значение.
        //          - и соответственно определаять его нужно до вызова этого метода внутри userName (т.е. создать
        //            отдельный класс User, в котором должен быть переменная Set<GameType> gameTypeSet).
        //          Также см. комментарий к
        //          RemoteClientState06GameMatchSetSelected :: void selectGameType(GameType gameType, Set<IGameMatch> gameMatchXSet)
        super.authorizeUser(userName, gameTypeSet);

        //  ToDo:   Ниже, использовать входящий параметр (здесь это userName) не рекомендуется, т.к.
        //          в методе super он может быть не принят полностью или в какой-то части, но в целевом экземпляре
        //          (здесь это ???) будет либо принят, либо сформирован свой (здесь это userName).
        //          Вот его и нужно упаковать в EventOfServer (здесь это EventOfServer21IdentifyAuthenticateAuthorizeUser) и
        //          отправить клиенту.
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer21IdentifyAuthenticateAuthorizeUser(userName, gameTypeSet)
        );
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton getClientStateAutomaton() {
        return (RemoteClientStateAutomaton) (super.getClientStateAutomaton());
    }
}

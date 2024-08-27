package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState07GameMatchSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

public class RemoteClientState07GameMatchSelected<ClientId> extends ClientState07GameMatchSelected {
    public RemoteClientState07GameMatchSelected(ClientStateAutomaton clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    @Override
    public void selectGameMatch(GameMatch gameMatch) {
        //  ToDo:   Обработать если получится null.
        //          Если будет передан матч, которого нет во множестве, то в gameMatch2 будет null.
        //  ToDo:   Приведение типа??? Два раза???
        GameMatch<ClientId> gameMatch2 = (GameMatch<ClientId>) getClientStateAutomaton()
                .getGameMatchSet()
                .stream()
                .filter(x -> ((GameMatch)x).getId().equals(gameMatch.getId()))
                .findAny()
                .orElse(null);

        super.selectGameMatch(gameMatch2);

        //  Done:   Ниже, использовать входящий параметр (здесь это gameMatch) не рекомендуется, т.к.
        //          в методе super он может быть не принят полностью или в какой-то части, но в целевом экземпляре
        //          (здесь это GameMatchSet) будет либо принят, либо сформирован свой (здесь это gameMatch).
        //          Вот его и нужно упаковать в EventOfServer (здесь это EventOfServer61SelectGameMatch) и
        //          отправить клиенту.
        getClientStateAutomaton().sendEventOfServer(
                getClientStateAutomaton().getClientId(),
                new EventOfServer61SelectGameMatch(
                        // gameMatch2
                        getClientStateAutomaton().getGameMatch()
                )
        );
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton<ClientId> getClientStateAutomaton() {
        return (RemoteClientStateAutomaton<ClientId>) (super.getClientStateAutomaton());
    }
}

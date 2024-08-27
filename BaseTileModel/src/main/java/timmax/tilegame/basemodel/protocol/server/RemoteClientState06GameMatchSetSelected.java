package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState06GameMatchSetSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

public class RemoteClientState06GameMatchSetSelected<ClientId> extends ClientState06GameMatchSetSelected {
    public RemoteClientState06GameMatchSetSelected(ClientStateAutomaton clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    @Override
    public void selectGameType(GameType gameType) {
        //  ToDo:   Обработать, если получится null.
        //          Если будет передан тип, которого нет во множестве, то в gameType2 будет null.
        //  ToDo:   Приведение типа??? Два раза???
        GameType gameType2 = (GameType) getClientStateAutomaton()
                .getGameTypeSet()
                .stream()
                .filter(x -> ((GameType)x).getGameTypeName().equals(gameType.getGameTypeName()))
                .findAny()
                .orElse(null);

        gameType2.initGameMatchSet(getClientStateAutomaton());

        super.selectGameType(gameType2);

        //  Done:   Ниже, использовать входящий параметр (здесь это gameMatch) не рекомендуется, т.к.
        //          в методе super (а здесь ещё выше) он может быть не принят полностью или в какой-то части,
        //          но в целевом экземпляре (здесь это GameMatchSet) будет либо принят,
        //          либо сформирован свой (здесь это gameMatch).
        //          Вот его и нужно упаковать в EventOfServer (здесь это EventOfServer61SelectGameMatch) и
        //          отправить клиенту.
        getClientStateAutomaton().sendEventOfServer(
                getClientStateAutomaton().getClientId(),
                // new EventOfServer41SelectGameType(gameType2)
                new EventOfServer41SelectGameType(getClientStateAutomaton().getGameType())
        );
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton<ClientId> getClientStateAutomaton() {
        return (RemoteClientStateAutomaton<ClientId>) (super.getClientStateAutomaton());
    }
}

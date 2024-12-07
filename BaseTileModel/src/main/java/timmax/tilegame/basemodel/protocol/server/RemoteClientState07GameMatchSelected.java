package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState07GameMatchSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchDto;

public class RemoteClientState07GameMatchSelected<ClientId> extends ClientState07GameMatchSelected<IGameMatch> {
    public RemoteClientState07GameMatchSelected(ClientStateAutomaton<IGameMatch> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    @Override
    public void setGameMatchX(IGameMatch gameMatchX) {
        super.setGameMatchX(gameMatchX);

        //  ToDo:   Ниже, использовать входящий параметр (здесь это gameMatchX) не рекомендуется, т.к.
        //          в методе super он может быть не принят полностью или в какой-то части, но в целевом экземпляре
        //          (здесь это GameMatchXSet) будет либо принят, либо сформирован свой (здесь это gameMatchX).
        //          Вот его и нужно упаковать в EventOfServer (здесь это EventOfServer61SetGameMatch) и
        //          отправить клиенту.
        getClientStateAutomaton().sendEventOfServer(
                getClientStateAutomaton().getClientId(),
                new EventOfServer61SetGameMatch(
                        new GameMatchDto(
                                gameMatchX.getId(),
                                gameMatchX.getStatus(),
                                gameMatchX.getParamsOfModelValueMap()
                        )
                )
        );
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton<ClientId> getClientStateAutomaton() {
        return (RemoteClientStateAutomaton<ClientId>) (super.getClientStateAutomaton());
    }
}

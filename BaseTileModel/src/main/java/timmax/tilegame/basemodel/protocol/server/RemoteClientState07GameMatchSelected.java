package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState07GameMatchSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchDto;

public class RemoteClientState07GameMatchSelected<ClientId> extends ClientState07GameMatchSelected<IGameMatch> {
    private final ClientId clientId;

    public RemoteClientState07GameMatchSelected(ClientStateAutomaton<IGameMatch> clientStateAutomaton, ClientId clientId) {
        super(clientStateAutomaton);
        this.clientId = clientId;
    }

    @Override
    public void selectGameMatchX(IGameMatch gameMatchX) {
        super.selectGameMatchX(gameMatchX);

        //  ToDo:   Ниже, использовать входящий параметр (здесь это gameMatchX) не рекомендуется, т.к.
        //          в методе super он может быть не принят полностью или в какой-то части, но в целевом экземпляре
        //          (здесь это GameMatchXSet) будет либо принят, либо сформирован свой (здесь это gameMatchX).
        //          Вот его и нужно упаковать в EventOfServer (здесь это EventOfServer61SelectGameMatch) и
        //          отправить клиенту.
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer61SelectGameMatch(
                        new GameMatchDto(
                                gameMatchX.getId(),
                                gameMatchX.isPlaying(),
                                gameMatchX.getParamsOfModelValueMap()
                        )
                )
        );
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton getClientStateAutomaton() {
        return (RemoteClientStateAutomaton) (super.getClientStateAutomaton());
    }
}

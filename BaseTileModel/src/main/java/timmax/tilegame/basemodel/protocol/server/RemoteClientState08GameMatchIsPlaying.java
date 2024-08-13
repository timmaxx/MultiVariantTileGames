package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

import java.util.Map;

public class RemoteClientState08GameMatchIsPlaying<ClientId> extends ClientState08GameMatchIsPlaying<IGameMatch> {
    private final ClientId clientId;

    public RemoteClientState08GameMatchIsPlaying(ClientStateAutomaton<IGameMatch> clientStateAutomaton, ClientId clientId) {
        super(clientStateAutomaton);
        this.clientId = clientId;
    }

    @Override
    public void startGameMatch(Map<String, Integer> mapOfParamsOfModelValue) {
        super.startGameMatch(mapOfParamsOfModelValue);
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer71StartGameMatch(mapOfParamsOfModelValue)
        );

        // ToDo: Вызов этого метода для модели:
        //       - для которой ранее ещё не было вызвано start().
        getClientStateAutomaton().getGameMatchX().start();
    }

    @Override
    public void resumeGameMatch() {
        super.resumeGameMatch();
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer73ResumeGameMatch()
        );

        // ToDo: Вызов этого метода для модели:
        //       - у которой был ранее вызов start(), потом было хотя-бы одно игровое событие, но потом она была поставлена на паузу.
        getClientStateAutomaton().getGameMatchX().resume();
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton getClientStateAutomaton() {
        return (RemoteClientStateAutomaton) (super.getClientStateAutomaton());
    }
}

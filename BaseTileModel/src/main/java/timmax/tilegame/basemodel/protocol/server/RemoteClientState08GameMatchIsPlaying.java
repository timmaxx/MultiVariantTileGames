package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

public class RemoteClientState08GameMatchIsPlaying<ClientId> extends ClientState08GameMatchIsPlaying<IGameMatch> {
    private final ClientId clientId;

    public RemoteClientState08GameMatchIsPlaying(ClientStateAutomaton<IGameMatch> clientStateAutomaton, ClientId clientId) {
        super(clientStateAutomaton);
        this.clientId = clientId;
    }

    @Override
    public GameMatchExtendedDto startGameMatch(GameMatchExtendedDto gameMatchExtendedDto) {
        // В метод передаётся, поступившая от клиента информация (параметры матча, текущие положения объектов),
        // но внутри метода будет проверка на соответствие: если информация будет признана неподходящей,
        // то внутри матча будет сформирована информация с другим содержанием.
        GameMatchExtendedDto gameMatchExtendedDto2 = super.startGameMatch(gameMatchExtendedDto);

        //  Именно из-за того, что информация могла быть сформирована не такая, какая пришла,
        //  клиенту будет отправлена информация, которая была сформирована при вызове предыдущего метода.
        //  (Клиент, после получения этого события только строит главную выборку (пустую доску)).
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer74StartGameMatch(gameMatchExtendedDto2)
        );
        return gameMatchExtendedDto2;
    }

    @Override
    public void resumeGameMatch() {
        super.resumeGameMatch();
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer73ResumeGameMatch()
                // new EventOfServer73ResumeGameMatch(getGameMatchX().getParamsOfModelValueMap())
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

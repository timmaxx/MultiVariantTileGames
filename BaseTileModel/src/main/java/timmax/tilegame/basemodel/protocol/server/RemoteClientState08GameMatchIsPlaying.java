package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

public class RemoteClientState08GameMatchIsPlaying<ClientId> extends ClientState08GameMatchIsPlaying {
    public RemoteClientState08GameMatchIsPlaying(ClientStateAutomaton clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    @Override
    public GameMatch startGameMatch(GameMatch gameMatch) {
        //  ToDo:   Обработать если получится null.
        //          Если будет передан матч, которого нет во множестве, то в gameMatch2 будет null.
        //  ToDo:   Приведение типа??? Два раза???
        GameMatch<ClientId> gameMatch2 = (GameMatch<ClientId>) getClientStateAutomaton()
                .getGameMatchSet()
                .stream()
                .filter(x -> ((GameMatch)x).getId().equals(gameMatch.getId()))
                .findAny()
                .orElse(null);

        gameMatch2.setParamsOfModelValueMap(gameMatch.getParamsOfModelValueMap());

        // В метод передаётся, поступившая от клиента информация (параметры матча, текущие положения объектов),
        // но внутри метода будет проверка на соответствие: если информация будет признана неподходящей,
        // то внутри матча будет сформирована информация с другим содержанием.
        GameMatch<ClientId> gameMatch3 = super.startGameMatch(gameMatch2);

        //  Именно из-за того, что информация могла быть сформирована не такая, какая пришла,
        //  клиенту будет отправлена информация, которая была сформирована при вызове предыдущего метода.
        //  (Клиент, после получения этого события только строит главную выборку (пустую доску)).
        getClientStateAutomaton().sendEventOfServer(
                getClientStateAutomaton().getClientId(),
                // new EventOfServer74StartGameMatch(gameMatch3)
                new EventOfServer74StartGameMatch<>(getClientStateAutomaton().getGameMatch())
        );
        return getClientStateAutomaton().getGameMatch();
    }

    @Override
    public void resumeGameMatch() {
        super.resumeGameMatch();
        getClientStateAutomaton().sendEventOfServer(
                getClientStateAutomaton().getClientId(),
                new EventOfServer73ResumeGameMatch()
        );

        // ToDo: Вызов этого метода для модели:
        //       - у которой был ранее вызов start(), потом было хотя-бы одно игровое событие, но потом она была поставлена на паузу.
        getClientStateAutomaton().getGameMatch().resume();
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton<ClientId> getClientStateAutomaton() {
        return (RemoteClientStateAutomaton<ClientId>) (super.getClientStateAutomaton());
    }
}

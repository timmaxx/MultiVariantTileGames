package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

public class RemoteClientState08GameMatchIsPlaying<ClientId> extends ClientState08GameMatchIsPlaying<IGameMatch> {
    public RemoteClientState08GameMatchIsPlaying(ClientStateAutomaton<IGameMatch> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    @Override
    public void startGameMatch(GameMatchExtendedDto gameMatchExtendedDto) {
        //  В метод передаётся, поступившая от клиента информация (параметры матча, текущие положения объектов),
        //  но внутри метода будет проверка на соответствие: если информация будет признана неподходящей,
        //  то внутри матча будет сформирована информация с другим содержанием.
        super.startGameMatch(gameMatchExtendedDto);
        GameMatchExtendedDto gameMatchExtendedDto2 =
                ((GameMatch)(getClientStateAutomaton().getGameMatchX()))
                        .getGameMatchExtendedDto();

        //  Именно из-за того, что информация могла быть сформирована не такая, какая пришла,
        //  клиенту будет отправлена информация, которая была сформирована при вызове предыдущего метода.
        //  (Клиент, после получения этого события только строит главную выборку (пустую доску)).
        getClientStateAutomaton().sendEventOfServer(
                getClientStateAutomaton().getClientId(),
                new EventOfServer74StartGameMatch(gameMatchExtendedDto2)
        );
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton<ClientId> getClientStateAutomaton() {
        return (RemoteClientStateAutomaton<ClientId>) (super.getClientStateAutomaton());
    }
}

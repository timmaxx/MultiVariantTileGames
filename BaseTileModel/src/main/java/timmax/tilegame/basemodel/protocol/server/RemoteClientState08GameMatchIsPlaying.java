package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

public class RemoteClientState08GameMatchIsPlaying extends ClientState08GameMatchIsPlaying {
    public RemoteClientState08GameMatchIsPlaying(ClientStateAutomaton clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    //  class State
    @Override
    public void doAfterTurnOn() {
        //  Именно из-за того, что информация могла быть сформирована не такая, какая пришла,
        //  клиенту будет отправлена информация, которая была сформирована при вызове предыдущего метода.
        //  (Клиент, после получения этого события только строит главную выборку (пустую доску)).
        getBaseStateAutomaton().getSenderOfEventOfServer().sendEventOfServer(
                //  Предыдущие RemoteClientState02-07 отправляли сообщение конкретному клиенту.
                //  Этот должен отправить уже нескольким клиентам - пользователям, являющимися игроками данного матча.
                getBaseStateAutomaton().getGameMatchX().getMatchPlayerList(),
                new EventOfServer71StartGameMatch(getBaseStateAutomaton().getGameMatchX().getGameMatchExtendedDto())
        );
    }

    //  class ClientState
    @Override
    public RemoteClientStateAutomaton getBaseStateAutomaton() {
        return (RemoteClientStateAutomaton) (super.getBaseStateAutomaton());
    }
}

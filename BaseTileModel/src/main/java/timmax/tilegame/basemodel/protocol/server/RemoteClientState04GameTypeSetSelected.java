package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState04GameTypeSetSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.baseview.View;

import java.util.Map;

public class RemoteClientState04GameTypeSetSelected<ClientId> extends ClientState04GameTypeSetSelected<IGameMatch> {
    private final ClientId clientId;

    public RemoteClientState04GameTypeSetSelected(ClientStateAutomaton<IGameMatch> clientStateAutomaton, ClientId clientId) {
        super(clientStateAutomaton);
        this.clientId = clientId;
    }

    // class ClientState04GameTypeSetSelected
    // ---- 3 (Список типов игр)
    @Override
    public void forgetGameTypeSet() {
        super.forgetGameTypeSet();
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer30ForgetGameTypeSet()
        );
    }

    // ---- 4 (Конкретный тип игры)
    @Override
    public void setGameType(GameType gameType) {
        super.setGameType(gameType);
        if (gameType == null) {
            getClientStateAutomaton().ViewNameSetClear();
            getClientStateAutomaton().sendEventOfServer(
                    clientId,
                    new EventOfServer40ForgetGameType()
            );
            return;
        }
        getClientStateAutomaton().ViewNameSetClear();
        // ToDo: Сейчас foreach работает и с ключём и со значением (аналогично как в классе LocalClientState),
        //       Но здесь достаточно только с ключём.
        for (Map.Entry<String, Class<? extends View>> entry : gameType.getMapOfViewNameViewClass().entrySet()) {
            // ToDo: Сделать прямой вызов add() и удалить метод getSetOfViewName()
            getClientStateAutomaton().getSetOfViewName().add(entry.getKey());
        }
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer41SetGameType(gameType.getGameName())
        );
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton<ClientId> getClientStateAutomaton() {
        return (RemoteClientStateAutomaton<ClientId>) (super.getClientStateAutomaton());
    }
}

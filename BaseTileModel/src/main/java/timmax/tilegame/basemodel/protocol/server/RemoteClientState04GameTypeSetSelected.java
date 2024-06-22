package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState04GameTypeSetSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.baseview.View;

import java.util.Map;

public class RemoteClientState04GameTypeSetSelected<ClientId> extends ClientState04GameTypeSetSelected<IModelOfServer> {
    public RemoteClientState04GameTypeSetSelected(ClientStateAutomaton<IModelOfServer> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // class ClientState04GameTypeSetSelected
    // ---- 3 (Список типов игр)
    @Override
    public void forgetGameTypeSet() {
        super.forgetGameTypeSet();
        getClientStateAutomaton().getTransportOfServer().sendEventOfServer(getClientStateAutomaton().getClientId(), new EventOfServer30ForgetGameTypeSet());
    }

    // ---- 4 (Конкретный тип игры)
    @Override
    public void setGameType(ModelOfServerDescriptor modelOfServerDescriptor) {
        super.setGameType(modelOfServerDescriptor);
        if (modelOfServerDescriptor == null) {
            getClientStateAutomaton().getSetOfViewName().clear();
            getClientStateAutomaton().getTransportOfServer().sendEventOfServer(
                    getClientStateAutomaton().getClientId(),
                    new EventOfServer40ForgetGameType()
            );
            return;
        }
        getClientStateAutomaton().getSetOfViewName().clear();
        // ToDo: Сейчас foreach работает и с ключём и со значением (аналогично как в классе LocalClientState),
        //       Но здесь достаточно только с ключём.
        for (Map.Entry<String, Class<? extends View>> entry : modelOfServerDescriptor.getMapOfViewNameViewClass().entrySet()) {
            getClientStateAutomaton().getSetOfViewName().add(entry.getKey());
        }
        getClientStateAutomaton().getTransportOfServer().sendEventOfServer(
                getClientStateAutomaton().getClientId(),
                new EventOfServer41SetGameType(modelOfServerDescriptor.getGameName())
        );
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton<ClientId> getClientStateAutomaton() {
        return (RemoteClientStateAutomaton<ClientId>) (super.getClientStateAutomaton());
    }
}

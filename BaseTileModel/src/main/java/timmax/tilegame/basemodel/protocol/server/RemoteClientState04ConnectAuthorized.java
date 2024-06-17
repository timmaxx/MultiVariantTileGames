package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState04GameTypeSetSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.baseview.View;
import timmax.tilegame.transport.TransportOfServer;

import java.lang.reflect.Constructor;
import java.util.Map;

public class RemoteClientState04ConnectAuthorized<ClientId> extends ClientState04GameTypeSetSelected<IModelOfServer, ClientId> {
    public RemoteClientState04ConnectAuthorized(ClientStateAutomaton<IModelOfServer, ClientId> clientStateAutomaton, TransportOfServer<ClientId> transportOfServer, ClientId clientId) {
        super(clientStateAutomaton, transportOfServer, clientId);
    }

    // ---- 3 (Список типов игр)
    @Override
    public void forgetGameTypeSet() {
        super.forgetGameTypeSet();
        getTransportOfServer().sendEventOfServer(getClientId(), new EventOfServer30ForgetGameTypeSet());
    }

    // ---- 4 (Конкретный тип игры)
    @Override
    public void setGameType(ModelOfServerDescriptor modelOfServerDescriptor) {
        super.setGameType(modelOfServerDescriptor);
        if(modelOfServerDescriptor == null) {
            // setOfViewName = null;
            getSetOfViewName().clear();
            getTransportOfServer().sendEventOfServer(getClientId(), new EventOfServer40ForgetGameType());
            return;
        }
        // setOfViewName = new HashSet<>();
        getSetOfViewName().clear();
        // ToDo: Сейчас foreach работает и с ключём и со значением (аналогично как в классе LocalClientState),
        //       Но здесь достаточно только с ключём.
        for (Map.Entry<String, Class<? extends View>> entry : modelOfServerDescriptor.getMapOfViewNameViewClass().entrySet()) {
            // setOfViewName.add(entry.getKey());
            getSetOfViewName().add(entry.getKey());
        }
        getTransportOfServer().sendEventOfServer(getClientId(), new EventOfServer41SetGameType(modelOfServerDescriptor.getGameName()));
    }

    @Override
    public Constructor<? extends View> getViewConstructor(Class<? extends View> classOfView) {
        throw new RuntimeException("Not available for this class!");
    }
}

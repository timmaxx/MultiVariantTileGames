package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState06GameMatchSetSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.baseview.View;
import timmax.tilegame.transport.TransportOfServer;

import java.lang.reflect.Constructor;

public class RemoteClientState06GameMatchSetSelected<ClientId> extends ClientState06GameMatchSetSelected<IModelOfServer, ClientId> {
    public RemoteClientState06GameMatchSetSelected(ClientStateAutomaton<IModelOfServer, ClientId> clientStateAutomaton, TransportOfServer<ClientId> transportOfServer, ClientId clientId) {
        super(clientStateAutomaton, transportOfServer, clientId);
    }

    // ---- 5 Перечень партий
    @Override
    public void forgetGameMatchSet() {
        // super.setGameMatchSet(null);
        super.forgetGameMatchSet();
        getTransportOfServer().sendEventOfServer(getClientId(), new EventOfServer50ForgetGameMatchSet());
    }

    // ---- 6 Конкретная партия игры
    @Override
    public void setServerBaseModel(IModelOfServer iModelOfServer) {
        super.setServerBaseModel(iModelOfServer);
        getTransportOfServer().sendEventOfServer(getClientId(), new EventOfServer61SetGameMatch(
                new InstanceIdOfModel(iModelOfServer.toString())
        ));
    }

    @Override
    public Constructor<? extends View> getViewConstructor(Class<? extends View> classOfView) {
        throw new RuntimeException("Not available for this class!");
    }
}

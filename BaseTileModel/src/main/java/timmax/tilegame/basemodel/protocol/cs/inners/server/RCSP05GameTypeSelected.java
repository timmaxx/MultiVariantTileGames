package timmax.tilegame.basemodel.protocol.cs.inners.server;

import java.util.Set;
import java.util.stream.Collectors;

import timmax.tilegame.basemodel.protocol.EventOfServer40ForgetGameType;
import timmax.tilegame.basemodel.protocol.EventOfServer51GetGameMatchSet;
import timmax.tilegame.basemodel.protocol.cs.inners.CSP05GameTypeSelected;
import timmax.tilegame.basemodel.protocol.cs.inners.ClientState;
import timmax.tilegame.basemodel.protocol.server.IModelOfServer;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.transport.TransportOfServer;

public class RCSP05GameTypeSelected<ClientId> extends CSP05GameTypeSelected<IModelOfServer> {
    private final ClientId clientId;
    private final TransportOfServer<ClientId> transportOfServer;

    public RCSP05GameTypeSelected(ClientState<IModelOfServer> clientState, ClientId clientId, TransportOfServer<ClientId> transportOfServer) {
        super(clientState);
        this.clientId = clientId;
        this.transportOfServer = transportOfServer;
    }

    @Override
    public void forgetGameType() {
        super.forgetGameType();
        transportOfServer.sendEventOfServer(clientId, new EventOfServer40ForgetGameType());
    }

    @Override
    public void setGameMatchSet(Set<IModelOfServer> setOfServerBaseModel) {
        super.setGameMatchSet(setOfServerBaseModel);
        transportOfServer.sendEventOfServer(clientId, new EventOfServer51GetGameMatchSet(
                setOfServerBaseModel
                        .stream()
                        .map(InstanceIdOfModel::modelOfServerToInstanceIdOfModel)
                        .collect(Collectors.toSet())
        ));
    }
}

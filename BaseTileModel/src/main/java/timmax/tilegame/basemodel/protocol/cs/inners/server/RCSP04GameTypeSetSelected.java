package timmax.tilegame.basemodel.protocol.cs.inners.server;

import java.util.Map;

import timmax.tilegame.basemodel.protocol.EventOfServer30ForgetGameTypeSet;
import timmax.tilegame.basemodel.protocol.EventOfServer40ForgetGameType;
import timmax.tilegame.basemodel.protocol.EventOfServer41SetGameType;
import timmax.tilegame.basemodel.protocol.cs.inners.CSP04GameTypeSetSelected;
import timmax.tilegame.basemodel.protocol.cs.inners.ClientState;
import timmax.tilegame.basemodel.protocol.server.IModelOfServer;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.baseview.View;
import timmax.tilegame.transport.TransportOfServer;

public class RCSP04GameTypeSetSelected<ClientId> extends CSP04GameTypeSetSelected<IModelOfServer> {
    private final ClientId clientId;
    private final TransportOfServer<ClientId> transportOfServer;

    public RCSP04GameTypeSetSelected(ClientState<IModelOfServer> clientState, ClientId clientId, TransportOfServer<ClientId> transportOfServer) {
        super(clientState);
        this.clientId = clientId;
        this.transportOfServer = transportOfServer;
    }

    @Override
    public void forgetGameTypeSet() {
        super.forgetGameTypeSet();
        transportOfServer.sendEventOfServer(clientId, new EventOfServer30ForgetGameTypeSet());
    }

    @Override
    public void setGameType(ModelOfServerDescriptor modelOfServerDescriptor) {
        super.setGameType(modelOfServerDescriptor);
        if (modelOfServerDescriptor == null) {
            // setOfViewName = null; // !!!!!
            transportOfServer.sendEventOfServer(clientId, new EventOfServer40ForgetGameType());
            return;
        }
        // setOfViewName = new HashSet<>(); // !!!!!
        // ToDo: Сейчас foreach работает и с ключём и со значением (аналогично как в классе LocalClientState),
        //       Но здесь достаточно только с ключём.
        for (Map.Entry<String, Class<? extends View>> entry : modelOfServerDescriptor.getMapOfViewNameViewClass().entrySet()) {
            // setOfViewName.add(entry.getKey()); // !!!!!
        }
        transportOfServer.sendEventOfServer(clientId, new EventOfServer41SetGameType(modelOfServerDescriptor.getGameName()));
    }
}

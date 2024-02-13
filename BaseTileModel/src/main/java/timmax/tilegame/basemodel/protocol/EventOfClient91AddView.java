package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.protocol.server.RemoteView;
import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient91AddView extends EventOfClient {
    private String viewName;

    public EventOfClient91AddView() {
        super();
    }

    public EventOfClient91AddView(String viewName) {
        this();
        this.viewName = viewName;
    }

    @Override
    public <ClientId> void executeOnServer(TransportOfServer<ClientId> transportOfServer, ClientId clientId) {
        System.out.println("  onAddView");

        transportOfServer
                .getRemoteClientStateByClientId(clientId)
                .getServerBaseModel()
                .addRemoteView(new RemoteView<>(clientId, viewName))
        ;

        // transportOfServer.sendEventOfServer(clientId, new EventOfServer91AddView(viewId));
    }

    @Override
    public String toString() {
        return "EventOfClient91AddView{" +
                "viewName='" + viewName + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(viewName);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        viewName = (String) in.readObject();
    }
}

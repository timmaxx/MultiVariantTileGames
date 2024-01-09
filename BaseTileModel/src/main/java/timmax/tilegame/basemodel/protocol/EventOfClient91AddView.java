package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.protocol.server.RemoteView;
import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient91AddView extends EventOfClient {
    private String viewId;

    public EventOfClient91AddView() {
        super();
    }

    public EventOfClient91AddView(String viewId) {
        this();
        this.viewId = viewId;
    }

    @Override
    public <T> void executeOnServer(TransportOfServer<T> transportOfServer, T clientId) {
        System.out.println("  onAddView");

        transportOfServer.getModelOfServer().addRemoteView(new RemoteView<>(clientId, viewId));
        transportOfServer.sendEventOfServerToClient(clientId, new EventOfServer91AddView(viewId));
    }

    @Override
    public String toString() {
        return "EventOfClient91AddView{" +
                "viewId='" + viewId + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(viewId);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        viewId = (String) in.readObject();
    }
}

package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.protocol.server.RemoteView;
import timmax.tilegame.transport.TransportOfServer;

public class TransportPackageOfClient91AddView<T> extends TransportPackageOfClient<T> {
    private String viewId;


    public TransportPackageOfClient91AddView() {
        super();
    }

    public TransportPackageOfClient91AddView(String viewId) {
        this();
        this.viewId = viewId;
    }

    @Override
    public void execute(TransportOfServer<T> transportOfServer, T clientId) {
        System.out.println("  onAddView");

        // System.out.println("    viewId = " + viewId);
        transportOfServer.getModelOfServer().addRemoteView(new RemoteView<>(clientId, viewId));
        transportOfServer.send(clientId, new TransportPackageOfServer91AddView<>(viewId));
    }

    @Override
    public String toString() {
        return "TransportPackageOfClient41AddView{" +
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
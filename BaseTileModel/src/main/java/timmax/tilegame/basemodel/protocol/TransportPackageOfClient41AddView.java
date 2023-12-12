package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.protocol.server.RemoteView;
import timmax.tilegame.transport.TransportOfModel;

public class TransportPackageOfClient41AddView<T> extends TransportPackageOfClient<T> {
    private String viewId;


    public TransportPackageOfClient41AddView() {
        super();
    }

    public TransportPackageOfClient41AddView(String viewId) {
        this();
        this.viewId = viewId;
    }

    @Override
    public void execute(TransportOfModel<T> transportOfModel, T clientId) {
        System.out.println("onAddView");

        // System.out.println("viewId = " + viewId);
        transportOfModel.getModelOfServer().addRemoteView(new RemoteView<>(clientId, viewId));
        transportOfModel.send(clientId, new TransportPackageOfServer41AddView<>(viewId));
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
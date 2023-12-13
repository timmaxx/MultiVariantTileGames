package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.transport.TransportOfClient;

public class TransportPackageOfServer91AddView<T> extends TransportPackageOfServer<T> {
    private String viewId;


    public TransportPackageOfServer91AddView() {
        super();
    }

    public TransportPackageOfServer91AddView(String viewId) {
        this();
        this.viewId = viewId;
    }

    @Override
    public void execute(TransportOfClient<T> transportOfClient) {
        System.out.println("  onAddView");

        System.out.println("    viewId = " + viewId);

        transportOfClient.getClientState().confirmView(viewId);
    }

    @Override
    public String toString() {
        return "TransportPackageOfServer41AddView{" +
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
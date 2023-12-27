package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.transport.TransportOfClient;

public class EventOfServer91AddView extends EventOfServer {
    private String viewId;

    public EventOfServer91AddView() {
        super();
    }

    public EventOfServer91AddView(String viewId) {
        this();
        this.viewId = viewId;
    }

    @Override
    public void executeOnClient(TransportOfClient transportOfClient) {
        System.out.println("  onAddView");

        System.out.println("    viewId = " + viewId);

        transportOfClient.getLocalClientState().confirmView(viewId);
    }

    @Override
    public String toString() {
        return "EventOfServer41AddView{" +
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

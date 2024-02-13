package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.baseview.View;
import timmax.tilegame.transport.TransportOfClient;

public class EventOfServer91AddView extends EventOfServer {
    private Class<? extends View> classOfView;
    private String viewName;

    public EventOfServer91AddView() {
        super();
    }

    public EventOfServer91AddView(Class<? extends View> classOfView, String viewName) {
        this();
        this.classOfView = classOfView;
        this.viewName = viewName;
    }

    @Override
    public void executeOnClient(TransportOfClient transportOfClient) {
        System.out.println("  onAddView");
        transportOfClient.getLocalClientState().addView(classOfView, viewName);
    }

    @Override
    public String toString() {
        return "EventOfServer91AddView{" +
                "classOfView=" + classOfView +
                ", viewName='" + viewName + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(classOfView);
        out.writeObject(viewName);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        classOfView = (Class<? extends View>) in.readObject();
        viewName = (String) in.readObject();
    }
}

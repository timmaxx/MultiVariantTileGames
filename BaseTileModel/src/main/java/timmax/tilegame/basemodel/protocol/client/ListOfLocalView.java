package timmax.tilegame.basemodel.protocol.client;

import java.util.ArrayList;

import timmax.tilegame.baseview.View;
import timmax.tilegame.transport.TransportOfClient;

public class ListOfLocalView extends ArrayList<View> {
    private TransportOfClient transportOfClient;

    public ListOfLocalView(TransportOfClient transportOfClient) {
        super();
        this.transportOfClient = transportOfClient;
    }

    public View getViewByViewId(String viewId) {
        for (View view : this) {
            if (view.toString().equals(viewId)) {
                return view;
            }
        }
        return null;
    }
}

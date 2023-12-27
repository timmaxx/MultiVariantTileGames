package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.AbstractClientState;
import timmax.tilegame.baseview.View;
import timmax.tilegame.transport.TransportOfClient;

public class LocalClientState extends AbstractClientState {
    private final ListOfLocalView listOfLocalView;

    public LocalClientState(TransportOfClient transportOfClient) {
        this.listOfLocalView = new ListOfLocalView(transportOfClient);
    }

    public void confirmView(String viewId) {
        if (listOfLocalView.contains(viewId)) {
            System.out.println("if (listOfLocalView.contains(viewId))");
        } else {
            System.out.println("else (listOfLocalView.contains(viewId))");
        }
    }

    public void addView(View view) {
        listOfLocalView.add(view);
    }

    public ListOfLocalView getListOfLocalView() {
        return listOfLocalView;
    }
}

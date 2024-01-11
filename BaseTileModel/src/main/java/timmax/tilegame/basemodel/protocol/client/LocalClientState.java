package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.AbstractClientState;
import timmax.tilegame.baseview.View;
import timmax.tilegame.transport.TransportOfClient;

public class LocalClientState extends AbstractClientState<String> {
    private final ListOfLocalView listOfLocalView;

    public LocalClientState(TransportOfClient transportOfClient) {
        this.listOfLocalView = new ListOfLocalView(transportOfClient);
    }

    public ListOfLocalView getListOfLocalView() {
        return listOfLocalView;
    }

    public void addView(View view) {
        listOfLocalView.add(view);
    }

    public void confirmView(String viewId) {
        if (listOfLocalView.contains(viewId)) {
            System.out.println("confirmView ok.");
        } else {
            System.err.println("confirmView is not ok!");
        }
    }
}

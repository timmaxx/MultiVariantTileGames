package timmax.tilegame.basemodel.protocol.client;

import java.util.Set;

import timmax.tilegame.basemodel.protocol.AbstractClientState;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.baseview.View;

public class LocalClientState extends AbstractClientState<InstanceIdOfModel> {
    private final IModelOfClient iModelOfClient;
    private final ListOfLocalView listOfLocalView;

    public LocalClientState(IModelOfClient iModelOfClient) {
        this.iModelOfClient = iModelOfClient;
        this.listOfLocalView = new ListOfLocalView();
    }

    // ---- 2 (Пользователь)
    @Override
    public void forgetUserName() {
        super.forgetUserName();
        iModelOfClient.getHashSetOfObserverOnAbstractEvent().updateOnLogout();
    }

    @Override
    public void setUserName(String userName) {
        super.setUserName(userName);
        iModelOfClient.getHashSetOfObserverOnAbstractEvent().updateOnLogin();
    }

    // ---- 3 (Список типов игр)
    @Override
    public void forgetGameTypeSet() {
        super.forgetGameTypeSet();
        iModelOfClient.getHashSetOfObserverOnAbstractEvent().updateOnForgetGameTypeSet();
    }

    @Override
    public void setGameTypeSet(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        super.setGameTypeSet(setOfModelOfServerDescriptor);
        iModelOfClient.getHashSetOfObserverOnAbstractEvent().updateOnGetGameTypeSet();
    }

    public ListOfLocalView getListOfLocalView() {
        return listOfLocalView;
    }

    public void addView(View view) {
        listOfLocalView.add(view);
    }

    public void confirmView(String viewId) {
        if (listOfLocalView.getViewByViewId(viewId) != null) {
            System.out.println("confirmView ok.");
        } else {
            System.err.println("confirmView is not ok!");
        }
    }
}

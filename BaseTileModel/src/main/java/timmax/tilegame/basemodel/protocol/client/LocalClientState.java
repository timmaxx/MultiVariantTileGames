package timmax.tilegame.basemodel.protocol.client;

import java.util.List;
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
    public void setUserName(String userName) {
        super.setUserName(userName);
        iModelOfClient.getHashSetOfObserverOnAbstractEvent().updateOnLogin();
    }

    @Override
    public void forgetUserName() {
        super.forgetUserName();
        iModelOfClient.getHashSetOfObserverOnAbstractEvent().updateOnLogout();
    }

    // ---- 3 (Список типов игр)
    @Override
    public void setGameTypeSet(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        super.setGameTypeSet(setOfModelOfServerDescriptor);
        iModelOfClient.getHashSetOfObserverOnAbstractEvent().updateOnGetGameTypeSet();
    }

    @Override
    public void forgetGameTypeSet() {
        super.forgetGameTypeSet();
        iModelOfClient.getHashSetOfObserverOnAbstractEvent().updateOnForgetGameTypeSet();
    }

    // ---- 4 (Конкретный тип игры)
    @Override
    public void setGameType(ModelOfServerDescriptor modelOfServerDescriptor) {
        super.setGameType(modelOfServerDescriptor);
        iModelOfClient.getHashSetOfObserverOnAbstractEvent().updateOnSelectGameType();
    }

    public void forgetGameType() {
        super.forgetGameType();
        iModelOfClient.getHashSetOfObserverOnAbstractEvent().updateOnForgetGameType();
    }

    // ---- 5 (Список моделей игр)
    @Override
    public void setGamePlaySet(List<InstanceIdOfModel> listOfServerBaseModel) {
        super.setGamePlaySet(listOfServerBaseModel);
        iModelOfClient.getHashSetOfObserverOnAbstractEvent().updateOnGetGamePlaySet();
    }
/*
    public void forgetGamePlaySet() {
        setGamePlaySet_(null);
    }
*/
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

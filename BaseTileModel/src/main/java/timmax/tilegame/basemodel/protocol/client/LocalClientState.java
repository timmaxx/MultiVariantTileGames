package timmax.tilegame.basemodel.protocol.client;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

import timmax.tilegame.basecontroller.BaseController;
import timmax.tilegame.basemodel.protocol.AbstractClientState;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.baseview.View;

public abstract class LocalClientState extends AbstractClientState<InstanceIdOfModel> {
    private final IModelOfClient iModelOfClient;
    private final BaseController baseController;
    private final ListOfLocalView listOfLocalView;

    public LocalClientState(IModelOfClient iModelOfClient, BaseController baseController) {
        this.iModelOfClient = iModelOfClient;
        this.baseController = baseController;
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
    public void setGameMatchSet(List<InstanceIdOfModel> listOfServerBaseModel) {
        super.setGameMatchSet(listOfServerBaseModel);
        iModelOfClient.getHashSetOfObserverOnAbstractEvent().updateOnGetGameMatchSet();
    }

    @Override
    public void forgetGameMatchSet() {
        super.forgetGameMatchSet();
        iModelOfClient.getHashSetOfObserverOnAbstractEvent().updateOnForgetGameMatchSet();
    }

    // ---- 6 (Конкретная модель игры)
    @Override
    public void setServerBaseModel(InstanceIdOfModel serverBaseModel) {
        super.setServerBaseModel(serverBaseModel);
        iModelOfClient.getHashSetOfObserverOnAbstractEvent().updateOnSelectGameMatch();
    }

    @Override
    public void forgetServerBaseModel() {
        super.forgetServerBaseModel();
        iModelOfClient.getHashSetOfObserverOnAbstractEvent().updateOnForgetGameMatch();
    }

    // ---- 7
    @Override
    public void setGameIsPlaying(Boolean gameIsPlaying) {
        super.setGameIsPlaying(gameIsPlaying);
        iModelOfClient.getHashSetOfObserverOnAbstractEvent().updateOnStartGameMatchPlaying();
    }

    @Override
    public void forgetGameIsPlaying() {
        super.forgetGameIsPlaying();
        iModelOfClient.getHashSetOfObserverOnAbstractEvent().updateOnStopGameMatchPlaying();
    }

    // Own methods
    public ListOfLocalView getListOfLocalView() {
        return listOfLocalView;
    }

    public void addView(View view) {
        listOfLocalView.add(view);
    }

    public void addView(Class<? extends View> classOfView, String viewName) {
        Constructor<? extends View> constructorOfView;
        constructorOfView = getViewConstructor(classOfView, baseController, viewName);
        try {
            constructorOfView.newInstance(iModelOfClient, baseController, viewName);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract Constructor<? extends View> getViewConstructor(Class<? extends View> classOfView, BaseController baseController, String typeIdName);
}

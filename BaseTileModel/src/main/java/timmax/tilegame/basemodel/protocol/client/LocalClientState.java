package timmax.tilegame.basemodel.protocol.client;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import timmax.tilegame.basecontroller.BaseController;
import timmax.tilegame.basemodel.protocol.AbstractClientState;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.baseview.View;

public abstract class LocalClientState extends AbstractClientState<InstanceIdOfModel> {
    private final IModelOfClient iModelOfClient;
    private final BaseController baseController;
    private final Map<String, Class<? extends View>> mapOfVieName_ViewClass;
    private final Map<String, View> mapOfVieName_View;

    public LocalClientState(IModelOfClient iModelOfClient, BaseController baseController) {
        this.iModelOfClient = iModelOfClient;
        this.baseController = baseController;
        this.mapOfVieName_ViewClass = new HashMap<>();
        this.mapOfVieName_View = new HashMap<>();
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

    // ---- 5 (Набор моделей игр)
    @Override
    public void setGameMatchSet(Set<InstanceIdOfModel> setOfServerBaseModel) {
        super.setGameMatchSet(setOfServerBaseModel);
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
    public Map<String, View> getMapOfVieName_View() {
        return mapOfVieName_View;
    }

    public Map<String, Class<? extends View>> getMapOfVieName_ViewClass() {
        return mapOfVieName_ViewClass;
    }

    public void addView(View view) {
        mapOfVieName_View.put(view.getViewName(), view);
    }

    public abstract Constructor<? extends View> getViewConstructor(
            Class<? extends View> classOfView, BaseController baseController, String viewName
    );
}

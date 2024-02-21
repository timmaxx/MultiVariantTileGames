package timmax.tilegame.basemodel.protocol.client;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import timmax.tilegame.basemodel.protocol.AbstractClientState;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.baseview.View;

public abstract class LocalClientState extends AbstractClientState<InstanceIdOfModel> {
    private final IModelOfClient iModelOfClient;
    private final BaseController baseController;
    private Map<String, View> mapOfViewName_View;

    public LocalClientState(IModelOfClient iModelOfClient) {
        super();
        this.iModelOfClient = iModelOfClient;
        this.baseController = baseController;
        this.mapOfViewName_View = new HashMap<>();
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
        if (modelOfServerDescriptor != null) {
            // ToDo: Создать локальные выборки, в соответствии с тем, что задано в modelOfServerDescriptor
            mapOfViewName_View = new HashMap<>();
            for (Map.Entry<String, Class<? extends View>> entry : modelOfServerDescriptor.getMapOfViewNameViewClass().entrySet()) {
                Constructor<? extends View> constructor = getViewConstructor(entry.getValue());
                View view;
                try {
                    view = constructor.newInstance(iModelOfClient, baseController, entry.getKey());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
                mapOfViewName_View.put(entry.getKey(), view);
            }
        } else {
            mapOfViewName_View = null;
        }
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
    public Map<String, View> getMapOfViewName_View() {
        return mapOfViewName_View;
    }

    public void addView(View view) {
        mapOfViewName_View.put(view.getViewName(), view);
    }

    public abstract Constructor<? extends View> getViewConstructor(
            Class<? extends View> classOfView
    );
}

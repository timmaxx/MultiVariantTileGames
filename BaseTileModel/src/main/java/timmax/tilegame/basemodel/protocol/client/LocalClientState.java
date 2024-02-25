package timmax.tilegame.basemodel.protocol.client;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import timmax.tilegame.basemodel.protocol.AbstractClientState;
import timmax.tilegame.basemodel.protocol.HashSetOfObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.ObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.baseview.View;

public abstract class LocalClientState extends AbstractClientState<InstanceIdOfModel> {
    private final HashSetOfObserverOnAbstractEvent hashSetOfObserverOnAbstractEvent;
    private Map<String, View> mapOfViewName_View;

    public LocalClientState() {
        super();
        this.hashSetOfObserverOnAbstractEvent = new HashSetOfObserverOnAbstractEvent();
        this.mapOfViewName_View = new HashMap<>();
    }

    public Map<String, View> getMapOfViewName_View() {
        return mapOfViewName_View;
    }

    public void addView(View view) {
        mapOfViewName_View.put(view.getViewName(), view);
    }

    public abstract Constructor<? extends View> getViewConstructor(
            Class<? extends View> classOfView
    );

    public HashSetOfObserverOnAbstractEvent getHashSetOfObserverOnAbstractEvent() {
        return hashSetOfObserverOnAbstractEvent;
    }

    public void addCallBackOnIncomingTransportPackageEvent(ObserverOnAbstractEvent observerOnAbstractEvent) {
        hashSetOfObserverOnAbstractEvent.add(observerOnAbstractEvent);
    }

    // Overriden methods of class AbstractClientState
    // ---- 2 (Пользователь)
    @Override
    public void setUserName(String userName) {
        super.setUserName(userName);
        hashSetOfObserverOnAbstractEvent.updateOnLogin();
    }

    @Override
    public void forgetUserName() {
        super.forgetUserName();
        hashSetOfObserverOnAbstractEvent.updateOnLogout();
    }

    // ---- 3 (Список типов игр)
    @Override
    public void setGameTypeSet(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        super.setGameTypeSet(setOfModelOfServerDescriptor);
        hashSetOfObserverOnAbstractEvent.updateOnGetGameTypeSet();
    }

    @Override
    public void forgetGameTypeSet() {
        super.forgetGameTypeSet();
        hashSetOfObserverOnAbstractEvent.updateOnForgetGameTypeSet();
    }

    // ---- 4 (Конкретный тип игры)
    @Override
    public void setGameType(ModelOfServerDescriptor modelOfServerDescriptor) {
        super.setGameType(modelOfServerDescriptor);
        if (modelOfServerDescriptor != null) {
            mapOfViewName_View = new HashMap<>();
        } else {
            mapOfViewName_View = null;
        }
        hashSetOfObserverOnAbstractEvent.updateOnSelectGameType();
    }

    @Override
    public void forgetGameType() {
        super.forgetGameType();
        hashSetOfObserverOnAbstractEvent.updateOnForgetGameType();
    }

    // ---- 5 (Набор моделей игр)
    @Override
    public void setGameMatchSet(Set<InstanceIdOfModel> setOfServerBaseModel) {
        super.setGameMatchSet(setOfServerBaseModel);
        hashSetOfObserverOnAbstractEvent.updateOnGetGameMatchSet();
    }

    @Override
    public void forgetGameMatchSet() {
        super.forgetGameMatchSet();
        hashSetOfObserverOnAbstractEvent.updateOnForgetGameMatchSet();
    }

    // ---- 6 (Конкретная модель игры)
    @Override
    public void setServerBaseModel(InstanceIdOfModel serverBaseModel) {
        super.setServerBaseModel(serverBaseModel);
        hashSetOfObserverOnAbstractEvent.updateOnSelectGameMatch();
    }

    @Override
    public void forgetServerBaseModel() {
        super.forgetServerBaseModel();
        hashSetOfObserverOnAbstractEvent.updateOnForgetGameMatch();
    }

    // ---- 7
    @Override
    public void setGameIsPlaying(Boolean gameIsPlaying) {
        super.setGameIsPlaying(gameIsPlaying);
        hashSetOfObserverOnAbstractEvent.updateOnStartGameMatchPlaying();
    }

    @Override
    public void forgetGameIsPlaying() {
        super.forgetGameIsPlaying();
        hashSetOfObserverOnAbstractEvent.updateOnStopGameMatchPlaying();
    }
}

package timmax.tilegame.basemodel.protocol.server_client;

import java.util.Map;
import java.util.Set;

import timmax.tilegame.basemodel.protocol.cs.IClientState;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;

public abstract class AbstractClientState<Model> implements IClientState<Model> {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:
    protected String userName; // ---- 2 (Пользователь)
    protected Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor; // ---- 3 (Список типов игр)
    protected ModelOfServerDescriptor modelOfServerDescriptor; // ---- 4 (Конкретный тип игры)
    protected Set<Model> setOfServerBaseModel; // ---- 5 (Набор моделей игр)
    protected Model serverBaseModel; // ---- 6 (Конкретная модель игры)
    protected Boolean gameIsPlaying; // ---- 7 (Партия была начата)

    private Map<String, Integer> mapOfParamsOfModelValue;

    public void setMapOfParamsOfModelValue(Map<String, Integer> mapOfParamsOfModelValue) {
        this.mapOfParamsOfModelValue = mapOfParamsOfModelValue;
    }

    public Map<String, Integer> getMapOfParamsOfModelValue() {
        return mapOfParamsOfModelValue;
    }

    // ---- 2 (Пользователь)
    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        if (userName == null || userName.isEmpty()) {
            throw new NullPointerException("UserName is null. It must be not null for this method.");
        }
        setUserName_(userName);
    }

    @Override
    public void forgetUserName() {
        setUserName_("");
    }

    private void setUserName_(String userName) {
        setGameTypeSet_(null);
        this.userName = userName;
    }

    // ---- 3 (Список типов игр)
    @Override
    public Set<ModelOfServerDescriptor> getGameTypeSet() {
        return setOfModelOfServerDescriptor;
    }

    @Override
    public void setGameTypeSet(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        // ToDo: сделать какую-то проверку на корректностность.
        setGameTypeSet_(setOfModelOfServerDescriptor);
    }

    @Override
    public void forgetGameTypeSet() {
        setGameTypeSet_(null);
    }

    private void setGameTypeSet_(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        setGameType_(null);
        this.setOfModelOfServerDescriptor = setOfModelOfServerDescriptor;
    }

    // ---- 4 (Конкретный тип игры)
    @Override
    public ModelOfServerDescriptor getGameType() {
        return modelOfServerDescriptor;
    }

    @Override
    public void setGameType(ModelOfServerDescriptor modelOfServerDescriptor) {
        setGameType_(modelOfServerDescriptor);
    }

    @Override
    public void forgetGameType() {
        setGameType_(null);
    }

    private void setGameType_(ModelOfServerDescriptor modelOfServerDescriptor) {
        setGameMatchSet_(null);
        this.modelOfServerDescriptor = modelOfServerDescriptor;
    }

    // ---- 5 (Набор моделей игр)
    @Override
    public Set<Model> getGameMatchSet() {
        return setOfServerBaseModel;
    }

    @Override
    public void setGameMatchSet(Set<Model> setOfServerBaseModel) {
        setGameMatchSet_(setOfServerBaseModel);
    }

    @Override
    public void forgetGameMatchSet() {
        setGameMatchSet_(null);
    }

    private void setGameMatchSet_(Set<Model> setOfServerBaseModel) {
        setServerBaseModel_(null);
        this.setOfServerBaseModel = setOfServerBaseModel;
    }

    // ---- 6 (Конкретная модель игры)
    @Override
    public Model getServerBaseModel() {
        return serverBaseModel;
    }

    @Override
    public void setServerBaseModel(Model serverBaseModel) {
        setServerBaseModel_(serverBaseModel);
    }

    @Override
    public void forgetServerBaseModel() {
        setServerBaseModel_(null);
    }

    private void setServerBaseModel_(Model serverBaseModel) {
        setGameIsPlaying_(null);
        this.serverBaseModel = serverBaseModel;
    }

    // ---- 7
    @Override
    public Boolean getGameIsPlaying() {
        return gameIsPlaying;
    }

    @Override
    public void setGameIsPlaying(Boolean gameIsPlaying) {
        setGameIsPlaying_(gameIsPlaying);
    }

    @Override
    public void forgetGameIsPlaying() {
        setGameIsPlaying_(null);
    }

    private void setGameIsPlaying_(Boolean gameIsPlaying) {
        this.gameIsPlaying = gameIsPlaying;
    }

    // ---- X
    @Override
    public MainGameClientStatus getMainGameClientStatus() {
        if (gameIsPlaying != null && gameIsPlaying) {
            return MainGameClientStatus.GAME_IS_PLAYING;
        } else if (serverBaseModel != null) {
            return MainGameClientStatus.GAME_MATCH_SELECTED;
        } // Проверка на длину набора закоментирована, т.к. предполагается, что пользователь может иметь возможность
          // создавать партию даже если он не получил набор готовых партий.
          else if (setOfServerBaseModel != null /*&& setOfServerBaseModel.size() > 0*/) {
            return MainGameClientStatus.GAME_MATCH_SET_SELECTED;
        } else if (modelOfServerDescriptor != null) {
            return MainGameClientStatus.GAME_TYPE_SELECTED;
        } // Проверка на длину списка не закоментирована, т.к. предполагается, что пользователь не может сам создать
          // новый тип игры. Что-бы это стало возможным, нужно что-бы сервер смог загрузить ещё один класс наследник
          // ModelOfServer. При развитии фреймворка возможно, но не сейчас.
          else if (setOfModelOfServerDescriptor != null && setOfModelOfServerDescriptor.size() > 0) {
            return MainGameClientStatus.GAME_TYPE_SET_SELECTED;
        } else if (userName != null && !userName.isEmpty()) {
            return MainGameClientStatus.CONNECT_AUTHORIZED;
        }
        return MainGameClientStatus.CONNECT_NON_IDENT;
    }
}

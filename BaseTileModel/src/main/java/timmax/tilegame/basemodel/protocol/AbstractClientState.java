package timmax.tilegame.basemodel.protocol;

import java.util.ArrayList;
import java.util.List;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;

public abstract class AbstractClientState<Model> {
    protected String userName = ""; // ---- 1 (Пользователь)
    // ---- 2 (Список типов игр)
    // ---- 3 (Конкретный тип игры)
    protected List<Model> listOfServerBaseModel = new ArrayList<>(); // ---- 4 (Список моделей игр)
    protected Model serverBaseModel = null; // ---- 5 (Конкретная модель игры)

    // ---- 1 (Пользователь)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (userName == null || userName.isEmpty()) {
            throw new NullPointerException("UserName is null. It must be not null for this method.");
        }
        setUserName_(userName);
    }

    public void forgetUserName() {
        setUserName_("");
    }

    private void setUserName_(String userName) {
        // setGameTypeSet_(null);
        this.userName = userName;
    }

    // ---- 2 (Список типов игр)
    public void getGameTypeSet() {
        // ToDo: Нет переменной, которую нужно вернуть. Создать.
    }
/*
    public void setGameTypeSet(??? ) {
        setGameTypeSet_(???);
    }
*/
    public void forgetGameTypeSet() {
        // ToDo: Нет переменной, которую нужно очищать. Создать. Очистить.
        // setGameTypeSet_(null);
    }
/*
    private void setGameTypeSet_(???) {
        // ToDo: Нет переменной, которую нужно установить. Создать. Инициализировать.
    }
*/
    // ---- 3 (Конкретный тип игры)
    // Наверное не Model должен быть типом. Вероятнее это ModelOfServerDescriptor
    public Model getGameType() {
        return serverBaseModel;
    }

    public void setGameType(Model modelOfServer) {
        setGameType_(modelOfServer);
    }

    public void forgetGameType() {
        setGameType_(null);
    }

    private void setGameType_(Model modelOfServer) {
        this.serverBaseModel = modelOfServer;
    }

    // ---- 4 (Список моделей игр)
    public List<Model> getListOfServerBaseModel() {
        return listOfServerBaseModel;
    }

    public void setListOfServerBaseModel(List<Model> listOfServerBaseModel) {
        setListOfServerBaseModel_(listOfServerBaseModel);
    }

    public void forgetListOfServerBaseModel() {
        setListOfServerBaseModel_(new ArrayList<>());
    }

    private void setListOfServerBaseModel_(List<Model> listOfServerBaseModel) {
        forgetServerBaseModel();
        this.listOfServerBaseModel = listOfServerBaseModel;
    }

    // ---- 5 (Конкретная модель игры)
    public Model getServerBaseModel() {
        return serverBaseModel;
    }

    public void setServerBaseModel(Model serverBaseModel) {
        setServerBaseModel_(serverBaseModel);
    }

    public void forgetServerBaseModel() {
        setServerBaseModel_(null);
    }

    private void setServerBaseModel_(Model serverBaseModel) {
        this.serverBaseModel = serverBaseModel;
    }

    // ---- X
    public MainGameClientStatus getMainGameClientStatus() {
        if (userName.equals("")) {
            return MainGameClientStatus.CONNECT_NON_IDENT;
        } else {
            if (listOfServerBaseModel.size() > 0) {
                if (serverBaseModel != null) {
                    return MainGameClientStatus.GAME_TYPE_SELECT;
                }
                return MainGameClientStatus.GET_GAME_TYPE_SET;
            }
            return MainGameClientStatus.CONNECT_AUTHORIZED;
        }
        // throw new RuntimeException("Unknown state.");
    }
}

/*
package timmax.tilegame.basemodel.protocol;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;

public abstract class AbstractClientState<Model> {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:
    protected String userName = ""; // ---- 2 (Пользователь)
    protected Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor = new HashSet<>(); // ---- 3 (Список типов игр)
    protected ModelOfServerDescriptor modelOfServerDescriptor; // ---- 4 (Конкретный тип игры)
    protected List<Model> listOfServerBaseModel = new ArrayList<>(); // ---- 5 (Список моделей игр)
    protected Model serverBaseModel = null; // ---- 6 (Конкретная модель игры)

    // ---- 2 (Пользователь)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (userName == null || userName.isEmpty()) {
            throw new NullPointerException("UserName is null. It must be not null for this method.");
        }
        setUserName_(userName);
    }

    public void forgetUserName() {
        setUserName_("");
    }

    private void setUserName_(String userName) {
        setGameTypeSet_(null);
        this.userName = userName;
    }

    // ---- 3 (Список типов игр)
    public Set<ModelOfServerDescriptor> getGameTypeSet() {
        return setOfModelOfServerDescriptor;
    }

    public void setGameTypeSet(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        // ToDo: сделать какую-то проверку на корректностность.
        setGameTypeSet_(setOfModelOfServerDescriptor);
    }

    public void forgetGameTypeSet() {
        setGameTypeSet_(null);
    }

    private void setGameTypeSet_(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        this.setOfModelOfServerDescriptor = setOfModelOfServerDescriptor;
    }

    // ---- 4 (Конкретный тип игры)
    // Наверное не Model должен быть типом. Вероятнее это ModelOfServerDescriptor
    public Model getGameType() {
        return serverBaseModel;
    }

    public void setGameType(Model modelOfServer) {
        setGameType_(modelOfServer);
    }

    public void forgetGameType() {
        setGameType_(null);
    }

    private void setGameType_(Model modelOfServer) {
        this.serverBaseModel = modelOfServer;
    }

    // ---- 5 (Список моделей игр)
    public List<Model> getListOfServerBaseModel() {
        return listOfServerBaseModel;
    }

    public void setListOfServerBaseModel(List<Model> listOfServerBaseModel) {
        setListOfServerBaseModel_(listOfServerBaseModel);
    }

    public void forgetListOfServerBaseModel() {
        setListOfServerBaseModel_(new ArrayList<>());
    }

    private void setListOfServerBaseModel_(List<Model> listOfServerBaseModel) {
        forgetServerBaseModel();
        this.listOfServerBaseModel = listOfServerBaseModel;
    }

    // ---- 6 (Конкретная модель игры)
    public Model getServerBaseModel() {
        return serverBaseModel;
    }

    public void setServerBaseModel(Model serverBaseModel) {
        setServerBaseModel_(serverBaseModel);
    }

    public void forgetServerBaseModel() {
        setServerBaseModel_(null);
    }

    private void setServerBaseModel_(Model serverBaseModel) {
        this.serverBaseModel = serverBaseModel;
    }

    // ---- X
    public MainGameClientStatus getMainGameClientStatus() {
        if (userName.equals("")) {
            return MainGameClientStatus.CONNECT_NON_IDENT;
        } else {
            if (listOfServerBaseModel.size() > 0) {
                if (serverBaseModel != null) {
                    return MainGameClientStatus.GAME_TYPE_SELECT;
                }
                return MainGameClientStatus.GET_GAME_TYPE_SET;
            }
            return MainGameClientStatus.CONNECT_AUTHORIZED;
        }
        // throw new RuntimeException("Unknown state.");
    }
}
*/

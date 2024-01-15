package timmax.tilegame.basemodel.protocol;

import java.util.ArrayList;
import java.util.List;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;

public abstract class AbstractClientState<Model> {
    protected String userName = "";
    protected List<Model> listOfServerBaseModel = new ArrayList<>();
    protected Model serverBaseModel = null;

    // ---- 1
/*
    public String getUserName() {
        return userName;
    }
*/
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
        forgetGameTypeSet_();
        this.userName = userName;
    }

    // ---- 2
    public void forgetGameTypeSet() {
        forgetGameTypeSet_();
    }

    private void forgetGameTypeSet_() {
        // ToDo: Нет переменной, которую нужно очищать. Создать. Очистить.
    }

    protected void getGameTypeSet() {
        // ToDo: Нет переменной, которую нужно вернуть. Создать.
    }

    // 3
    public void forgetGameType() {
        // ToDo: Нет переменной, которую нужно очищать. Создать. Очистить.
    }

    public void gameTypeSelect(Model modelOfServer) {
        this.serverBaseModel = modelOfServer;
    }

    // ---- 4
    public List<Model> getListOfServerBaseModel() {
        return listOfServerBaseModel;
    }

    public void forgetListOfServerBaseModel() {
        forgetListOfServerBaseModel_();
    }

    private void forgetListOfServerBaseModel_() {
        forgetServerBaseModel();
        this.listOfServerBaseModel = new ArrayList<>();
    }

    // ---- 5
    public Model getServerBaseModel() {
        return serverBaseModel;
    }

    public void setServerBaseModel(Model serverBaseModel) {
        setServerBaseModel_(serverBaseModel);
    }

    private void setServerBaseModel_(Model serverBaseModel) {
        this.serverBaseModel = serverBaseModel;
    }

    public void forgetServerBaseModel() {
        forgetServerBaseModel_();
    }

    private void forgetServerBaseModel_() {
        setServerBaseModel(null);
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

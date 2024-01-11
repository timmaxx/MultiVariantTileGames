package timmax.tilegame.basemodel.protocol;

import java.util.ArrayList;
import java.util.List;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;

public abstract class AbstractClientState<T> {
    protected String userName = "";
    protected List<T> listOfServerBaseModel = new ArrayList<>();
    protected T serverBaseModel = null;

    public void setUserName(String userName) {
        newListOfServerBaseModel();
        this.userName = userName;
    }

    public List<T> getListOfServerBaseModel() {
        return listOfServerBaseModel;
    }

    public T getServerBaseModel() {
        return serverBaseModel;
    }

    public void setServerBaseModel(T serverBaseModel) {
        this.serverBaseModel = serverBaseModel;
    }

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

    public void forgetUserName() {
        setUserName("");
    }

    public void newListOfServerBaseModel() {
        forgetServerBaseModelString();
        this.listOfServerBaseModel = new ArrayList<>();
    }

    public void forgetServerBaseModelString() {
        this.serverBaseModel = null;
    }

    public boolean addServerBaseModelClass(T serverBaseModelString) {
        return listOfServerBaseModel.add(serverBaseModelString);
    }
}

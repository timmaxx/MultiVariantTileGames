package timmax.tilegame.basemodel.protocol;

import java.util.ArrayList;
import java.util.List;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;

public abstract class AbstractClientState<ClienId> {
    protected String userName = "";
    protected List<ClienId> listOfServerBaseModel = new ArrayList<>();
    protected ClienId serverBaseModel = null;

    public void setUserName(String userName) {
        newListOfServerBaseModel();
        this.userName = userName;
    }

    public List<ClienId> getListOfServerBaseModel() {
        return listOfServerBaseModel;
    }

    public ClienId getServerBaseModel() {
        return serverBaseModel;
    }

    public void setServerBaseModel(ClienId serverBaseModel) {
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
        forgetServerBaseModel();
        this.listOfServerBaseModel = new ArrayList<>();
    }

    public void forgetServerBaseModel() {
        setServerBaseModel(null);
    }

    public boolean addServerBaseModelClass(ClienId serverBaseModelString) {
        return listOfServerBaseModel.add(serverBaseModelString);
    }
}

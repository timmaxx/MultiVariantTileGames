package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.ServerBaseModel;
import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;

import java.util.ArrayList;
import java.util.List;

public class ClientState {
    private String userName = "";
    private List<Class<? extends ServerBaseModel>> arrayListOfServerBaseModelClass = new ArrayList<>();
    private Class<? extends ServerBaseModel> serverBaseModelClass = null;


    public void setUserName(String userName) {
        serverBaseModelClass = null;
        this.arrayListOfServerBaseModelClass = new ArrayList<>();
        this.userName = userName;
    }

    public List<Class<? extends ServerBaseModel>> getArrayListOfServerBaseModelClass() {
        return arrayListOfServerBaseModelClass;
    }

    public void setArrayListOfServerBaseModelClass(List<Class<? extends ServerBaseModel>> arrayListOfServerBaseModelClass) {
        serverBaseModelClass = null;
        this.arrayListOfServerBaseModelClass = arrayListOfServerBaseModelClass;
    }

    public Class<? extends ServerBaseModel> getServerBaseModelClass() {
        return serverBaseModelClass;
    }

    public void setServerBaseModelClass(Class<? extends ServerBaseModel> serverBaseModelClass) {
        this.serverBaseModelClass = serverBaseModelClass;
    }

    public MainGameClientStatus getMainGameClientStatus() {
        if (userName.equals("")) {
            return MainGameClientStatus.CONNECT_NON_IDENT;
        } else {
            if (arrayListOfServerBaseModelClass.size() > 0) {
                if (serverBaseModelClass != null) {
                    return MainGameClientStatus.GAME_TYPE_SELECT;
                }
                return MainGameClientStatus.GET_GAME_TYPE_SET;
            }
            return MainGameClientStatus.CONNECT_AUTHORIZED;
        }
        // throw new RuntimeException("Unknown state.");
    }

    public boolean addServerBaseModelClass(Class<? extends ServerBaseModel> serverBaseModelClass) {
        return arrayListOfServerBaseModelClass.add(serverBaseModelClass);
    }
}
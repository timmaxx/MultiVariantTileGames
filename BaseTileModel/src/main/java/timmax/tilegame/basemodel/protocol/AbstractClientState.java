package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractClientState {
    protected String userName = "";
    protected List<String> arrayListOfServerBaseModelClass = new ArrayList<>();
    protected String serverBaseModelClass = "";

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

    public void setUserName(String userName) {
        serverBaseModelClass = null;
        this.arrayListOfServerBaseModelClass = new ArrayList<>();
        this.userName = userName;
    }

    public List<String> getArrayListOfServerBaseModelClass() {
        return arrayListOfServerBaseModelClass;
    }

    public void setArrayListOfServerBaseModelClass(List<String> arrayListOfServerBaseModelClass) {
        serverBaseModelClass = null;
        this.arrayListOfServerBaseModelClass = arrayListOfServerBaseModelClass;
    }

    public String getServerBaseModelClass() {
        return serverBaseModelClass;
    }

    public void setServerBaseModelClass(String serverBaseModelClass) {
        this.serverBaseModelClass = serverBaseModelClass;
    }

    public boolean addServerBaseModelClass(String serverBaseModelClass) {
        return arrayListOfServerBaseModelClass.add(serverBaseModelClass);
    }
}

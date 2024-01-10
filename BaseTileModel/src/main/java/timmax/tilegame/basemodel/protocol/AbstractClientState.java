package timmax.tilegame.basemodel.protocol;

import java.util.ArrayList;
import java.util.List;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;

public abstract class AbstractClientState {
    protected String userName = "";
    protected List<String> arrayListOfServerBaseModelString = new ArrayList<>();
    protected String serverBaseModelString = "";

    public MainGameClientStatus getMainGameClientStatus() {
        if (userName.equals("")) {
            return MainGameClientStatus.CONNECT_NON_IDENT;
        } else {
            if (arrayListOfServerBaseModelString.size() > 0) {
                if (serverBaseModelString != null) {
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

    public void setUserName(String userName) {
        newArrayListOfServerBaseModelClass();
        this.userName = userName;
    }

    public List<String> getArrayListOfServerBaseModelString() {
        return arrayListOfServerBaseModelString;
    }

    public void newArrayListOfServerBaseModelClass() {
        forgetServerBaseModelString();
        this.arrayListOfServerBaseModelString = new ArrayList<>();
    }

    public String getServerBaseModelString() {
        return serverBaseModelString;
    }

    public void forgetServerBaseModelString() {
        this.serverBaseModelString = null;
    }

    public void setServerBaseModelString(String serverBaseModelString) {
        this.serverBaseModelString = serverBaseModelString;
    }

    public boolean addServerBaseModelClass(String serverBaseModelString) {
        return arrayListOfServerBaseModelString.add(serverBaseModelString);
    }
}

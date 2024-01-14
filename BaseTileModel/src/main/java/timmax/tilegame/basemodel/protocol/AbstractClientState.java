package timmax.tilegame.basemodel.protocol;

import java.util.ArrayList;
import java.util.List;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;

public abstract class AbstractClientState<ClienId> {
    protected String userName = "";
    protected List<ClienId> listOfServerBaseModel = new ArrayList<>();
    protected ClienId serverBaseModel = null;

    // ---- 1
    public void setUserName(String userName) {
        forgetListOfServerBaseModel();
        this.userName = userName;
    }

    public void forgetUserName() {
        setUserName("");
    }

    // ---- 2
    public List<ClienId> getListOfServerBaseModel() {
        return listOfServerBaseModel;
    }

    public void forgetListOfServerBaseModel() {
        forgetServerBaseModel();
        this.listOfServerBaseModel = new ArrayList<>();
    }

    // ---- 3
    public ClienId getServerBaseModel() {
        return serverBaseModel;
    }

    public void setServerBaseModel(ClienId serverBaseModel) {
        this.serverBaseModel = serverBaseModel;
    }

    public void forgetServerBaseModel() {
        setServerBaseModel(null);
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

    public boolean addServerBaseModelClass(ClienId serverBaseModelString) {
        return listOfServerBaseModel.add(serverBaseModelString);
    }
}

/*


-public abstract class AbstractClientState<ClienId> {
    protected String userName; // 1
    protected Collection<ModelOfServerDescriptor> collectionOfGameTypeSet; // 2
    // protected List<ClienId> listOfServerBaseModel; // 3
    // protected ClienId serverBaseModel; // 4

    // ---- 1
    public String getUserName() {
        return userName;
    }

-    public void setUserName(String userName) {
        if (userName == null && userName.isEmpty()) {
            throw new NullPointerException("!!!!!"); // !!!!!
        }
        setUserName_(userName);
    }

    public void forgetUserName() {
        setUserName_("");
    }

    private void setUserName_(String userName) {
        forgetGameTypeSet_();
        t-his.userName = userName;
    -}
/-*
    // ---- 2
    public void forgetGameTypeSet() {
        forgetGameTypeSet_();
    }
*-/
    private void forgetGameTypeSet_() {
        // forgetServerBaseModel();
        this.listOfServerBaseModel = new ArrayList<>();
    }

    // ---- 3
-    public List<ClienId> getListOfServerBaseModel() {
        r-eturn listOfServerBaseModel;
    -}

/-*
    // ---- 4
    -public ClienId getServerBaseModel() {
    -    return serverBaseModel;
    -}

    -public void setServerBaseModel(ClienId serverBaseModel) {
        setServerBaseModel_(serverBaseModel);
    -}

    private void setServerBaseModel_(ClienId serverBaseModel) {
        this.serverBaseModel = serverBaseModel;
    }

    public void forgetServerBaseModel() {
        setServerBaseModel_(null);
    }
*-/

    /-*
        public boolean addServerBaseModelClass(ClienId serverBaseModel) {
            return listOfServerBaseModel.add(serverBaseModel);
        }
    *-/
    // ---- 8
    -public MainGameClientStatus getMainGameClientStatus() {
    -    if (userName.equals("")) {
    -        return MainGameClientStatus.CONNECT_NON_IDENT;
    -    } else {
    -        if (listOfServerBaseModel.size() > 0) {
/-*
    -            if (serverBaseModel != null) {
    -                return MainGameClientStatus.GAME_TYPE_SELECT;
    -            }
*-/
    -            return MainGameClientStatus.GET_GAME_TYPE_SET;
    -        }
    -        return MainGameClientStatus.CONNECT_AUTHORIZED;
    -    }
    -    // throw new RuntimeException("Unknown state.");
    -}
-}
*/

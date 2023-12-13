package timmax.tilegame.basemodel.protocol;

import java.util.ArrayList;
import java.util.List;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.protocol.server.RemoteView;
import timmax.tilegame.basemodel.protocol.server.SetOfRemoteView;
import timmax.tilegame.transport.TransportOfServer;

public class ClientState<T> {
    private final SetOfRemoteView<T> setOfRemoteView;

    private String userName = "";
    private List<String> arrayListOfServerBaseModelClass = new ArrayList<>();
    private String serverBaseModelClass = "";


    public ClientState(TransportOfServer<T> transportOfServer) {
        setOfRemoteView = new SetOfRemoteView<>(transportOfServer);
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

    public boolean addServerBaseModelClass(String serverBaseModelClass) {
        return arrayListOfServerBaseModelClass.add(serverBaseModelClass);
    }

    public void addView(String viewId) {
/*
        System.out.println("ClientState. addView(String viewId)");
        System.out.println("setOfRemoteView = " + setOfRemoteView);
        for (RemoteView remoteView: setOfRemoteView) {
            System.out.println("remoteView = " + remoteView);
        }
*/
        setOfRemoteView.add(new RemoteView<>(null, viewId));
/*
        System.out.println("setOfRemoteView = " + setOfRemoteView);
        for (RemoteView remoteView: setOfRemoteView) {
            System.out.println("remoteView = " + remoteView);
        }
*/
    }

    public void confirmView(String viewId) {
/*
        System.out.println("ClientState. confirmView(String viewId)");
        System.out.println("setOfRemoteView = " + setOfRemoteView);
        for (RemoteView remoteView: setOfRemoteView) {
            System.out.println("remoteView = " + remoteView);
        }
*/
        if (setOfRemoteView.contains(new RemoteView<>(null, viewId))) {
            System.out.println("if (setOfRemoteView.contains( new RemoteView<>(null, viewId)))");
        } else {
            System.out.println("else (setOfRemoteView.contains( new RemoteView<>(null, viewId)))");
        }
    }
}
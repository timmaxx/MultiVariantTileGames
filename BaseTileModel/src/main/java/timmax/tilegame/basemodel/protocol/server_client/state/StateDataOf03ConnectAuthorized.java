package timmax.tilegame.basemodel.protocol.server_client.state;

import timmax.commons.state.StateData;

public class StateDataOf03ConnectAuthorized implements StateData {
    private final String userName; // ---- 3 (Пользователь)
    private final String password;

    public StateDataOf03ConnectAuthorized(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }
}

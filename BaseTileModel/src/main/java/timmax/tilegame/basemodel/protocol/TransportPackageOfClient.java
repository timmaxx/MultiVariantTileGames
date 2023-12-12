package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfModel;

public abstract class TransportPackageOfClient<T> extends TransportPackage {

    public abstract void execute(TransportOfModel<T> transportOfModel, T clientId);

    @Override
    public String toString() {
        return "TransportPackageOfClient{}";
    }

    // sendLogoutAnswer используется только в классе TransportPackageOfClient011Login
    protected void sendLogoutAnswer(TransportOfModel<T> transportOfModel, T clientId) {
        transportOfModel.send(clientId, new TransportPackageOfServer010Logout<>());
    }

    // sendLoginAnswer используется только в классе TransportPackageOfClient010Logout и TransportPackageOfClient010Logout
    protected void sendLoginAnswer(TransportOfModel<T> transportOfModel, T clientId, String userName) {
        transportOfModel.send(clientId, new TransportPackageOfServer011Login<>(userName));
    }
}
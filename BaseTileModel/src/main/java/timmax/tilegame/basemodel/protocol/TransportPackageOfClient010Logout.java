package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfModel;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class TransportPackageOfClient010Logout<T> extends TransportPackageOfClient {

    public TransportPackageOfClient010Logout() {
        super();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
    }

    @Override
    public void execute(TransportOfModel transportOfModel, Object clientId) {
        System.out.println("onLogout");

        sendLogoutAnswer(transportOfModel, clientId);
    }

    @Override
    public String toString() {
        return "TransportPackageOfClientLogout{}";
    }
}
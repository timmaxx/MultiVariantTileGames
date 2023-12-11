package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.transport.TransportOfController;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.LOGIN;

public class TransportPackageOfServer011Login<T> extends TransportPackageOfServer<T> {
    private /*final*/ String userName;


    public TransportPackageOfServer011Login() {
    }

    public TransportPackageOfServer011Login(String userName) {
        this.userName = userName;
    }

    @Override
    public void execute(TransportOfController<T> transportOfModel) {
        System.out.println("onLogin");

        transportOfModel.getClientState().setUserName(userName);
        transportOfModel.getHashSetOfObserverOnAbstractEvent().updateConnectStatePane(LOGIN);
    }

    @Override
    public String toString() {
        return "TransportPackageOfServer011Login{" +
                "userName='" + userName + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(userName);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        userName = (String) in.readObject();
    }
}
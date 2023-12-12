package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

import timmax.tilegame.transport.TransportOfController;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.GET_GAME_TYPE_SET;

public class TransportPackageOfServer021GetGameTypeSet<T> extends TransportPackageOfServer<T> {
    List<String> arrayListOfServerBaseModelClass = new ArrayList<>();


    public TransportPackageOfServer021GetGameTypeSet() {
        super();
    }

    public TransportPackageOfServer021GetGameTypeSet(List<String> arrayListOfServerBaseModelClass) {
        this();
        this.arrayListOfServerBaseModelClass = arrayListOfServerBaseModelClass;
    }

    @Override
    public void execute(TransportOfController<T> transportOfModel) {
        System.out.println("onGetGameTypeSet");

        transportOfModel.getClientState().setArrayListOfServerBaseModelClass(new ArrayList<>());

        for (String serverBaseModelClass : arrayListOfServerBaseModelClass) {
            transportOfModel.getClientState().addServerBaseModelClass(serverBaseModelClass);
        }
        transportOfModel.getHashSetOfObserverOnAbstractEvent().updateConnectStatePane(GET_GAME_TYPE_SET);
    }

    @Override
    public String toString() {
        return "TransportPackageOfServer021GetGameTypeSet{" +
                "arrayListOfServerBaseModelClass=" + arrayListOfServerBaseModelClass +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(arrayListOfServerBaseModelClass);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        arrayListOfServerBaseModelClass = (List<String>) in.readObject();
    }
}
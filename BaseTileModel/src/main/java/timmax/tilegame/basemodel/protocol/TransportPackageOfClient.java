package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfModel;

public abstract class TransportPackageOfClient<T> extends TransportPackage {
    // private static MapOfStructOfTransportPackageOfClient mapOfStructOfTransportPackageOfClient;


    public TransportPackageOfClient() {
        super();
    }
/*
    public TransportPackageOfClient(TypeOfTransportPackage typeOfTransportPackage) {
        super(typeOfTransportPackage);
    }

    public TransportPackageOfClient(
            TypeOfTransportPackage typeOfTransportPackage,
            Map<String, Object> mapOfParamName_Value) {
        super(typeOfTransportPackage, mapOfParamName_Value);
    }
*/
/*
    @Override
    MapOfStructOfTransportPackage initMapOfStructOfTransportPackage() {
        if (mapOfStructOfTransportPackageOfClient == null) {
            mapOfStructOfTransportPackageOfClient = new MapOfStructOfTransportPackageOfClient();
        }
        return mapOfStructOfTransportPackageOfClient;
    }
*/
    public abstract void execute(TransportOfModel<T> TransportOfModel, T clientId);

    protected void sendLogoutAnswer(TransportOfModel<T> transportOfModel, T clientId) {
        transportOfModel.send(clientId, new TransportPackageOfServer010Logout<T>());
    }

    protected void sendLoginAnswer(TransportOfModel<T> transportOfModel, T clientId, String userName) {
        transportOfModel.send(clientId, new TransportPackageOfServer011Login<T>(userName));
    }

    @Override
    public String toString() {
        return "TransportPackageOfClient{}";
    }
}
package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfController;

public abstract class TransportPackageOfServer<T> extends TransportPackage {
    // private static MapOfStructOfTransportPackageOfServer mapOfStructOfTransportPackageOfServer;


    public TransportPackageOfServer() {
        super();
    }
/*
    public TransportPackageOfServer(TypeOfTransportPackage typeOfTransportPackage) {
        super(typeOfTransportPackage);
    }

    public TransportPackageOfServer(
            TypeOfTransportPackage typeOfTransportPackage,
            Map<String, Object> mapOfParamName_Value) {
        super(typeOfTransportPackage, mapOfParamName_Value);
    }
*/
/*
    @Override
    MapOfStructOfTransportPackage initMapOfStructOfTransportPackage() {
        if (mapOfStructOfTransportPackageOfServer == null) {
            mapOfStructOfTransportPackageOfServer = new MapOfStructOfTransportPackageOfServer();
        }
        return mapOfStructOfTransportPackageOfServer;
    }
*/
    public abstract void execute(TransportOfController<T> transportOfModel);

    @Override
    public String toString() {
        return "TransportPackageOfServer{}";
    }
}
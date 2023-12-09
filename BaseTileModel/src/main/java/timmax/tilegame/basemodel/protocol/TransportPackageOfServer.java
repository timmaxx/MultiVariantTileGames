package timmax.tilegame.basemodel.protocol;

import java.util.Map;

public class TransportPackageOfServer extends TransportPackage {
    private static MapOfStructOfTransportPackageOfServer mapOfStructOfTransportPackageOfServer;


    public TransportPackageOfServer() {
        super();
    }

    public TransportPackageOfServer(TypeOfTransportPackage typeOfTransportPackage) {
        super(typeOfTransportPackage);
    }

    public TransportPackageOfServer(
            TypeOfTransportPackage typeOfTransportPackage,
            Map<String, Object> mapOfParamName_Value) {
        super(typeOfTransportPackage, mapOfParamName_Value);
    }

    @Override
    MapOfStructOfTransportPackage initMapOfStructOfTransportPackage() {
        if (mapOfStructOfTransportPackageOfServer == null) {
            mapOfStructOfTransportPackageOfServer = new MapOfStructOfTransportPackageOfServer();
        }
        return mapOfStructOfTransportPackageOfServer;
    }
}
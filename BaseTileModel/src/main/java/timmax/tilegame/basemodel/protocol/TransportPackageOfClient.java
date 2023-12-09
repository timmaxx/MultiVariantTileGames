package timmax.tilegame.basemodel.protocol;

import java.util.Map;

public class TransportPackageOfClient extends TransportPackage {
    private static MapOfStructOfTransportPackageOfClient mapOfStructOfTransportPackageOfClient;


    public TransportPackageOfClient() {
        super();
    }

    public TransportPackageOfClient(TypeOfTransportPackage typeOfTransportPackage) {
        super(typeOfTransportPackage);
    }

    public TransportPackageOfClient(
            TypeOfTransportPackage typeOfTransportPackage,
            Map<String, Object> mapOfParamName_Value) {
        super(typeOfTransportPackage, mapOfParamName_Value);
    }

    @Override
    MapOfStructOfTransportPackage initMapOfStructOfTransportPackage() {
        if (mapOfStructOfTransportPackageOfClient == null) {
            mapOfStructOfTransportPackageOfClient = new MapOfStructOfTransportPackageOfClient();
        }
        return mapOfStructOfTransportPackageOfClient;
    }
}
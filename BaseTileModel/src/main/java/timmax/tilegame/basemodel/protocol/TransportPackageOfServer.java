package timmax.tilegame.basemodel.protocol;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

public class TransportPackageOfServer extends TransportPackage {
    private static MapOfStructOfTransportPackageOfServer mapOfStructOfTransportPackageOfServer;


    public TransportPackageOfServer(TypeOfTransportPackage typeOfTransportPackage) {
        super(typeOfTransportPackage);
    }

    @JsonCreator(mode = PROPERTIES)
    public TransportPackageOfServer(
            @JsonProperty("typeOfTransportPackage") TypeOfTransportPackage typeOfTransportPackage,
            @JsonProperty("mapOfParamName_Value") Map<String, Object> mapOfParamName_Value) {
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
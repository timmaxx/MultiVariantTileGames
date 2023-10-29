package timmax.tilegame.basemodel.protocol;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

public class TransportPackageOfClient extends TransportPackage {
    private static MapOfStructOfTransportPackageOfClient mapOfStructOfTransportPackageOfClient;

    /*
        public TransportPackageOfClient(TypeOfTransportPackage typeOfTransportPackage) {
            super(typeOfTransportPackage);
        }
    */

    @JsonCreator(mode = PROPERTIES)
    public TransportPackageOfClient(
            @JsonProperty("typeOfTransportPackage") TypeOfTransportPackage typeOfTransportPackage,
            @JsonProperty("mapOfParamName_Value") Map<String, Object> mapOfParamName_Value) {
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
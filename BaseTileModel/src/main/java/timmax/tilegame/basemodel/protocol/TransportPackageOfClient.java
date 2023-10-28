package timmax.tilegame.basemodel.protocol;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

public class TransportPackageOfClient extends TransportPackage< TypeOfTransportPackageOfClient> {
    public TransportPackageOfClient( TypeOfTransportPackageOfClient typeOfTransportPackageOfClient) {
        super( typeOfTransportPackageOfClient);
    }

    @JsonCreator( mode = PROPERTIES)
    public TransportPackageOfClient(
            @JsonProperty( "inOutPackType") TypeOfTransportPackageOfClient typeOfTransportPackageOfClient,
            @JsonProperty( "mapOfParamName_Value") Map< String, Object> mapOfParamName_Value) {
        super( typeOfTransportPackageOfClient, mapOfParamName_Value);
    }

    @Override
    MapOfStructOfTransportPackage< TypeOfTransportPackageOfClient> initMapOfStructOfTransportPackage( ) {
        return new MapOfStructOfTransportPackageOfClient( );
    }
}
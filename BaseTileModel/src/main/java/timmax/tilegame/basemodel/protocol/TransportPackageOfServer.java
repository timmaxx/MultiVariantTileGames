package timmax.tilegame.basemodel.protocol;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

public class TransportPackageOfServer extends TransportPackage< TypeOfTransportPackageOfServer> {
    public TransportPackageOfServer( TypeOfTransportPackageOfServer typeOfTransportPackageOfServer) {
        super( typeOfTransportPackageOfServer);
    }

    @JsonCreator( mode = PROPERTIES)
    public TransportPackageOfServer(
            @JsonProperty( "inOutPackType") TypeOfTransportPackageOfServer typeOfTransportPackageOfServer,
            @JsonProperty( "mapOfParamName_Value") Map< String, Object> mapOfParamName_Value) {
        super( typeOfTransportPackageOfServer, mapOfParamName_Value);
    }

    @Override
    MapOfStructOfTransportPackage initMapOfStructOfTransportPackage( ) {
        return new MapOfStructOfTransportPackageOfServer( );
    }
}
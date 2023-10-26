package timmax.tilegame.basemodel.protocol;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Map;

@JsonTypeInfo( use = JsonTypeInfo.Id.CLASS, property="type")
public class TransportPackageOfServer extends TransportPackage< TypeOfTransportPackageOfServer> {
    public TransportPackageOfServer( TypeOfTransportPackageOfServer typeOfTransportPackageOfServer) {
        super( typeOfTransportPackageOfServer);
    }

    public TransportPackageOfServer( TypeOfTransportPackageOfServer typeOfTransportPackageOfServer, Map< String, Object> mapOfParamName_Value) {
        super( typeOfTransportPackageOfServer, mapOfParamName_Value);
    }
}
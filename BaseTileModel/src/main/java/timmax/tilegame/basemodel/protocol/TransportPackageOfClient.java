package timmax.tilegame.basemodel.protocol;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Map;

@JsonTypeInfo( use = JsonTypeInfo.Id.CLASS, property="type")
public class TransportPackageOfClient extends TransportPackage< TypeOfTransportPackageOfClient> {
    public TransportPackageOfClient( TypeOfTransportPackageOfClient typeOfTransportPackageOfClient) {
        super( typeOfTransportPackageOfClient);
    }

    public TransportPackageOfClient( TypeOfTransportPackageOfClient typeOfTransportPackageOfClient, Map mapOfParamName_Value) {
        super( typeOfTransportPackageOfClient, mapOfParamName_Value);
    }
}
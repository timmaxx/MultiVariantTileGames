package timmax.tilegame.basemodel.protocol;

import java.util.HashMap;
import java.util.Map;

public class MapOfStructOfTransportPackage< InOutPackType> {
    // Также см. EnumSet и EnumMap
    // https://habr.com/ru/articles/267389/
    protected final Map< InOutPackType, Map< String, Class>> mapOfUniversalType;


    public MapOfStructOfTransportPackage( ) {
        mapOfUniversalType = new HashMap< >( );
    }

    public Map< String, Class> getMapString_ClassByReqType( InOutPackType inOutPackType) {
        return mapOfUniversalType.get( inOutPackType);
    }

    public Class getClassByReqTypeAndParamName( InOutPackType inOutPackType, String paramName) {
        return mapOfUniversalType.get( inOutPackType).get( paramName);
    }
}
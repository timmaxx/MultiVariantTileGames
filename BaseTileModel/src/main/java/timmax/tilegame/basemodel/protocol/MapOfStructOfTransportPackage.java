package timmax.tilegame.basemodel.protocol;

import java.util.EnumMap;
import java.util.Map;

public class MapOfStructOfTransportPackage {
    protected final Map<TypeOfTransportPackage, Map<String, Class<?>>> mapOfUniversalType;


    public MapOfStructOfTransportPackage() {
        mapOfUniversalType = new EnumMap<>(TypeOfTransportPackage.class);
    }

    public Map<String, Class<?>> getMapParamName_ClassByTypeOfTransportPackage(TypeOfTransportPackage typeOfTransportPackage) {
        if (!mapOfUniversalType.containsKey(typeOfTransportPackage)) {
            System.err.println("There aren't key = '" + typeOfTransportPackage + "' in mapOfUniversalType.");
            System.exit(1);
        }

        return mapOfUniversalType.get(typeOfTransportPackage);
    }
/*
    public Class getClassByReqTypeAndParamName( InOutPackType inOutPackType, String paramName) {
        return mapOfUniversalType.get( inOutPackType).get( paramName);
    }
*/
}
package timmax.tilegame.basemodel.protocol;

import java.util.Map;

public abstract class MapOfStructOfTransportPackage {
    protected Map<TypeOfTransportPackage, Map<String, Class<?>>> mapOfUniversalType;


    public Map<String, Class<?>> getMapParamName_ClassByTypeOfTransportPackage(TypeOfTransportPackage typeOfTransportPackage) {
        if (!mapOfUniversalType.containsKey(typeOfTransportPackage)) {
            throw new RuntimeException("There aren't key = '" + typeOfTransportPackage + "' in mapOfUniversalType.");
        }

        return mapOfUniversalType.get(typeOfTransportPackage);
    }
/*
    public Class getClassByReqTypeAndParamName( InOutPackType inOutPackType, String paramName) {
        return mapOfUniversalType.get( inOutPackType).get( paramName);
    }
*/
}
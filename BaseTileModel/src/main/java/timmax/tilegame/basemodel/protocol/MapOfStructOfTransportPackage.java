package timmax.tilegame.basemodel.protocol;

import java.util.Collections;
import java.util.Map;

public abstract class MapOfStructOfTransportPackage {
    private final Map<TypeOfTransportPackage, Map<String, Class<?>>> mapOfUniversalType;


    public MapOfStructOfTransportPackage(Map<TypeOfTransportPackage, Map<String, Class<?>>> mapOfUniversalType) {
        this.mapOfUniversalType = Collections.unmodifiableMap(mapOfUniversalType);
    }

    public Map<String, Class<?>> getMapParamName_ClassByTypeOfTransportPackage(TypeOfTransportPackage typeOfTransportPackage) {
        if (!mapOfUniversalType.containsKey(typeOfTransportPackage)) {
            throw new RuntimeException("There aren't key = '" + typeOfTransportPackage + "' in mapOfUniversalType.");
        }

        return mapOfUniversalType.get(typeOfTransportPackage);
    }
/*
    public Class<?> getClassByReqTypeAndParamName( TypeOfTransportPackage typeOfTransportPackage, String paramName) {
        return mapOfUniversalType.get( typeOfTransportPackage).get( paramName);
    }
*/
}
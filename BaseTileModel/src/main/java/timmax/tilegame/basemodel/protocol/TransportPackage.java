package timmax.tilegame.basemodel.protocol;

import java.util.Collections;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import timmax.tilegame.basemodel.protocol.exception.MultiGameProtocolException;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

public abstract class TransportPackage {
    private final static String ERROR_MESSAGE_PARAMETERS_LIST_IS_EMPTY_BUT_IT_IS_WRONG =
            "Transport package is '%s'. Type of transport package is '%s'. There are not any parameters, but they must be %d.";
    private final static String ERROR_MESSAGE_COUNT_OF_PARAMETERS_IN_SPECIFICATION_IS_NOT_EQUAL_COUNT_OF_PARAMETERS_IN_THIS_PACKAGE =
            "\nTransport package is '%s'. \nType of transport package is '%s'. \nCount of parameters in specification of protocol is %d and it is not equal count of parameters in package %d.";
    private final static String ERROR_MESSAGE_TYPE_OF_PARAMETER_IN_SPECIFICATION_IS_NOT_EQUAL_TYPE_OF_PARAMETERS_IN_THIS_PACKAGE =
            "\nTransport package is '%s'. \nType of transport package is '%s'. \nType of parameter '%s' must be type of '%s'. But received value = '%s' is type of '%s'.\n";

    private final MapOfStructOfTransportPackage mapOfStructOfTransportPackage;
    private final TypeOfTransportPackage typeOfTransportPackage;
    private final Map<String, Object> mapOfParamName_Value;


    public TransportPackage(TypeOfTransportPackage typeOfTransportPackage) {
        mapOfStructOfTransportPackage = initMapOfStructOfTransportPackage();
        Map<String, Class<?>> mapOfParamName_Class = mapOfStructOfTransportPackage.getMapParamName_ClassByTypeOfTransportPackage(typeOfTransportPackage);

        if (!mapOfParamName_Class.isEmpty()) {
            throw new RuntimeException(String.format(ERROR_MESSAGE_PARAMETERS_LIST_IS_EMPTY_BUT_IT_IS_WRONG, typeOfTransportPackage.getClass(), typeOfTransportPackage, mapOfParamName_Class.size()));
        }

        this.typeOfTransportPackage = typeOfTransportPackage;
        this.mapOfParamName_Value = Collections.emptyMap();
    }

    @JsonCreator(mode = PROPERTIES)
    public TransportPackage(
            @JsonProperty("typeOfTransportPackage") TypeOfTransportPackage typeOfTransportPackage,
            @JsonProperty("mapOfParamName_Value") Map<String, Object> mapOfParamName_Value) throws MultiGameProtocolException {
        mapOfStructOfTransportPackage = initMapOfStructOfTransportPackage();

        Map<String, Class<?>> mapOfParamName_Class = mapOfStructOfTransportPackage.getMapParamName_ClassByTypeOfTransportPackage(typeOfTransportPackage);

        StringBuilder stringBuilder = new StringBuilder();
        if (mapOfParamName_Class.size() != mapOfParamName_Value.size()) {
            System.out.println("TransportPackage(..., ...). 3 1");
            stringBuilder.append(String.format(ERROR_MESSAGE_COUNT_OF_PARAMETERS_IN_SPECIFICATION_IS_NOT_EQUAL_COUNT_OF_PARAMETERS_IN_THIS_PACKAGE,
                    typeOfTransportPackage.getClass(), typeOfTransportPackage, mapOfParamName_Class.size(), mapOfParamName_Value.size()));
        } else {
            System.out.println("TransportPackage(..., ...). 3 2");
            for (Map.Entry<String, Class<?>> nameParam : mapOfParamName_Class.entrySet()) {
                String paramName = nameParam.getKey();
                Class<?> clazz = nameParam.getValue();
                Object value = mapOfParamName_Value.get(paramName);

                if ((value.getClass() != String.class || !clazz.isEnum())
                        // && (value.getClass() != clazz)
                        && (!Classes.isInstanceOf(value, clazz))
                ) {
                    stringBuilder.append(String.format(ERROR_MESSAGE_TYPE_OF_PARAMETER_IN_SPECIFICATION_IS_NOT_EQUAL_TYPE_OF_PARAMETERS_IN_THIS_PACKAGE,
                            typeOfTransportPackage.getClass(), typeOfTransportPackage, paramName, clazz, value, value.getClass()));
                }
            }
        }

        if (stringBuilder.toString().length() > 0) {
            throw new MultiGameProtocolException(stringBuilder.toString());
        }

        this.typeOfTransportPackage = typeOfTransportPackage;
        this.mapOfParamName_Value = mapOfParamName_Value;
    }

    public TypeOfTransportPackage getTypeOfTransportPackage() {
        return typeOfTransportPackage;
    }

    public Map<String, Object> getMapOfParamName_Value() {
        return mapOfParamName_Value;
    }

    abstract MapOfStructOfTransportPackage initMapOfStructOfTransportPackage();
}
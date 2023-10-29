package timmax.tilegame.basemodel.protocol;

import java.util.Collections;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

public abstract class TransportPackage<InOutPackType> {
    private final static String ERROR_MESSAGE_PARAMETERS_LIST_IS_EMPTY_BUT_IT_IS_WRONG =
            "Transport package is '%s'. Type of transport package is '%s'. There are not any parameters, but they must be %d.";
    private final static String ERROR_MESSAGE_COUNT_OF_PARAMETERS_IN_SPECIFICATION_IS_NOT_EQUAL_COUNT_OF_PARAMETERS_IN_THIS_PACKAGE =
            "\nTransport package is '%s'. \nType of transport package is '%s'. \nCount of parameters in specification of protocol is %d and it is not equal count of parameters in package %d.";
    private final static String ERROR_MESSAGE_TYPE_OF_PARAMETER_IN_SPECIFICATION_IS_NOT_EQUAL_TYPE_OF_PARAMETERS_IN_THIS_PACKAGE =
            "\nTransport package is '%s'. \nType of transport package is '%s'. \nType of parameter '%s' must be type of '%s'. But received value = '%s' is type of '%s'.\n";

    // private static MapOfStructOfTransportPackageOfClient mapOfStructOfTransportPackageOfClient = new MapOfStructOfTransportPackageOfClient( );
    // От static пришлось отказаться, но и конструктор MapOfStructOfTransportPackage вызывать для каждого объекта этого
    // класса - неправильно.
    // ToDo: Вместо вызова конструктора нужно фабричный метод вероятно вызывать...
    // private final MapOfStructOfTransportPackage< InOutPackType> mapOfStructOfTransportPackage = new MapOfStructOfTransportPackage< >( );
    private final MapOfStructOfTransportPackage<InOutPackType> mapOfStructOfTransportPackage;

    private final InOutPackType inOutPackType;
    private final Map<String, Object> mapOfParamName_Value;


    public TransportPackage(InOutPackType inOutPackType) {
        mapOfStructOfTransportPackage = initMapOfStructOfTransportPackage();
        Map<String, Class<?>> mapOfName_Class = mapOfStructOfTransportPackage.getMapParamName_ClassByReqType(inOutPackType);

        if (!mapOfName_Class.isEmpty()) {
            throw new RuntimeException(String.format(ERROR_MESSAGE_PARAMETERS_LIST_IS_EMPTY_BUT_IT_IS_WRONG, inOutPackType.getClass(), inOutPackType, mapOfName_Class.size()));
        }

        this.inOutPackType = inOutPackType;
        this.mapOfParamName_Value = Collections.emptyMap();
    }

    @JsonCreator(mode = PROPERTIES)
    public TransportPackage(
            @JsonProperty("inOutPackType") InOutPackType inOutPackType,
            @JsonProperty("mapOfParamName_Value") Map<String, Object> mapOfParamName_Value) throws MultiGameProtocolException {
        mapOfStructOfTransportPackage = initMapOfStructOfTransportPackage();
        Map<String, Class<?>> mapOfParamName_Class = mapOfStructOfTransportPackage.getMapParamName_ClassByReqType(inOutPackType);

        StringBuilder stringBuilder = new StringBuilder();
        if (mapOfParamName_Class.size() != mapOfParamName_Value.size()) {
            stringBuilder.append(String.format(ERROR_MESSAGE_COUNT_OF_PARAMETERS_IN_SPECIFICATION_IS_NOT_EQUAL_COUNT_OF_PARAMETERS_IN_THIS_PACKAGE, inOutPackType.getClass(), inOutPackType, mapOfParamName_Class.size(), mapOfParamName_Value.size()));
        } else {
            for (Map.Entry<String, Class<?>> nameParam : mapOfParamName_Class.entrySet()) {
                String paramName = nameParam.getKey();
                Object value = mapOfParamName_Value.get(paramName);
                Class<?> clazz = nameParam.getValue();
                if ((value.getClass() != String.class || !clazz.isEnum())
                        && (value.getClass() != clazz)) {
                    stringBuilder.append(String.format(ERROR_MESSAGE_TYPE_OF_PARAMETER_IN_SPECIFICATION_IS_NOT_EQUAL_TYPE_OF_PARAMETERS_IN_THIS_PACKAGE, inOutPackType.getClass(), inOutPackType, paramName, clazz, value, value.getClass()));
                }
            }
        }

        if (stringBuilder.toString().length() > 0) {
            // Предполагалось, что 'throw multiGameProtocolException;' должен был привести и к выводу стек-трейса и
            // к остановке приложения. Но этого не происходит.
            // Поэтому вместо простого
            // throw new MultiGameProtocolException( stringBuilder.toString( ));

            // Делаем так:
            MultiGameProtocolException multiGameProtocolException = new MultiGameProtocolException(stringBuilder.toString());
            multiGameProtocolException.printStackTrace();
            System.exit(1);
        }

        this.inOutPackType = inOutPackType;
        this.mapOfParamName_Value = mapOfParamName_Value;
    }

    public InOutPackType getInOutPackType() {
        return inOutPackType;
    }

    public Map<String, Object> getMapOfParamName_Value() {
        return mapOfParamName_Value;
    }

    abstract MapOfStructOfTransportPackage<InOutPackType> initMapOfStructOfTransportPackage();
}
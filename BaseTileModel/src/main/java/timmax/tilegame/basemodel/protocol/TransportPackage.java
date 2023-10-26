package timmax.tilegame.basemodel.protocol;

import java.util.Collections;
import java.util.Map;

public abstract class TransportPackage< InOutPackType> {
    // private static MapOfStructOfTransportPackageOfClient mapOfStructOfTransportPackageOfClient = new MapOfStructOfTransportPackageOfClient( );
    // От static пришлось отказаться, но и конструктор MapOfStructOfTransportPackage вызывать для каждого объекта этого
    // класса - неправильно.
    // ToDo: Вместо вызова конструктора нужно фабричный метод вероятно вызывать...
    private final MapOfStructOfTransportPackage< InOutPackType> mapOfStructOfTransportPackage = new MapOfStructOfTransportPackage< >( );

    private final InOutPackType inOutPackType;
    private final Map< String, Object> mapOfParamName_Value;


    public TransportPackage( InOutPackType inOutPackType) {
        Map< String, Class> mapOfName_Class = mapOfStructOfTransportPackage.getMapString_ClassByReqType( inOutPackType);

        if ( !mapOfName_Class.isEmpty( )) {
            // throw new RuntimeException( "'" + InOutPackType + "' '" + inOutPackType + "'. There are not any parameters, but they must be " + mapOfName_Class.size( ) + ".");
            throw new RuntimeException( "'" + "???" + "' '" + inOutPackType + "'. There are not any parameters, but they must be " + mapOfName_Class.size( ) + ".");
        }

        this.inOutPackType = inOutPackType;
        this.mapOfParamName_Value = Collections.emptyMap( );
    }

    public TransportPackage( InOutPackType inOutPackType, Map< String, Object> mapOfParamName_Value) {
        Map< String, Class> mapOfName_Class = mapOfStructOfTransportPackage.getMapString_ClassByReqType( inOutPackType);

        StringBuilder stringBuilder = new StringBuilder( );
        if ( mapOfName_Class.size( ) != mapOfParamName_Value.size( )) {
            // stringBuilder.append( "'ReqType' = '" + inOutPackType + "'. Count of params in specification of protocol '" + mapOfName_Class.size( ) + "' is not equal of count of params in package.");
            // stringBuilder.append( "'" + "???" + "' = '" + inOutPackType + "'. Count of params in specification of protocol '" + mapOfName_Class.size( ) + "' is not equal of count of params in package.");
            stringBuilder.append( "'" + "???" + "' = '").append(inOutPackType).append( "'. Count of params in specification of protocol '").append( mapOfName_Class.size( )).append( "' is not equal of count of params in package.");
        } else {
            for ( Map.Entry<String, Class> nameParam : mapOfName_Class.entrySet()) {
                String paramName = nameParam.getKey( );
                Object value = mapOfParamName_Value.get( paramName);
                Class clazz = nameParam.getValue( );
                if ( value.getClass( ) != clazz) {
                    // stringBuilder.append( "ReqType = '" + inOutPackType + "'. Type of param '" + paramName + "' must be type of '" + clazz + "'. But received value " + value + " is type of '" + value.getClass() + ".\n");
                    // stringBuilder.append( "'" + "???" + "' = '" + inOutPackType + "'. Type of param '" + paramName + "' must be type of '" + clazz + "'. But received value " + value + " is type of '" + value.getClass() + ".\n");
                    stringBuilder.append( "'" + "???" + "' = '").append( inOutPackType).append( "'. Type of param '").append( paramName).append( "' must be type of '").append( clazz).append( "'. But received value ").append( value).append( " is type of '").append( value.getClass( )).append(".\n");
                }
            }
        }

        if ( stringBuilder.toString( ).length( ) > 0) {
            throw new RuntimeException( stringBuilder.toString( ));
        }

        this.inOutPackType = inOutPackType;
        this.mapOfParamName_Value = mapOfParamName_Value;
    }

    public InOutPackType getInOutPackType( ) {
        return inOutPackType;
    }

    public Map< String, Object> getMapOfParamName_Value( ) {
        return mapOfParamName_Value;
    }
}
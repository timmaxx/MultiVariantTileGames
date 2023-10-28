package timmax.tilegame.basemodel.protocol;

import java.util.*;

import timmax.tilegame.basemodel.ServerBaseModel;
import timmax.tilegame.basemodel.credential.ResultOfCredential;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackageOfServer.*;

public class MapOfStructOfTransportPackageOfServer extends MapOfStructOfTransportPackage< TypeOfTransportPackageOfServer> {
    // Также см. EnumSet и EnumMap
    // https://habr.com/ru/articles/267389/

    public MapOfStructOfTransportPackageOfServer( ) {
        // mapOfUniversalType.put( INFO_LOGIN, Collections.emptyMap( ));

        Map< String, Class> mapOfParamName_Class__Login = new HashMap< >( );

        // mapOfParamName_Class__Login.put( "resultOfCredential", String.class);
        // На сервере:
        // timmax.tilegame.basemodel.protocol.MultiGameProtocolException:
        // Transport package is 'class timmax.tilegame.basemodel.protocol.TypeOfTransportPackageOfServer'.
        // Type of transport package is 'INFO_LOGIN'.
        // Type of parameter 'resultOfCredential' must be type of 'class java.lang.String'.
        // But received value = 'AUTHORISED' is type of 'class timmax.tilegame.basemodel.credential.ResultOfCredential'.

        mapOfParamName_Class__Login.put( "resultOfCredential", ResultOfCredential.class);
        // На сервере - Ок
        // JSON:
        // {"inOutPackType":"INFO_LOGIN","mapOfParamName_Value":{"resultOfCredential":"AUTHORISED"}}
        // На клиенте:
        // timmax.tilegame.basemodel.protocol.MultiGameProtocolException:
        // Transport package is 'class timmax.tilegame.basemodel.protocol.TypeOfTransportPackageOfServer'.
        // Type of transport package is 'INFO_LOGIN'.
        // Type of parameter 'resultOfCredential' must be type of 'class timmax.tilegame.basemodel.credential.ResultOfCredential'.
        // But received value = 'AUTHORISED' is type of 'class java.lang.String'.

        mapOfUniversalType.put( INFO_LOGIN, mapOfParamName_Class__Login);


        mapOfUniversalType.put( INFO_LOGOUT, Collections.emptyMap( ));

        Map< String, Class> mapOfParamName_Class__GameTypeMap = new HashMap< >( );
        // По хорошему, в следующей строке, вместо
        // Map.class
        // лучше было-бы написать что-то типа
        // Map< ServerBaseClass, Description>.class
        // Но такой синтаксис не принимается. Нужно разобраться!
        mapOfParamName_Class__GameTypeMap.put( "gameTypeList", Map.class);
        mapOfUniversalType.put( INFO_GAME_TYPE_MAP, mapOfParamName_Class__GameTypeMap);

        Map< String, Class> mapOfParamName_Class__SelectGameType = new HashMap< >( );
        mapOfParamName_Class__SelectGameType.put( "gameType", ServerBaseModel.class);
        mapOfUniversalType.put( INFO_SELECT_GAME_TYPE, mapOfParamName_Class__SelectGameType);
    }
}
package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.ServerBaseModel;

import java.util.*;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackageOfServer.*;

public class MapOfStructOfTransportPackageOfServer extends MapOfStructOfTransportPackage< TypeOfTransportPackageOfServer> {
    // Также см. EnumSet и EnumMap
    // https://habr.com/ru/articles/267389/

    public MapOfStructOfTransportPackageOfServer( ) {
        mapOfUniversalType.put( INFO_LOGIN, Collections.emptyMap( ));
        mapOfUniversalType.put( INFO_LOGOUT, Collections.emptyMap( ));

        Map< String, Class> mapOfParamName_Class__GameTypeMap = new HashMap<>( );
        // По хорошему, в следующей строке, вместо
        // Map.class
        // лучше было-бы написать что-то типа
        // Map< ServerBaseClass, Description>.class
        mapOfParamName_Class__GameTypeMap.put( "gameTypeList", Map.class);
        mapOfUniversalType.put( INFO_GAME_TYPE_MAP, mapOfParamName_Class__GameTypeMap);

        Map< String, Class> mapOfParamName_Class__SelectGameType = new HashMap< >( );
        mapOfParamName_Class__SelectGameType.put( "gameType", ServerBaseModel.class);
        mapOfUniversalType.put( INFO_SELECT_GAME_TYPE, mapOfParamName_Class__SelectGameType);
    }
}
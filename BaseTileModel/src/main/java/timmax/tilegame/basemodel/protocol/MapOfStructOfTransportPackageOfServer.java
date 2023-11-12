package timmax.tilegame.basemodel.protocol;

//import java.util.*;

import timmax.tilegame.basemodel.credential.ResultOfCredential;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.*;

public class MapOfStructOfTransportPackageOfServer extends MapOfStructOfTransportPackage {
    public MapOfStructOfTransportPackageOfServer() {
        super( Map.of(
            LOGIN, Map.of(
                    "resultOfCredential", ResultOfCredential.class,
                    "userName", String.class
                ),
            LOGOUT, Collections.emptyMap(),
            GET_GAME_TYPE_SET, Map.of(
                    "gameTypeSet", ArrayList.class
                )
        ));

/*
        Map<String, Class<?>> mapOfParamName_Class__GameTypeMap = new HashMap<>();
        // По хорошему, в следующей строке, вместо
        // Map.class
        // лучше было-бы написать что-то типа
        // Map< ServerBaseClass, Description>.class
        // Но такой синтаксис не принимается. Нужно разобраться!
        mapOfParamName_Class__GameTypeMap.put("gameTypeList", Map.class);
        mapOfUniversalType.put(GAME_TYPE_MAP, mapOfParamName_Class__GameTypeMap);

        Map<String, Class<?>> mapOfParamName_Class__SelectGameType = new HashMap<>();
        mapOfParamName_Class__SelectGameType.put("gameType", ServerBaseModel.class);
        mapOfUniversalType.put(SELECT_GAME_TYPE, mapOfParamName_Class__SelectGameType);
*/
    }
}
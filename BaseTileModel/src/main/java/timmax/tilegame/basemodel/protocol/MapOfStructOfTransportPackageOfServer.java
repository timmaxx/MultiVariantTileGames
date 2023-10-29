package timmax.tilegame.basemodel.protocol;

import java.util.*;

import timmax.tilegame.basemodel.credential.ResultOfCredential;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.LOGIN;

public class MapOfStructOfTransportPackageOfServer extends MapOfStructOfTransportPackage {
    public MapOfStructOfTransportPackageOfServer() {
        super();

        Map<String, Class<?>> mapOfParamName_Class__Login = new HashMap<>();
        mapOfParamName_Class__Login.put("resultOfCredential", ResultOfCredential.class);
        mapOfUniversalType.put(LOGIN, mapOfParamName_Class__Login);
/*
        mapOfUniversalType.put(LOGOUT, Collections.emptyMap());

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
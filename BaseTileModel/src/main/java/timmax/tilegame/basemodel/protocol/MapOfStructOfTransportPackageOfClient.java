package timmax.tilegame.basemodel.protocol;

import java.util.*;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.LOGIN;

public class MapOfStructOfTransportPackageOfClient extends MapOfStructOfTransportPackage {
    public MapOfStructOfTransportPackageOfClient() {
        super();

        Map<String, Class<?>> mapOfParamName_Class__Login = new HashMap<>();
        mapOfParamName_Class__Login.put("userName", String.class);
        mapOfParamName_Class__Login.put("password", String.class);
        mapOfUniversalType.put(LOGIN, mapOfParamName_Class__Login);
/*
        mapOfUniversalType.put(LOGOUT, Collections.emptyMap());

        mapOfUniversalType.put(GAME_TYPE_MAP, Collections.emptyMap());

        Map<String, Class<?>> mapOfParamName_Class__SelectGameType = new HashMap<>();
        mapOfParamName_Class__SelectGameType.put("gameType", ServerBaseModel.class);
        mapOfUniversalType.put(SELECT_GAME_TYPE, mapOfParamName_Class__SelectGameType);
*/
    }
}
package timmax.tilegame.basemodel.protocol;

import java.util.*;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackageOfClient.*;

public class MapOfStructOfTransportPackageOfClient extends MapOfStructOfTransportPackage<TypeOfTransportPackageOfClient> {
    // Также см. EnumSet и EnumMap
    // https://habr.com/ru/articles/267389/


    public MapOfStructOfTransportPackageOfClient() {
        super();

        Map<String, Class<?>> mapOfParamName_Class__Login = new HashMap<>();
        mapOfParamName_Class__Login.put("userName", String.class);
        mapOfParamName_Class__Login.put("password", String.class);
        mapOfUniversalType.put(REQ_LOGIN, mapOfParamName_Class__Login);
/*
        mapOfUniversalType.put(REQ_LOGOUT, Collections.emptyMap());

        mapOfUniversalType.put(REQ_GAME_TYPE_MAP, Collections.emptyMap());

        Map<String, Class<?>> mapOfParamName_Class__SelectGameType = new HashMap<>();
        mapOfParamName_Class__SelectGameType.put("gameType", ServerBaseModel.class);
        mapOfUniversalType.put(REQ_SELECT_GAME_TYPE, mapOfParamName_Class__SelectGameType);
*/
    }
}
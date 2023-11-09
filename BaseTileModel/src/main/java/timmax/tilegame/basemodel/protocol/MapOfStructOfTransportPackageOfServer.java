package timmax.tilegame.basemodel.protocol;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;

import timmax.tilegame.basemodel.credential.ResultOfCredential;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.LOGIN;
import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.LOGOUT;

public class MapOfStructOfTransportPackageOfServer extends MapOfStructOfTransportPackage {
    public MapOfStructOfTransportPackageOfServer() {
        super(new EnumMap<>(TypeOfTransportPackage.class) {{
            put(LOGIN, Collections.unmodifiableMap(new HashMap<>() {{
                put("resultOfCredential", ResultOfCredential.class);
                put("userName", String.class);
            }}));
            put(LOGOUT, Collections.emptyMap());
        }});
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
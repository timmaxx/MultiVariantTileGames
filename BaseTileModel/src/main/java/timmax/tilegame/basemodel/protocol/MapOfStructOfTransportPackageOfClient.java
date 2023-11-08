package timmax.tilegame.basemodel.protocol;

import java.util.Collections;
import java.util.HashMap;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.LOGIN;
import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.LOGOUT;

public class MapOfStructOfTransportPackageOfClient extends MapOfStructOfTransportPackage {
    public MapOfStructOfTransportPackageOfClient() {
        super();

        mapOfUniversalType.put(LOGIN, new HashMap<>() {{
            put("userName", String.class);
            put("password", String.class);
        }});

        mapOfUniversalType.put(LOGOUT, Collections.emptyMap());
/*
        mapOfUniversalType.put(GAME_TYPE_MAP, Collections.emptyMap());

        Map<String, Class<?>> mapOfParamName_Class__SelectGameType = new HashMap<>();
        mapOfParamName_Class__SelectGameType.put("gameType", ServerBaseModel.class);
        mapOfUniversalType.put(SELECT_GAME_TYPE, mapOfParamName_Class__SelectGameType);
*/
    }
}
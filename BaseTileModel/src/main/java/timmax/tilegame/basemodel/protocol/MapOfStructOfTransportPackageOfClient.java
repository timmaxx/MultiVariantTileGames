package timmax.tilegame.basemodel.protocol;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.*;

public class MapOfStructOfTransportPackageOfClient extends MapOfStructOfTransportPackage {
    public MapOfStructOfTransportPackageOfClient() {
        super(new EnumMap<>(TypeOfTransportPackage.class) {{
            put(LOGIN, Collections.unmodifiableMap(new HashMap<>() {{
                put("userName", String.class);
                put("password", String.class);
            }}));
            put(LOGOUT, Collections.emptyMap());
            put(GET_GAME_TYPE_SET, Collections.emptyMap());
        }});

/*
        Map<String, Class<?>> mapOfParamName_Class__SelectGameType = new HashMap<>();
        mapOfParamName_Class__SelectGameType.put("gameType", ServerBaseModel.class);
        mapOfUniversalType.put(SELECT_GAME_TYPE, mapOfParamName_Class__SelectGameType);
*/
    }
}
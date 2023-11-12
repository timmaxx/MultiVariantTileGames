package timmax.tilegame.basemodel.protocol;

import java.util.Collections;
import java.util.Map;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.*;

public class MapOfStructOfTransportPackageOfClient extends MapOfStructOfTransportPackage {
    public MapOfStructOfTransportPackageOfClient() {
        super(Map.of(
                LOGIN, Map.of(
                        "userName", String.class,
                        "password", String.class
                ),
                LOGOUT, Collections.emptyMap(),
                GET_GAME_TYPE_SET, Collections.emptyMap()
        ));

/*
        Map<String, Class<?>> mapOfParamName_Class__SelectGameType = new HashMap<>();
        mapOfParamName_Class__SelectGameType.put("gameType", ServerBaseModel.class);
        mapOfUniversalType.put(SELECT_GAME_TYPE, mapOfParamName_Class__SelectGameType);
*/
    }
}
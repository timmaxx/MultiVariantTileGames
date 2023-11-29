package timmax.tilegame.basemodel.protocol;

import java.util.Collections;
import java.util.Map;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.*;

public class MapOfStructOfTransportPackageOfClient extends MapOfStructOfTransportPackage {
    public MapOfStructOfTransportPackageOfClient() {
        super(Map.of(
                LOGOUT, Collections.emptyMap(),
                LOGIN, Map.of(
                        "userName", String.class,
                        "password", String.class
                ),
                FORGET_GAME_TYPE_SET, Collections.emptyMap(),
                GET_GAME_TYPE_SET, Collections.emptyMap(),
                FORGET_GAME_TYPE, Collections.emptyMap(),
                SELECT_GAME_TYPE, Map.of(
                        "gameType", String.class
                ),
                ADD_VIEW, Map.of(
                        "viewId", String.class
                ),
                CREATE_NEW_GAME, Collections.emptyMap()
        ));
    }
}
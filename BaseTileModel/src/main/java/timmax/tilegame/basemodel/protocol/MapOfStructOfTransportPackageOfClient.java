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
                GET_GAME_TYPE_SET, Collections.emptyMap(),
                SELECT_GAME_TYPE, Map.of(
                        "gameType", String.class
                )
        ));
    }
}
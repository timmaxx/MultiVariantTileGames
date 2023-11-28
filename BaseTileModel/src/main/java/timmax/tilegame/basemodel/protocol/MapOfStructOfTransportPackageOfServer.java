package timmax.tilegame.basemodel.protocol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

// import timmax.tilegame.basemodel.gameevent.GameEvent;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.*;

public class MapOfStructOfTransportPackageOfServer extends MapOfStructOfTransportPackage {
    public MapOfStructOfTransportPackageOfServer() {
        super(Map.of(
                LOGOUT, Collections.emptyMap(),
                LOGIN, Map.of(
                        "userName", String.class
                ),
                FORGET_GAME_TYPE_SET, Collections.emptyMap(),
                GET_GAME_TYPE_SET, Map.of(
                        "gameTypeSet", ArrayList.class
                ),
                FORGET_GAME_TYPE, Collections.emptyMap(),
                SELECT_GAME_TYPE, Map.of(
                        "gameType", String.class
                ),
                ADD_VIEW, Map.of(
                        "viewId", String.class
                )/*,
                GAME_EVENT, Map.of(
                        "viewId", String.class,
                        "gameEvent", GameEvent.class
                )*/
        ));
    }
}
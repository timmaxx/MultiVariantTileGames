package timmax.tilegame.basemodel.protocol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import timmax.tilegame.basemodel.credential.ResultOfCredential;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.*;

public class MapOfStructOfTransportPackageOfServer extends MapOfStructOfTransportPackage {
    public MapOfStructOfTransportPackageOfServer() {
        super(Map.of(
                LOGOUT, Collections.emptyMap(),
                LOGIN, Map.of(
                        "resultOfCredential", ResultOfCredential.class,
                        "userName", String.class
                ),
                FORGET_GAME_TYPE_SET, Collections.emptyMap(),
                GET_GAME_TYPE_SET, Map.of(
                        "gameTypeSet", ArrayList.class
                ),
                SELECT_GAME_TYPE, Map.of(
                        "gameType", String.class
                )
        ));
    }
}
package timmax.basetilemodel.gameevent.game2;

import timmax.basetilemodel.GameStatus;
import java.util.HashMap;

import static timmax.basetilemodel.gameevent.game2.TypeOfOneParamOfEvent.*;

// 2
public class MapOfAllBaseParamsOfEvents extends HashMap<TypeOfOneParamOfEvent, Class>{
    protected MapOfAllBaseParamsOfEvents( ) {
        super( );

        // NEW_GAME
        put( WIDTH, Integer.class);
        put( HEIGHT, Integer.class);
        put( COUNT_OF_ALL_TILES, Integer.class);

        // GAME_OVER
        put( GAME_STATUS, GameStatus.class);

        // ONE_TILE
        put( X, Integer.class);
        put( Y, Integer.class);
    }

    public Class getClassByGame2TypeOfOneParamOfEvent( TypeOfOneParamOfEvent typeOfOneParamOfEvent) {
        return get(typeOfOneParamOfEvent);
    }
}
package timmax.basetilemodel.gameevent.game2;

import java.util.HashMap;
import java.util.Map;

import static timmax.basetilemodel.gameevent.game2.TypeOfEvent.*;
import static timmax.basetilemodel.gameevent.game2.TypeOfOneParamOfEvent.*;

// 5
public class MapOfTypeEvent {
    private Map<TypeOfEvent, Map<TypeOfOneParamOfEvent, Class>> game2MapOfTypesOfEvents;


    public MapOfTypeEvent( ) {
        MapOfAllBaseParamsOfEvents mapOfAllBaseParamsOfEvents = new MapOfAllBaseParamsOfEvents( );

        this.game2MapOfTypesOfEvents = new HashMap< >( );

        // NEW_GAME
        MapOfTypeOfOneParamOfEvent mapOfTypeOfOneParamOfEventNewGame  = new MapOfTypeOfOneParamOfEvent(mapOfAllBaseParamsOfEvents);
        mapOfTypeOfOneParamOfEventNewGame.put( WIDTH);
        mapOfTypeOfOneParamOfEventNewGame.put( HEIGHT);
        mapOfTypeOfOneParamOfEventNewGame.put( COUNT_OF_ALL_TILES);
        game2MapOfTypesOfEvents.put( NEW_GAME, mapOfTypeOfOneParamOfEventNewGame);

        // GAME_OVER
        MapOfTypeOfOneParamOfEvent mapOfTypeOfOneParamOfEventGameOver  = new MapOfTypeOfOneParamOfEvent(mapOfAllBaseParamsOfEvents);
        mapOfTypeOfOneParamOfEventGameOver.put( GAME_STATUS);
        game2MapOfTypesOfEvents.put( GAME_OVER, mapOfTypeOfOneParamOfEventGameOver);

        // ONE_TILE
        MapOfTypeOfOneParamOfEvent mapOfTypeOfOneParamOfEventOneTile  = new MapOfTypeOfOneParamOfEvent(mapOfAllBaseParamsOfEvents);
        mapOfTypeOfOneParamOfEventOneTile.put( GAME_STATUS);
        game2MapOfTypesOfEvents.put( ONE_TILE, mapOfTypeOfOneParamOfEventOneTile);
    }

    public Map<TypeOfOneParamOfEvent, Class> get(TypeOfOneParamOfEvent typeOfOneParamOfEvent) {
        return game2MapOfTypesOfEvents.get(typeOfOneParamOfEvent);
    }
}
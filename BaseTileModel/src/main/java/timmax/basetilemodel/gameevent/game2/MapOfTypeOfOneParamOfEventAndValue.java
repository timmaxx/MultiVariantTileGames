package timmax.basetilemodel.gameevent.game2;

import java.util.HashMap;
import java.util.Map;

// Карта( Тип отдельного параметра события - Значение). Например: Width - 15, Height - 10.
/*
public enum TypeOfEvent {
    NEW_GAME,
    GAME_OVER,
    ONE_TILE
}
*/
public class MapOfTypeOfOneParamOfEventAndValue {
    private MapOfTypeEvent mapOfTypeEvent;
    protected Map< TypeOfOneParamOfEvent, Object> game2TypeOfOneParamOfEventObjectMap;

    public MapOfTypeOfOneParamOfEventAndValue( MapOfTypeEvent mapOfTypeEvent) {
        this.mapOfTypeEvent = mapOfTypeEvent;
        this.game2TypeOfOneParamOfEventObjectMap = new HashMap< >( );
    }

    protected Object put( TypeOfOneParamOfEvent typeOfOneParamOfEvent, Object object) {
        Map< TypeOfOneParamOfEvent, Class> map = mapOfTypeEvent.get( typeOfOneParamOfEvent);
/*
public enum Game2TypeOfOneParamOfEvent {
    // NEW_GAME
    WIDTH,
    HEIGHT,
    COUNT_OF_ALL_TILES,

    // GAME_OVER
    GAME_STATUS,

    // ONE_TILE
    X,
    Y
}
*/
        // Class clazz = map.get();
        return game2TypeOfOneParamOfEventObjectMap.put(typeOfOneParamOfEvent, object);
    }
}
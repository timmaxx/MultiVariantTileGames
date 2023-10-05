package timmax.basetilemodel.gameevent.game2;

import java.util.HashMap;

// 3
public class MapOfTypeOfOneParamOfEvent extends HashMap<TypeOfOneParamOfEvent, Class> {
    MapOfAllBaseParamsOfEvents mapOfAllBaseParamsOfEvents;


    public MapOfTypeOfOneParamOfEvent(MapOfAllBaseParamsOfEvents mapOfAllBaseParamsOfEvents) {
        super( );
        this.mapOfAllBaseParamsOfEvents = mapOfAllBaseParamsOfEvents;
    }

    protected Class put( TypeOfOneParamOfEvent key) {
        return put( key, mapOfAllBaseParamsOfEvents.getClassByGame2TypeOfOneParamOfEvent( key));
    }
}
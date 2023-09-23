package timmax.basetilemodel;

import timmax.basetilemodel.gameevent.GameEvent;

public interface ViewInterface {
    void update( ); // Обновить представление (по данным модели)
    GameEvent removeFromGameQueueForOneView( );
}
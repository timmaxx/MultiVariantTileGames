package timmax.tilegame.baseview;

import timmax.tilegame.basemodel.gameevent.GameEvent;

public interface ViewInterface {
    void update( ); // Обновить представление (по данным модели)
    GameEvent removeFromGameQueueForOneView( );
}
package timmax.tilegame.baseview;

import timmax.tilegame.basemodel.gameevent.GameEvent;

public interface View {
    void update( ); // Обновить представление (по данным модели)
    GameEvent removeFromGameQueueForOneView( );
}
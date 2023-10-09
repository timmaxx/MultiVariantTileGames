package timmax.tilegame.baseview;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.GameQueueForOneView;
import timmax.tilegame.basemodel.gameevent.GameEvent;

// Представление
public abstract class View implements ViewInterface {
    protected GameQueueForOneView gameQueueForOneView;


    public View( BaseModel baseModel) {
        gameQueueForOneView = baseModel.addView( this); // К модели привязать это представление
    }

    @Override
    public GameEvent removeFromGameQueueForOneView( ) {
        return gameQueueForOneView.remove( );
    }
}
package timmax.tilegame.baseview;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.GameQueueForOneView;
import timmax.tilegame.basemodel.gameevent.GameEvent;

// Представление
public abstract class BaseView implements View {
    protected GameQueueForOneView gameQueueForOneView;


    public BaseView( BaseModel baseModel) {
        gameQueueForOneView = baseModel.addViewListener( this); // К модели привязать это представление
    }

    @Override
    public GameEvent removeFromGameQueueForOneView( ) {
        return gameQueueForOneView.remove( );
    }
}
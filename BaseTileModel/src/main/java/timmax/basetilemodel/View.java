package timmax.basetilemodel;

import timmax.basetilemodel.gameevent.GameEvent;

// Представление
public abstract class View implements ViewInterface {
    protected GameQueueForOneView gameQueueForOneView;


    public View( BaseModel baseModel) {
        gameQueueForOneView = baseModel.addViewListener( this); // К модели привязать это представление
    }

    @Override
    public GameEvent removeFromGameQueueForOneView( ) {
        return gameQueueForOneView.remove( );
    }
}
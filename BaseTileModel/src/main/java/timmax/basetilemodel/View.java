package timmax.basetilemodel;

import timmax.basetilemodel.gameevent.GameEvent;

// Представление
public class View implements ViewInterface {
    protected GameQueueForOneView gameQueueForOneView;

    // Конструктор представления
    public View( BaseModel baseModel) {
        gameQueueForOneView = baseModel.addViewListener( this); // К модели привязать это представление
    }

    // Обновить представление (по данным модели)
    @Override
    public void update( ) {
        System.out.println( "View.update() " + this.getClass( ) + " " + this);
    }

    @Override
    public GameEvent removeFromGameQueueForOneView( ) {
        return gameQueueForOneView.remove( );
    }
}
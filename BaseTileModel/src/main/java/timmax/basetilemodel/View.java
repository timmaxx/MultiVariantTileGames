package timmax.basetilemodel;

// Представление
public abstract class View implements ViewInterface {
    protected GameQueueForOneView gameQueueForOneView;

    // Конструктор представления
    public View( BaseModel baseModel) {
        gameQueueForOneView = baseModel.addViewListener( this); // К модели привязать это представление
    }

    @Override
    public abstract void update( ); // Обновить представление (по данным модели)
}
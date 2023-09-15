package timmax.basetilemodel;

// Представление
public abstract class View implements ViewInterface {
    protected BaseModel baseModel; // Ссылается на одну модель

    // Конструктор представления
    public View( BaseModel baseModel) {
        this.baseModel = baseModel; // С привязкой к модели
        baseModel.addViewListener( this); // К модели привязать это представление
    }

    @Override
    public abstract void update( ); // Обновить представление (по данным модели)
}
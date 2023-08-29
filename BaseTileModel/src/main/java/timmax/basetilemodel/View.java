package timmax.basetilemodel;

// Представление
public abstract class View {
    protected BaseModel baseModel; // Ссылается на одну модель

    // Конструктор представления
    public View( BaseModel baseModel) {
        this.baseModel = baseModel; // С привязкой к модели
        baseModel.addViewListener( this); // К модели привязать это представление
    }

    protected abstract void update( ); // Обновить представление (по данным модели)
}
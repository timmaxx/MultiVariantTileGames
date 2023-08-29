package timmax.basetilemodel;

// Модель-наблюдатель
public interface ObservableModel {
    void addViewListener( View view);

    void notifyViews( );
}
package timmax.basetilemodel;

// Модель-наблюдатель
public interface ObservableModel {
    GameQueueForOneView addViewListener( ViewInterface view);

    void notifyViews( );
}
package timmax.basetilemodel;

// Модель-наблюдатель
public interface ObservableModel {
    GameQueueForOneView addViewListener(View view);

    void notifyViews( );
}
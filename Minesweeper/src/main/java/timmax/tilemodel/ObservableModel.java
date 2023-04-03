package timmax.tilemodel;

public interface ObservableModel {
    void addViewListener( View view);

    void notifyViews( );
}
package timmax.basetilemodel;

public interface ObservableModel {
    void addViewListener( View view);

    void notifyViews( );
}
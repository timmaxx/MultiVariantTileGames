package timmax.minesweeper.model;

public interface ObservableModel {
    void addViewListener( View view);

    void notifyViews( );
}
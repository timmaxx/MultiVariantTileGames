package timmax.minesweeper.model;

import timmax.minesweeper.view.View;

public interface ObservableModel {
    void addViewListener( View view);

    void notifyViews( );
}
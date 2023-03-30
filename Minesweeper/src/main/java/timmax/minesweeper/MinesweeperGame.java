package timmax.minesweeper;

import timmax.minesweeper.controller.Controller;
import timmax.minesweeper.model.GameStatus;
import timmax.minesweeper.view.View;
import timmax.minesweeper.view.ViewMainArea;
import timmax.tilegameengine.*;
import timmax.minesweeper.model.Model;

import static javafx.scene.paint.Color.AQUA;
import static javafx.scene.paint.Color.WHITE;

public class MinesweeperGame extends Game {
    private final static int SIDE_OF_WIDTH = 5;
    private final static int SIDE_OF_HEIGHT = 5;

    private static final int REST_OF_MINE_INSTALLATION_IN_PERCENTS = 10;

    private Model model;

    private View viewMainArea;

    private Controller controller;

    @Override
    public void initialize( ) {
        createGame( );
    }

    private void createGame( ) {
        viewMainArea = new ViewMainArea( this);
        setScreenSize( SIDE_OF_WIDTH, SIDE_OF_HEIGHT);
        model = new Model( SIDE_OF_WIDTH, SIDE_OF_HEIGHT, REST_OF_MINE_INSTALLATION_IN_PERCENTS);
        controller = new Controller( model);
        viewMainArea.setModel( model);
        viewMainArea.update( );
    }

    @Override
    public void onMouseLeftClick( int x, int y) {
        if ( model.getGameStatus( ) == GameStatus.GAME) {
            controller.onMouseLeftClick( x, y);
            if ( model.getGameStatus() == GameStatus.VICTORY) {
                showMessageDialog( AQUA, "Win!", WHITE, 30);
            } else if ( model.getGameStatus() == GameStatus.DEFEAT) {
                showMessageDialog( AQUA, "Game over!", WHITE, 30);
            }
        } else {
            createGame( );
        }
    }

    @Override
    public void onMouseRightClick( int x, int y) {
        controller.onMouseRightClick( x, y);
    }
}
package timmax.minesweeper;

import timmax.tilegameenginejfx.*;
import timmax.minesweeper.controller.Controller;
import timmax.minesweeper.model.*;
import timmax.minesweeper.view.*;
import timmax.basetilemodel.View;

public class MinesweeperGame extends Game {
    private final static int SIDE_OF_WIDTH = 10;
    private final static int SIDE_OF_HEIGHT = 10;

    private static final int REST_OF_MINE_INSTALLATION_IN_PERCENTS = 20;

    private Model model;

    private View viewMainArea;

    private Controller controller;

    @Override
    public void initialize( ) {
        createGame( );
    }

    private void createGame( ) {
        model = new Model( SIDE_OF_WIDTH, SIDE_OF_HEIGHT, REST_OF_MINE_INSTALLATION_IN_PERCENTS);
        viewMainArea = new ViewMainArea( this);
        controller = new Controller( model);

        setScreenSize( SIDE_OF_WIDTH, SIDE_OF_HEIGHT);
        viewMainArea.setModel( model);
        viewMainArea.updateAllTiles( );
    }

    @Override
    public void onMouseLeftClick( int x, int y) {
        if ( model.getGameStatus( ) == GameStatus.GAME) {
            controller.onMouseLeftClick( x, y);
        } else {
            createGame( );
        }
    }

    @Override
    public void onMouseRightClick( int x, int y) {
        controller.onMouseRightClick( x, y);
    }
}
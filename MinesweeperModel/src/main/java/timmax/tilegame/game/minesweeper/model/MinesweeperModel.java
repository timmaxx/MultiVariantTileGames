package timmax.tilegame.game.minesweeper.model;

import timmax.tilegame.basemodel.BaseModel;

import timmax.tilegame.game.minesweeper.model.gameevent.GameEventOneTileChangeFlag;
import timmax.tilegame.game.minesweeper.model.gameevent.GameEventMinesweeperPersistentParams;
import timmax.tilegame.game.minesweeper.model.gameevent.GameEventMinesweeperVariableParamsFlag;
import timmax.tilegame.game.minesweeper.model.gameevent.GameEventMinesweeperVariableParamsOpenClose;
import timmax.tilegame.game.minesweeper.model.gameobject.AllMinesweeperObjects;
import timmax.tilegame.game.minesweeper.model.gameobject.LevelGenerator;

// Модель игры Сапёр
public class MinesweeperModel extends BaseModel {
    private static final int REST_OF_MINE_INSTALLATION_IN_PERCENTS = 10;
    private final static int SIDE_OF_WIDTH = 15;
    private final static int SIDE_OF_HEIGHT = 10;

    private final LevelGenerator levelGenerator = new LevelGenerator( );

    private AllMinesweeperObjects allMinesweeperObjects;


    @Override
    public void createNewGame( ) {
        createNewGame( SIDE_OF_WIDTH, SIDE_OF_HEIGHT);
        addGameEventIntoQueueAndNotifyViews( new GameEventMinesweeperPersistentParams( allMinesweeperObjects.getCountOfMines( )));
    }

    @Override
    public void createNewGame( int width, int height) {
        allMinesweeperObjects = levelGenerator.getLevel( width, height, REST_OF_MINE_INSTALLATION_IN_PERCENTS);
        allMinesweeperObjects.setModel( this);
        addGameEventIntoQueue( new GameEventMinesweeperVariableParamsOpenClose( 0, width * height));
        addGameEventIntoQueue( new GameEventMinesweeperVariableParamsFlag( 0, allMinesweeperObjects.getCountOfMines( )));
        super.createNewGame( width, height);
    }

    public void inverseFlag( int x, int y) {
        boolean isFlag = allMinesweeperObjects.inverseFlag( allMinesweeperObjects.getTileByXY( x, y));
        addGameEventIntoQueueAndNotifyViews( new GameEventOneTileChangeFlag( x, y, isFlag));
    }

    public void open( int x, int y) {
        setGameStatus( allMinesweeperObjects.open( allMinesweeperObjects.getTileByXY( x, y)));
        notifyViews( );
    }

    @Override
    protected void restart( ) {
    }

    @Override
    protected void nextLevel( ) {
    }

    @Override
    protected void prevLevel( ) {
    }

    @Override
    protected void win( ) {
    }
}
package timmax.tilegame.game.minesweeper.model;

import timmax.tilegame.basemodel.ServerBaseModel;

import timmax.tilegame.game.minesweeper.model.gameevent.GameEventOneTileChangeFlag;
import timmax.tilegame.game.minesweeper.model.gameevent.GameEventMinesweeperPersistentParams;
import timmax.tilegame.game.minesweeper.model.gameevent.GameEventMinesweeperVariableParamsFlag;
import timmax.tilegame.game.minesweeper.model.gameevent.GameEventMinesweeperVariableParamsOpenClose;
import timmax.tilegame.game.minesweeper.model.gameobject.AllMinesweeperObjects;
import timmax.tilegame.game.minesweeper.model.gameobject.LevelGenerator;
import timmax.tilegame.transport.TransportOfModel;

// Модель игры Сапёр
public class MinesweeperModel extends ServerBaseModel {
    private static final int REST_OF_MINE_INSTALLATION_IN_PERCENTS = 10;
    private final static int SIDE_OF_WIDTH = 15;
    private final static int SIDE_OF_HEIGHT = 10;

    private final LevelGenerator levelGenerator = new LevelGenerator( );

    private AllMinesweeperObjects allMinesweeperObjects;

    public MinesweeperModel( TransportOfModel transportOfModel) {
        super( transportOfModel);
    }

    @Override
    public void createNewGame( ) {
        createNewGame( SIDE_OF_WIDTH, SIDE_OF_HEIGHT);
        addGameEventIntoQueueAndNotifyViews( new GameEventMinesweeperPersistentParams( allMinesweeperObjects.getCountOfMines( )));
    }

    @Override
    public void createNewGame( int width, int height) {
        allMinesweeperObjects = levelGenerator.getLevel( width, height, REST_OF_MINE_INSTALLATION_IN_PERCENTS);
        allMinesweeperObjects.setModel( this);

        addGameEventIntoQueueAndNotifyViews( new GameEventMinesweeperVariableParamsOpenClose( 0, width * height));
        addGameEventIntoQueueAndNotifyViews( new GameEventMinesweeperVariableParamsFlag( 0, allMinesweeperObjects.getCountOfMines( )));
        super.createNewGame( width, height);
    }

    public void inverseFlag( int x, int y) {
        if ( verifyGameStatusNotGameAndMayBeCreateNewGame( )) {
            return;
        }
        try {
            boolean isFlag = allMinesweeperObjects.inverseFlag(allMinesweeperObjects.getTileByXY(x, y));
            addGameEventIntoQueueAndNotifyViews( new GameEventOneTileChangeFlag( x, y, isFlag));
        } catch ( RuntimeException rte) {
        }
    }

    public void open( int x, int y) {
        if ( verifyGameStatusNotGameAndMayBeCreateNewGame( )) {
            return;
        }
        setGameStatus( allMinesweeperObjects.open( allMinesweeperObjects.getTileByXY( x, y)));
    }

    @Override
    public void restart( ) {
    }

    @Override
    public void nextLevel( ) {
    }

    @Override
    public void prevLevel( ) {
    }

    @Override
    public void win( ) {
    }
}
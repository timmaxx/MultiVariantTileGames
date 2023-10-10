package timmax.tilegame.game.sokoban.model;

import java.net.URISyntaxException;
import java.nio.file.Paths;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.GameStatus;
import timmax.tilegame.basemodel.tile.Direction;
import timmax.tilegame.basemodel.gameevent.GameEventGameOver;

import timmax.tilegame.game.sokoban.model.gameevent.GameEventOneTileSokobanChangeable;
import timmax.tilegame.game.sokoban.model.gameevent.GameEventSokobanPersistentParams;
import timmax.tilegame.game.sokoban.model.gameevent.GameEventSokobanVariableParamsCountOfBoxesInHouses;
import timmax.tilegame.game.sokoban.model.gameevent.GameEventSokobanVariableParamsCountOfSteps;
import timmax.tilegame.game.sokoban.model.gameobject.*;
import timmax.tilegame.game.sokoban.model.route.Route;
import timmax.tilegame.game.sokoban.model.route.Step;

import static timmax.tilegame.basemodel.GameStatus.*;
import static timmax.tilegame.game.sokoban.model.gameobject.WhoMovableInTile.*;

public class SokobanModel extends BaseModel {
    private static LevelLoader levelLoader;

    private final CurrentLevel currentLevel = new CurrentLevel( );

    private AllSokobanObjects allSokobanObjects;
    private int countOfBoxesInHomes;
    private Route route;
    private Route routeRedo = new Route( );


    static {
        try {
            levelLoader = new LevelLoader( Paths.get( SokobanModel.class.getResource( "levels.txt").toURI( )));
        } catch ( URISyntaxException e) {
        }
    }

    @Override
    public void createNewGame( ) {
        allSokobanObjects = levelLoader.getLevel( currentLevel.getValue( ));
        super.createNewGame( allSokobanObjects.getWidth( ), allSokobanObjects.getHeight( ));
        for ( int y = 0; y < allSokobanObjects.getHeight( ); y++) {
            for ( int x = 0; x < allSokobanObjects.getWidth( ); x++) {
                WhoPersistentInTile whoPersistentInTile = allSokobanObjects.getWhoPersistentInTile( x, y);
                WhoMovableInTile whoMovableInTile = allSokobanObjects.getWhoMovableInTile( x, y);
                addGameEventIntoQueue( new GameEventOneTileSokobanChangeable( x, y, whoPersistentInTile, whoMovableInTile));
            }
        }
        route = new Route( );
        routeRedo = new Route( );

        addGameEventIntoQueue( new GameEventSokobanPersistentParams( allSokobanObjects.getCountOfHomesBoxes( )));
        addGameEventIntoQueue( new GameEventSokobanVariableParamsCountOfSteps( 0));
        calcCountOfBoxesInHomes( );
        addGameEventIntoQueue( new GameEventSokobanVariableParamsCountOfBoxesInHouses( countOfBoxesInHomes));
        notifyViews( );
    }

    public void moveUndo( ) {
        if ( verifyGameStatusNotGameAndMayBeCreateNewGame( )) {
            return;
        }
        if ( route.size( ) <= 0) {
            return;
        }

        Step step = route.pop( );
        Player player = allSokobanObjects.getPlayer( );

        int oldBoxX;
        int oldBoxY;
        WhoMovableInTile oldWhoMovableInTile;
        if ( step.isBoxMoved( )) {
            for ( Box box: allSokobanObjects.getBoxes( )) {
                if ( box.isCollision( player, step.oppositeStepDirection( ))) {
                    oldBoxX = box.getX( );
                    oldBoxY = box.getY( );
                    box.move( step.oppositeStepDirection( ));

                    WhoPersistentInTile oldWhoPersistentInTile = allSokobanObjects.getWhoPersistentInTile( oldBoxX, oldBoxY);
                    addGameEventIntoQueue( new GameEventOneTileSokobanChangeable( oldBoxX, oldBoxY, oldWhoPersistentInTile, IS_NOBODY));

                    break;
                }
            }
            oldWhoMovableInTile = IS_BOX;
        } else {
            oldWhoMovableInTile = IS_NOBODY;
        }

        Direction direction = step.oppositeStepDirection( );

        {   // ToDo: Удалить повторяющийся блок в методах moveUndo и movePlayerIfPossible
            int oldX = player.getX( );
            int oldY = player.getY( );

            player.move( direction);

            int newX = player.getX( );
            int newY = player.getY( );

            WhoPersistentInTile oldWhoPersistentInTile = allSokobanObjects.getWhoPersistentInTile( oldX, oldY);
            addGameEventIntoQueue( new GameEventOneTileSokobanChangeable( oldX, oldY, oldWhoPersistentInTile, oldWhoMovableInTile));

            WhoPersistentInTile newWhoPersistentInTile = allSokobanObjects.getWhoPersistentInTile( newX, newY);
            addGameEventIntoQueue( new GameEventOneTileSokobanChangeable( newX, newY, newWhoPersistentInTile, IS_PLAYER));

            notifyViews( );
        }

        routeRedo.push( step);
        addGameEventIntoQueue( new GameEventSokobanVariableParamsCountOfSteps( route.size( )));
        if ( step.isBoxMoved( )) {
            calcCountOfBoxesInHomes( );
            addGameEventIntoQueue( new GameEventSokobanVariableParamsCountOfBoxesInHouses( countOfBoxesInHomes));
        }
        notifyViews( );
    }

    public void move( Direction direction) {
        if ( verifyGameStatusNotGameAndMayBeCreateNewGame( )) {
            return;
        }
        movePlayerIfPossible( direction, false);
        routeRedo = new Route( );
    }

    public void moveRedo( ) {
        if ( verifyGameStatusNotGameAndMayBeCreateNewGame( )) {
            return;
        }
        if ( routeRedo.size( ) <= 0) {
            return;
        }
        Step step = routeRedo.pop( );
        movePlayerIfPossible( step.getDirection( ), true);
    }

    private boolean isCollisionWithWall( CollisionObject gameObject, Direction direction) {
        for( Wall wall: allSokobanObjects.getWalls( )) {
            if ( gameObject.isCollision( wall, direction)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCollisionWithBox( CollisionObject gameObject, Direction direction) {
        for( Box box: allSokobanObjects.getBoxes( )) {
            if ( gameObject.isCollision( box, direction)) {
                return true;
            }
        }
        return false;
    }

    private void movePlayerIfPossible( Direction direction, boolean isRedo) {
        Player player = allSokobanObjects.getPlayer( );
        if ( !isRedo) {
            if ( isCollisionWithWall( player, direction)) {
                return;
            }
        }
        boolean isBoxMoved = false;
        int newBoxX;
        int newBoxY;

        for( Box box: allSokobanObjects.getBoxes( )) {
            if ( player.isCollision( box, direction)) {
                if ( !isRedo) {
                    if ( isCollisionWithWall( box, direction)) {
                        return;
                    }
                    if ( isCollisionWithBox( box, direction)) {
                        return;
                    }
                }
                box.move( direction);

                newBoxX = box.getX( );
                newBoxY = box.getY( );

                WhoPersistentInTile newWhoPersistentInTile = allSokobanObjects.getWhoPersistentInTile( newBoxX, newBoxY);
                addGameEventIntoQueue( new GameEventOneTileSokobanChangeable( newBoxX, newBoxY, newWhoPersistentInTile, IS_BOX));

                isBoxMoved = true;
                break;
            }
        }

        {   // ToDo: Удалить повторяющийся блок в методах moveUndo и movePlayerIfPossible
            int oldX = player.getX( );
            int oldY = player.getY( );

            player.move( direction);

            int newX = player.getX( );
            int newY = player.getY( );

            WhoPersistentInTile oldWhoPersistentInTile = allSokobanObjects.getWhoPersistentInTile( oldX, oldY);
            addGameEventIntoQueue( new GameEventOneTileSokobanChangeable( oldX, oldY, oldWhoPersistentInTile, IS_NOBODY));

            WhoPersistentInTile newWhoPersistentInTile = allSokobanObjects.getWhoPersistentInTile( newX, newY);
            addGameEventIntoQueue( new GameEventOneTileSokobanChangeable( newX, newY, newWhoPersistentInTile, IS_PLAYER));

            notifyViews( );
        }

        route.push( new Step( direction, isBoxMoved));

        addGameEventIntoQueue( new GameEventSokobanVariableParamsCountOfSteps( route.size( )));
        if ( isBoxMoved) {
            calcCountOfBoxesInHomes( );
            addGameEventIntoQueue( new GameEventSokobanVariableParamsCountOfBoxesInHouses( countOfBoxesInHomes));
        }
        notifyViews( );

        checkCompletion( );
    }

    private void calcCountOfBoxesInHomes( ) {
        int countOfBoxesInHomes = 0;
        for ( Home home : allSokobanObjects.getHomes( )) {
            for ( Box box : allSokobanObjects.getBoxes( )) {
                if ( box.getX( ) == home.getX( ) && box.getY( ) == home.getY( )) {
                    countOfBoxesInHomes++;
                    break;
                }
            }
        }
        this.countOfBoxesInHomes = countOfBoxesInHomes;
    }

    private void checkCompletion( ) {
        // Этот метод должен проверить, пройден ли уровень (т.е. на всех ли домах стоят ящики?).
        // Если условие выполнено, то проинформировать слушателей событий, что текущий уровень завершен.
        if ( countOfBoxesInHomes == allSokobanObjects.getCountOfHomesBoxes( )) {
            win( );
        }
    }

    @Override
    protected void win( ) {
        setGameStatus( GameStatus.VICTORY);
        currentLevel.incValue( );
        addGameEventIntoQueueAndNotifyViews( new GameEventGameOver( VICTORY));
    }

    @Override
    public void nextLevel( ) {
        if ( verifyGameStatusNotGameAndMayBeCreateNewGame( )) {
            return;
        }
        setGameStatus( FORCE_RESTART_OR_CHANGE_LEVEL);
        currentLevel.incValue( );
        addGameEventIntoQueueAndNotifyViews( new GameEventGameOver( FORCE_RESTART_OR_CHANGE_LEVEL));
    }

    @Override
    public void prevLevel( ) {
        if ( verifyGameStatusNotGameAndMayBeCreateNewGame( )) {
            return;
        }
        setGameStatus( FORCE_RESTART_OR_CHANGE_LEVEL);
        currentLevel.decValue( );
        addGameEventIntoQueueAndNotifyViews( new GameEventGameOver( FORCE_RESTART_OR_CHANGE_LEVEL));
    }

    @Override
    public void restart( ) {
        if ( verifyGameStatusNotGameAndMayBeCreateNewGame( )) {
            return;
        }
        setGameStatus( FORCE_RESTART_OR_CHANGE_LEVEL);
        addGameEventIntoQueueAndNotifyViews( new GameEventGameOver( FORCE_RESTART_OR_CHANGE_LEVEL));
    }
}
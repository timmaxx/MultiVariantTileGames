package timmax.sokoban.model;

import timmax.basetilemodel.*;
import timmax.basetilemodel.gameevent.GameEventGameOver;
import timmax.basetilemodel.tile.*;
import timmax.sokoban.model.gameevent.*;
import timmax.sokoban.model.gameobject.*;
import timmax.sokoban.model.route.*;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import static timmax.basetilemodel.GameStatus.*;
import static timmax.sokoban.model.gameobject.WhoMovableInTile.*;

public class SokobanModel extends BaseModel {
    private static LevelLoader levelLoader;

    private final CurrentLevel currentLevel = new CurrentLevel( );

    private AllSokobanObjects allSokobanObjects;

    private Route route;
    private Route routeRedo = new Route( );

    static {
        try {
            System.out.println( SokobanModel.class.getResource( "levels.txt"));
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
        notifyViews( );
        route = new Route( );
        routeRedo = new Route( );
    }

    public void moveUndo( ) {
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
                    addGameEventIntoQueueAndNotifyViews( new GameEventOneTileSokobanChangeable( oldBoxX, oldBoxY, oldWhoPersistentInTile, IS_NOBODY));

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
            addGameEventIntoQueueAndNotifyViews( new GameEventOneTileSokobanChangeable( oldX, oldY, oldWhoPersistentInTile, oldWhoMovableInTile));

            WhoPersistentInTile newWhoPersistentInTile = allSokobanObjects.getWhoPersistentInTile( newX, newY);
            addGameEventIntoQueueAndNotifyViews( new GameEventOneTileSokobanChangeable( newX, newY, newWhoPersistentInTile, IS_PLAYER));
        }

        routeRedo.push( step);
    }

    public void move( Direction direction) {
        movePlayerIfPossible( direction, false);
        routeRedo = new Route( );
    }

    public void moveRedo( ) {
        if ( routeRedo.size( ) <= 0) {
            return;
        }
        Step step = routeRedo.pop( );
        movePlayerIfPossible( step.getDirection( ), true);
    }
/*
    private boolean isCollisionWithWall( CollisionObject gameObject, Direction direction) {
        for( Wall wall: allSokobanObjects.getWalls( )) {
            if ( gameObject.isCollision( wall, direction)) {
                return false;
            }
        }
        return true;
    }
*/
    private boolean isCollisionWithWall( CollisionObject gameObject, Direction direction) {
        for( Wall wall: allSokobanObjects.getWalls( )) {
            if ( gameObject.isCollision( wall, direction)) {
                return true;
            }
        }
        return false;
    }

/*
    private boolean isCollisionWithBox( CollisionObject gameObject, Direction direction) {
        for( Box box: allSokobanObjects.getBoxes( )) {
            if ( gameObject.isCollision( box, direction)) {
                return false;
            }
        }
        return true;
    }
*/
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
                    if (isCollisionWithWall(box, direction)) {
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
                addGameEventIntoQueueAndNotifyViews( new GameEventOneTileSokobanChangeable( newBoxX, newBoxY, newWhoPersistentInTile, IS_BOX));

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
            addGameEventIntoQueueAndNotifyViews(new GameEventOneTileSokobanChangeable( oldX, oldY, oldWhoPersistentInTile, IS_NOBODY));

            WhoPersistentInTile newWhoPersistentInTile = allSokobanObjects.getWhoPersistentInTile( newX, newY);
            addGameEventIntoQueueAndNotifyViews(new GameEventOneTileSokobanChangeable( newX, newY, newWhoPersistentInTile, IS_PLAYER));
        }

        route.push( new Step( direction, isBoxMoved));
        checkCompletion( );
    }

    private void checkCompletion( ) {
        // Этот метод должен проверить, пройден ли уровень (т.е. на всех ли домах стоят ящики?).
        // Если условие выполнено, то проинформировать слушателя событий, что текущий уровень завершен.
        int count = 0;
        for ( Home home : allSokobanObjects.getHomes( )) {
            for ( Box box : allSokobanObjects.getBoxes( )) {
                if ( box.getX( ) == home.getX( ) && box.getY( ) == home.getY( )) {
                    count++;
                    break;
                }
            }
        }
        if ( count == allSokobanObjects.getCountOfHomesBoxes( )) {
            win( );
        }
    }

    @Override
    protected void win( ) {
        setGameStatus( GameStatus.VICTORY);
        currentLevel.incValue( );
        addGameEventIntoQueue( new GameEventGameOver( VICTORY));
    }

    @Override
    public void nextLevel( ) {
        setGameStatus( FORCE_RESTART_OR_CHANGE_LEVEL);
        currentLevel.incValue( );
        addGameEventIntoQueue( new GameEventGameOver( FORCE_RESTART_OR_CHANGE_LEVEL));
    }

    @Override
    public void prevLevel( ) {
        setGameStatus( FORCE_RESTART_OR_CHANGE_LEVEL);
        currentLevel.decValue( );
        addGameEventIntoQueue( new GameEventGameOver( FORCE_RESTART_OR_CHANGE_LEVEL));
    }

    @Override
    public void restart( ) {
        setGameStatus( FORCE_RESTART_OR_CHANGE_LEVEL);
        addGameEventIntoQueue( new GameEventGameOver( FORCE_RESTART_OR_CHANGE_LEVEL));
    }
}
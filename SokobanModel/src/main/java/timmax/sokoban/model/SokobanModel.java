package timmax.sokoban.model;

import timmax.basetilemodel.*;
import timmax.basetilemodel.gameevent.GameEventGameOver;
import timmax.basetilemodel.tile.*;
import timmax.sokoban.model.gameevent.*;
import timmax.sokoban.model.gameobject.*;
import timmax.sokoban.model.route.*;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.*;

import static timmax.basetilemodel.GameStatus.FORCE_RESTART_OR_CHANGE_LEVEL;
import static timmax.basetilemodel.GameStatus.VICTORY;

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


    public List< Tile> getListOfXY( int x, int y) {
        List< Tile> result = new ArrayList< >( );
        for ( Tile tile : allSokobanObjects.getAll( )) {
            if ( tile.getX( ) == x && tile.getY( ) == y) {
                result.add( tile);
            }
        }

        return result;
    }

    @Override
    public void createNewGame( ) {
        allSokobanObjects = levelLoader.getLevel( currentLevel.getValue());
        super.createNewGame( allSokobanObjects.getWidth( ), allSokobanObjects.getHeight( ));
        route = new Route( );
        routeRedo = new Route( );
    }

    public void moveUndo( ) {
        if ( route.size( ) <= 0) {
            return;
        }

        Step step = route.pop( );
        Player player = allSokobanObjects.getPlayer( );

        int oldBoxX = -1;
        int oldBoxY = -1;
        if ( step.isBoxMoved( )) {
            for ( Box box: allSokobanObjects.getBoxes( )) {
                if ( box.isCollision( player, step.oppositeStepDirection( ))) {
                    oldBoxX = box.getX( );
                    oldBoxY = box.getY( );
                    box.move( step.oppositeStepDirection( ));
                    break;
                }
            }
        }
        int oldX = player.getX( );
        int oldY = player.getY( );
        player.move( step.oppositeStepDirection( ));
        int newX = player.getX( );
        int newY = player.getY( );
        boolean isBoxMoved = step.isBoxMoved( );
        // ToDo: Лучше в очередь записать две или три записи о тех клетках, которые были изменены.
        //  И по каждой из них указать ( пустая / дом) и ( пустая / игрок / коробка).
        if ( isBoxMoved) {
            addGameEventIntoQueueAndNotifyViews( new GameEventPlayerWithBoxMoved( oldX, oldY, newX, newY, oldBoxX, oldBoxY));
        } else {
            addGameEventIntoQueueAndNotifyViews( new GameEventPlayerMoved( oldX, oldY, newX, newY));
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

    private boolean checkWallCollision( CollisionObject gameObject, Direction direction) {
        for( Wall wall: allSokobanObjects.getWalls( )) {
            if ( gameObject.isCollision( wall, direction)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkBoxCollision( CollisionObject gameObject, Direction direction) {
        for( Box box: allSokobanObjects.getBoxes( )) {
            if ( gameObject.isCollision( box, direction)) {
                return false;
            }
        }
        return true;
    }

    private boolean movePlayerIfPossible( Direction direction, boolean isRedo) {
        Player player = allSokobanObjects.getPlayer( );
        if ( !isRedo) {
            if ( !checkWallCollision( player, direction)) {
                return false;
            }
        }
        boolean isBoxMoved = false;
        int newBoxX = -1;
        int newBoxY = -1;
        for( Box box: allSokobanObjects.getBoxes( )) {
            if ( player.isCollision( box, direction)) {
                if ( !isRedo) {
                    if ( !checkWallCollision( box, direction)) {
                        return false;
                    }
                    if ( !checkBoxCollision( box, direction)) {
                        return false;
                    }
                }
                box.move( direction);
                newBoxX = box.getX( );
                newBoxY = box.getY( );
                isBoxMoved = true;
                break;
            }
        }
        int oldX = player.getX( );
        int oldY = player.getY( );
        player.move( direction);
        int newX = player.getX( );
        int newY = player.getY( );
        // ToDo: Лучше в очередь записать две или три записи о тех клетках, которые были изменены.
        //  И по каждой из них указать ( пустая / дом) и ( пустая / игрок / коробка).
        if ( isBoxMoved) {
            addGameEventIntoQueueAndNotifyViews( new GameEventPlayerWithBoxMoved( oldX, oldY, newX, newY, newBoxX, newBoxY));
        } else {
            addGameEventIntoQueueAndNotifyViews( new GameEventPlayerMoved( oldX, oldY, newX, newY));
        }
        route.push( new Step( direction, isBoxMoved));
        checkCompletion( );

        return true;
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

    private void win( ) {
        setGameStatus( GameStatus.VICTORY);
        currentLevel.incValue( );
        addGameEventIntoQueue( new GameEventGameOver( VICTORY));
    }

    public void incLevel( ) {
        setGameStatus( FORCE_RESTART_OR_CHANGE_LEVEL);
        currentLevel.incValue( );
        addGameEventIntoQueue( new GameEventGameOver( FORCE_RESTART_OR_CHANGE_LEVEL));
    }

    public void decLevel( ) {
        setGameStatus( FORCE_RESTART_OR_CHANGE_LEVEL);
        currentLevel.decValue( );
        addGameEventIntoQueue( new GameEventGameOver( FORCE_RESTART_OR_CHANGE_LEVEL));
    }

    public void restart( ) {
        setGameStatus( FORCE_RESTART_OR_CHANGE_LEVEL);
        addGameEventIntoQueue( new GameEventGameOver( FORCE_RESTART_OR_CHANGE_LEVEL));
    }
}
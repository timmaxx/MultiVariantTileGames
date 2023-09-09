package timmax.sokoban.model;

import timmax.basetilemodel.*;
import timmax.basetilemodel.tile.*;
import timmax.sokoban.model.gameobject.*;
import timmax.sokoban.model.route.*;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.*;

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
        super.createNewGame( allSokobanObjects.width( ), allSokobanObjects.height( ));
        route = new Route( );
        routeRedo = new Route( );
    }

    public void moveUndo( ) {
        if ( route.size( ) <= 0) {
            return;
        }

        Step step = route.pop( );
        Player player = allSokobanObjects.player();

        if ( step.isBoxMoved( )) {
            for ( Box box: allSokobanObjects.boxes()) {
                if ( box.isCollision( player, step.oppositeStepDirection( ))) {
                    box.move( step.oppositeStepDirection( ));
                    break;
                }
            }
        }
        player.move( step.oppositeStepDirection( ));
        routeRedo.push( step);
        notifyViews( );
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
        for( Wall wall: allSokobanObjects.walls( )) {
            if ( gameObject.isCollision( wall, direction)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkBoxCollision( CollisionObject gameObject, Direction direction) {
        for( Box box: allSokobanObjects.boxes( )) {
            if ( gameObject.isCollision( box, direction)) {
                return false;
            }
        }
        return true;
    }

    private boolean movePlayerIfPossible( Direction direction, boolean isRedo) {
        Player player = allSokobanObjects.player( );
        if ( !isRedo) {
            if ( !checkWallCollision( player, direction)) {
                return false;
            }
        }
        boolean isBoxMoved = false;
        for( Box box: allSokobanObjects.boxes( )) {
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
                isBoxMoved = true;
                break;
            }
        }
        player.move( direction);
        route.push( new Step( direction, isBoxMoved));
        checkCompletion( );
        notifyViews( );

        return true;
    }

    private void checkCompletion( ) {
        // Этот метод должен проверить пройден ли уровень (на всех ли домах стоят ящики, их координаты должны совпадать).
        // Если условие выполнено, то проинформировать слушателя событий, что текущий уровень завершен.
        int count = 0;
        for ( Home home : allSokobanObjects.homes( )) {
            for ( Box box : allSokobanObjects.boxes( )) {
                if ( box.getX( ) == home.getX( ) && box.getY( ) == home.getY( )) {
                    count++;
                    break;
                }
            }
        }
        if ( count == allSokobanObjects.countOfHomesBoxes( )) {
            win( );
        }
    }

    private void win( ) {
        gameStatus = GameStatus.VICTORY;
        currentLevel.incValue( );
    }

    public void incLevel( ) {
        gameStatus = GameStatus.FORCE_RESTART_OR_CHANGE_LEVEL;
        currentLevel.incValue( );
    }

    public void decLevel( ) {
        gameStatus = GameStatus.FORCE_RESTART_OR_CHANGE_LEVEL;
        currentLevel.decValue( );
    }

    public void restart( ) {
        gameStatus = GameStatus.FORCE_RESTART_OR_CHANGE_LEVEL;
    }
}
package timmax.sokoban.model;

import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.Direction;
import timmax.basetilemodel.GameStatus;
import timmax.basetilemodel.XY;
import timmax.sokoban.model.gameobject.*;

import java.net.URISyntaxException;
import java.nio.file.Paths;

public class SokobanModel extends BaseModel< SokobanTile> {
    private static LevelLoader levelLoader;

    private final CurrentLevel currentLevel = new CurrentLevel( );
//  private LocalScore localScore;

    private Route route;
    private Route routeRedo = new Route( );


    // private boolean isLevelRestarted;
    private boolean isPlayerMoved;
    // private boolean isLevelCompleted;

    private boolean isBoxMoved;

    private int countOfHomesAndBoxes;

    static {
        try {
            System.out.println( SokobanModel.class.getResource("levels.txt"));
            levelLoader = new LevelLoader( Paths.get( SokobanModel.class.getResource("levels.txt").toURI( )));
        } catch ( URISyntaxException e) {
        }
    }

    public void createNewGame( ) {
        super.createNewGame( levelLoader.getLevel( currentLevel.getValue( )));
        calcCountOfHomesAndBoxes( );
        route = new Route( );
        routeRedo = new Route( );
/*
        currentLevel = new CurrentLevel( );
        localScore = new LocalScore( );
        localScore.setValue( -1);
        */
    }

    private void calcCountOfHomesAndBoxes( ) {
        int countOfHomes = 0;
        int countOfBoxes = 0;
        for ( int y = 0; y < getHeight( ); y++) {
            for ( int x = 0; x < getWidth( ); x++) {
                SokobanTile tile = getTileByXY( x, y);
                if ( tile == null) {
                    break;
                }
                if ( tile.isBox( )) {
                    countOfBoxes++;
                }
                if ( tile.isHome( )) {
                    countOfHomes++;
                }
            }
        }

        if ( countOfBoxes != countOfHomes) {
            throw new RuntimeException( "Count of boxes is not equal count of homes!");
        }
        countOfHomesAndBoxes = countOfBoxes;
    }

/*
    public boolean isPlayerMoved( ) {
        return isPlayerMoved;
    }
*/
    public void moveExec( Direction direction) {
        // 15.4.1. Проверить столкновение со стеной (метод checkWallCollision()), если есть столкновение - выйти из метода.
        if ( checkWallCollision( getPlayer( ), direction)) {
            return;
        }

        // 15.4.2. Проверить столкновение с ящиками (метод checkBoxCollisionAndMoveIfAvailable()), если есть столкновение - выйти из метода.
        if ( checkBoxCollisionAndMoveIfAvailable( direction)) {
            return;
        }

        // 15.4.3. Передвинуть игрока в направлении direction.
        // В результате движения один или два объекта изменят свое положение.
        // Именно их и можно было-бы перерисовать, а не всё поле.
        move( getPlayer( ), direction);

        route.push( new Step( direction, isBoxMoved));

        // 15.4.4. Проверить завершен ли уровень.
        checkCompletion( );

        notifyViews( );
        // После того, как все наблюдатели отработали перерисовку, список объектов для перерисовки следует очистить.
    }

    private void move( XY player, Direction direction) {
        SokobanTile playerTile = getTileByXY( player.getX( ), player.getY( ));
        XY dXdY = direction.getDxDy( );
        XY nextXYOneTile = new XY( player.getX( ) + dXdY.getX( ), player.getY( ) + dXdY.getY( ));
        SokobanTile nextOneTile = getTileByXY( nextXYOneTile.getX( ), nextXYOneTile.getY( ));
        if ( !nextOneTile.isNotWallAndNotBox( )) {
            XY nextXYTwoTile = new XY( nextXYOneTile.getX( ) + dXdY.getX( ), nextXYOneTile.getY( ) + dXdY.getY( ));
            SokobanTile nextTwoTile = getTileByXY( nextXYTwoTile.getX(), nextXYTwoTile.getY());

            nextTwoTile.setBox( true);
            nextOneTile.setBox( false);
            isBoxMoved = true;
        }
        nextOneTile.setPlayer( true);
        playerTile.setPlayer( false);
        isPlayerMoved = true;
    }

    public void moveUndo( ) {
        // Если в маршруте есть хоть один шаг, то:
        if ( route.size( ) <= 0) {
            return;
        }

        Step step = route.pop( );

        XY playerXY = getPlayer( );
        SokobanTile playerPlaceTile = getTileByXY( playerXY);
        playerPlaceTile.setPlayer( false);

        Direction tmpDirection = step.oppositeStepDirection( );
        XY prevPlayerPlaceXY = playerXY.add( tmpDirection);
        SokobanTile prevPlayerPlaceTile = getTileByXY( prevPlayerPlaceXY);
        prevPlayerPlaceTile.setPlayer( true);

        if ( step.isBoxMoved( )) {
            XY prevBoxPlaceXY = playerXY.add( step.getDirection( ));
            SokobanTile prevBoxPlaceXYTile = getTileByXY( prevBoxPlaceXY);
            prevBoxPlaceXYTile.setBox( false);

            playerPlaceTile.setBox( true);
        }

        isPlayerMoved = true;
        routeRedo.push( step);
        notifyViews( );
    }

    public void move( Direction direction) {
        moveExec( direction);
        routeRedo = new Route( );
    }

    public void moveRedo( ) {
        if ( routeRedo.size( ) <= 0) {
            return;
        }
        Step step = routeRedo.pop( );
        moveExec( step.getDirection( ));
    }


    private boolean checkWallCollision( XY xy, Direction direction) {
        // Этот метод проверяет столкновение со стеной.
        // Он должен вернуть true, если при движении объекта XY в направлении direction произойдет столкновение со стеной, иначе false.
        XY dXdY = direction.getDxDy( );
        return getTileByXY(xy.getX( ) + dXdY.getX( ), xy.getY( ) + dXdY.getY( )).isWall( );
    }

    private XY getPlayer( ) {
        for ( int y = 0; y < getHeight( ); y++) {
            for ( int x = 0; x < getWidth( ); x++) {
                if ( getTileByXY( x, y) == null) {
                    break;
                }
                if ( getTileByXY( x, y).isPlayer( )) {
                    return new XY( x, y);
                }
            }
        }
        throw new RuntimeException( "Player is not found!");
    }

    private boolean checkBoxCollisionAndMoveIfAvailable( Direction direction) {
        // Этот метод проверяет столкновение с ящиками. Метод должен:
        // 15.2.1. Вернуть true, если игрок не может быть сдвинут в направлении direction
        // (там находится: или ящик, за которым стена; или ящик, за которым еще один ящик).
        isBoxMoved = false;
        XY player = getPlayer( );
        XY dXdY = direction.getDxDy( );
        XY nextXYOneTile = new XY( player.getX( ) + dXdY.getX( ), player.getY( ) + dXdY.getY( ));
        SokobanTile nextOneTile = getTileByXY( nextXYOneTile.getX( ), nextXYOneTile.getY( ));
        if ( nextOneTile.isNotWallAndNotBox( )) {
            return false;
        }
        if ( nextOneTile.isWall( )) {
            return true;
        }
        if ( nextOneTile.isBox( )) {
            isBoxMoved = true;
            XY nextXYTwoTile = new XY(nextXYOneTile.getX( ) + dXdY.getX( ), nextXYOneTile.getY( ) + dXdY.getY( ));
            SokobanTile nextTwoTile = getTileByXY(nextXYTwoTile.getX( ), nextXYTwoTile.getY( ));
            return !nextTwoTile.isNotWallAndNotBox( );
        }
        // 15.2.2. Вернуть false, если игрок может быть сдвинут в направлении direction (там находится: или свободная ячейка; или дом; или ящик, за которым свободная ячейка или дом).
        // При этом, если на пути есть ящик, который может быть сдвинут, то необходимо переместить этот ящик на новые координаты.
        // Обрати внимание, что все объекты перемещаются на единичное значение.
        return false;
    }

    private void checkCompletion( ) {
        // Этот метод должен проверить пройден ли уровень (на всех ли домах стоят ящики, их координаты должны совпадать).
        // Если условие выполнено, то проинформировать слушателя событий, что текущий уровень завершен.
        int count = 0;
        for ( int y = 0; y < getHeight( ); y++) {
            for ( int x = 0; x < getWidth( ); x++) {
                SokobanTile tile = getTileByXY( x, y);
                if ( tile == null) {
                    break;
                }
                if ( tile.isBox( ) && tile.isHome( )) {
                    count++;
                }
            }
        }
        if ( count == countOfHomesAndBoxes) {
            win( );
        }
    }

    private void win( ) {
        gameStatus = GameStatus.VICTORY;
        currentLevel.incValue( );
    }

    public void setLevel( int level) {
        currentLevel.setValue( level);
    }

    public void incLevel( ) {
        currentLevel.incValue( );
    }

    public void decLevel( ) {
        currentLevel.decValue();
    }

    public void restart( ) {
        currentLevel.restart( );
    }

    public boolean isCurrentLevelChanged( ) {
        return currentLevel.isCurrentLevelChanged( );
    }

    public void dropCurrentLevelChanged( ) {
        currentLevel.dropCurrentLevelChanged();
    }
}
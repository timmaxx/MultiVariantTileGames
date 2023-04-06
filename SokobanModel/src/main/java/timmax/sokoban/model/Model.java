package timmax.sokoban.model;


import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.XY;
import timmax.sokoban.model.gameobject.*;

import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Model extends BaseModel< Tile> {

    private static LevelLoader levelLoader;

    private CurrentLevel currentLevel;
    private LocalScore localScore;

    private Route route;
    private Route routeRedo;

    private boolean isLevelRestarted;
    private boolean isPlayerMoved;
    private boolean isLevelCompleted;

    // private boolean isBoxMoved;

    static {
        try {
            System.out.println( Model.class.getResource("levels.txt"));
            levelLoader = new LevelLoader( Paths.get( Model.class.getResource("levels.txt").toURI( )));
        } catch ( URISyntaxException e) {
        }
    }

    public Model( int level) {
        super( levelLoader.getLevel( level));

        currentLevel = new CurrentLevel( );
        localScore = new LocalScore( );
        localScore.setValue( -1);
    }

    @Override
    public void toDoAfterNotifyViews( ) {
        isLevelRestarted = false;
        isLevelCompleted = false;
    }

    public boolean isLevelRestarted( ) {
        return isLevelRestarted;
    }

    public boolean isPlayerMoved( ) {
        return isPlayerMoved;
    }

    public boolean isLevelCompleted( ) {
        return isLevelCompleted;
    }

    public boolean isLocalScoreChanged( ) { return localScore.isLocalScoreChanged( );}

    public boolean isCurrentLevelChanged( ) { return currentLevel.isCurrentLevelChanged( );}

    public int getCurrentLevel( ) {
        return currentLevel.getValue( );
    }

    public int getLocalScore( ) {
        return localScore.getValue( );
    }

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
        // getPlayer( ).move( direction);
        move( getPlayer( ), direction);
        isPlayerMoved = true;
        localScore.incValue( );
        // route.push( new Step( direction, isBoxMoved));
        notifyViews( );
        // После того, как все наблюдатели отработали перерисовку, список объектов для перерисовки следует очистить.

        // 15.4.4. Проверить завершен ли уровень.
        // checkCompletion( );
    }

    private void move( XY player, Direction direction) {
        Tile playerTile = getTileByXY( player.getX( ), player.getY( ));
        XY dXdY = direction.getDxDy( );
        XY nextXYOneTile = new XY( player.getX( ) + dXdY.getX( ), player.getY( ) + dXdY.getY( ));
        Tile nextOneTile = getTileByXY( nextXYOneTile.getX( ), nextXYOneTile.getY( ));
        if ( !nextOneTile.isNotWallAndNotBox( )) {
            XY nextXYTwoTile = new XY( nextXYOneTile.getX( ) + dXdY.getX( ), nextXYOneTile.getY( ) + dXdY.getY( ));
            Tile nextTwoTile = getTileByXY( nextXYTwoTile.getX(), nextXYTwoTile.getY());

            nextTwoTile.setBox( true);
            nextOneTile.setBox( false);
        }
        nextOneTile.setPlayer( true);
        playerTile.setPlayer( false);
    }
/*
    public void moveUndo( ) {
        Step step;
        // Если в маршруте есть хоть один шаг, то:
        if ( route.size( ) <= 0) {
            return;
        }

        step = route.pop( );

        if ( step.isBoxMoved( )) {
            for ( Box box: gameObjects.getBoxes( )) {
                // Нужно найти среди ящиков тот, который стоит рядом с игроком и сдвинуть его в противоположном от step.direction направления.
                if ( box.isCollision( gameObjects.getPlayer(), step.oppositeStepDirrection( ))) {
                    gameObjects.getPlayer( ).move( step.oppositeStepDirrection( ));
                    box.move( step.oppositeStepDirrection( ));
                    break;
                }
            }
            // Если нет такого, то выйти из функции - нельзя отмотать на шаг назад!
            // Надо сделать!
        } else {
            gameObjects.getPlayer( ).move( step.oppositeStepDirrection( ));
        }
        isPlayerMoved = true;
        localScore.decValue( );
        routeRedo.push( step);
        notifyViews( );
    }
*/
    public void move( Direction direction) {
        moveExec( direction);
        routeRedo = new Route( );
    }

    public void moveRedo( ) {
        Step step;
        if ( routeRedo.size( ) <= 0) {
            return;
        }
        step = routeRedo.pop( );
        moveExec( step.getDirection( ));
    }

    public void restart( ) { // должен перезапускать текущий уровень, вызывая restartLevel с нужным параметром.
        restartLevel( currentLevel.getValue( ));
    }

    public void startPrevLevel( ) { // должен увеличивать значение переменной currentLevel, хранящей номер текущего уровня и выполнять перезапуск нового уровня.
        isLevelCompleted = true;
        notifyViews( );
        currentLevel.decValue( );
        restartLevel( currentLevel.getValue( ));
    }

    public void startNextLevel( ) { // должен увеличивать значение переменной currentLevel, хранящей номер текущего уровня и выполнять перезапуск нового уровня.
        isLevelCompleted = true;
        notifyViews( );
        currentLevel.incValue( );
        restartLevel( currentLevel.getValue( ));
    }


    private boolean checkWallCollision( XY xy, Direction direction) {
        // Этот метод проверяет столкновение со стеной.
        // Он должен вернуть true, если при движении объекта XY в направлении direction произойдет столкновение со стеной, иначе false.
        XY dXdY = direction.getDxDy();
        if ( getTileByXY( xy.getX() + dXdY.getX(), xy.getY() + dXdY.getY()).isWall()) {
            return true;
        }
        return false;
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
        return new XY( -1, -1);
    }

    private boolean checkBoxCollisionAndMoveIfAvailable( Direction direction) {
        // Этот метод проверяет столкновение с ящиками. Метод должен:
        // 15.2.1. Вернуть true, если игрок не может быть сдвинут в направлении direction (там находится: или ящик, за которым стена; или ящик за которым еще один ящик).
        // Подсказка: для определения столкновений используй методы isCollision() игровых объектов и метод checkWallCollision() модели.
        /*
        isBoxMoved = false;
        for( Box box: gameObjects.getBoxes( ) ) {
            if ( gameObjects.getPlayer( ).isCollision( box, direction)) {
                for( Box nextBox: gameObjects.getBoxes( )) {
                    if ( box == nextBox)
                        continue;
                    if ( box.isCollision( nextBox, direction)) {
                        return true;
                    }
                }
                if ( checkWallCollision( box, direction)) {
                    return true;
                }
                // 15.2.2. Вернуть false, если игрок может быть сдвинут в направлении direction (там находится: или свободная ячейка; или дом; или ящик, за которым свободная ячейка или дом).
                // При этом, если на пути есть ящик, который может быть сдвинут, то необходимо переместить этот ящик на новые координаты.
                // Обрати внимание, что все объекты перемещаются на единичное значение.
                box.move( direction);
                isBoxMoved = true;
                break; // ?
            }
        }
        return  false;
        */
        XY player = getPlayer( );
        XY dXdY = direction.getDxDy( );
        XY nextXYOneTile = new XY( player.getX( ) + dXdY.getX( ), player.getY( ) + dXdY.getY( ));
        Tile nextOneTile = getTileByXY( nextXYOneTile.getX( ), nextXYOneTile.getY( ));
        if ( nextOneTile.isNotWallAndNotBox( )) {
            return false;
        }
        if ( nextOneTile.isWall( )) {
            return true;
        }
        if ( nextOneTile.isBox( )) {
            XY nextXYTwoTile = new XY(nextXYOneTile.getX( ) + dXdY.getX( ), nextXYOneTile.getY( ) + dXdY.getY( ));
            Tile nextTwoTile = getTileByXY(nextXYTwoTile.getX( ), nextXYTwoTile.getY( ));
            if ( nextTwoTile.isNotWallAndNotBox( )) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /*
    private void checkCompletion( ) {
        // Этот метод должен проверить пройден ли уровень (на всех ли домах стоят ящики, их координаты должны совпадать).
        // Если условие выполнено, то проинформировать слушателя событий, что текущий уровень завершен.
        int count = 0;
        for ( Home home : gameObjects.getHomes( )) {
            for ( Box box : gameObjects.getBoxes( )) {
                if ( box.getX( ) == home.getX( ) && box.getY( ) == home.getY( )) {
                    count++;
                    break;
                }
            }
        }

        if ( gameObjects.getBoxes( ).size( ) == count) {
            startNextLevel( );
        }
    }
*/
    private void restartLevel( int level) { // должен получать новые игровые объекты для указанного уровня у загрузчика уровня levelLoader и сохранять их в поле gameObjects.
        // tales = levelLoader.getLevel( level);

        route = new Route( );
        routeRedo = new Route( );
        isPlayerMoved = false;
        isLevelRestarted = true;
        isLevelCompleted = false;

        currentLevel.setValue( level);
        localScore.setValue( 0);

        notifyViews( );
    }
}
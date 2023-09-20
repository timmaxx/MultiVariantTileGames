package timmax.tilegameenginejfx;

import javafx.scene.Parent;
import javafx.scene.Scene;

// Это:
// - либо как-бы мега ViewJfx, на которой будут размещены несколько ViewJfx.
// - либо в такой сцене должно быть по одной ViewJfx/
public class GameScene extends Scene {
    private final GameController gameController;
    // game используется для доступа к getCellSize, но этот параметр относится исключительно только к MainFieldJfx
    // ToDo: Удалить отсюда game, но при этом опять переделывать архитектуру...
    // ToDo: Сначала создать отдельное тестовое приложение с двумя-тремя сценами и посмотреть, как там будет с перехватом событий.
    //       Хотя, вроде к Stage только одна Scene привязывается? Если так, то не получится.
    private final Game game;


    public GameScene( Parent root, Game game, GameController gameController) {
        super( root);
        this.game = game;
        this.gameController = gameController;
        setOnMouseClicked( );
        setOnKeyPressed( );
        setOnKeyReleased( );
    }

    // ToDo:
    // 1. Либо в пределах сцены определять над каким узлом произошло событие и ему отправлять его.
    // 2. Либо создавать отдельную сцену для каждой ViewJfx?

    // setOnMouseClicked в классе Scene объявлен final, поэтому не получилось его перегрузить в этом классе.
    private void setOnMouseClicked( ) {
        setOnMouseClicked( event -> {
            if ( game.getCellSize( ) == 0) {
                return;
            }

            double xx = event.getX( ) - GameBorderImage.getPaddingSide( );
            double yy = event.getY( ) - GameBorderImage.getPaddingTop( );

            int x = ( int)Math.floor( xx / game.getCellSize( ));
            if ( x < 0 || x >= gameController.baseModel.getWidth( )) {
                return;
            }

            int y = ( int)Math.floor( yy / game.getCellSize( ));
            if ( y < 0 || y >= gameController.baseModel.getHeight( )) {
                return;
            }

            switch ( event.getButton( )) {
                case PRIMARY -> gameController.onMouseLeftClick( x, y);
                case SECONDARY -> gameController.onMouseRightClick( x, y);
            }
        });
    }

    // setOnKeyReleased в классе Scene объявлен final, поэтому не получилось его перегрузить в этом классе.
    private void setOnKeyReleased( ) {
        setOnKeyReleased( event -> gameController.onKeyReleased( event.getCode( )));
    }

    // setOnKeyPressed в классе Scene объявлен final, поэтому не получилось его перегрузить в этом классе.
    private void setOnKeyPressed( ) {
        setOnKeyPressed( event -> gameController.onKeyPress( event.getCode( )));
    }
}
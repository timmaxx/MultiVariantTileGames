package timmax.tilegameenginejfx;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class GameScene extends Scene {
    private final GameController gameController;
    private final Game game;


    public GameScene( Parent root, Game game, GameController gameController) {
        super( root);
        this.game = game;
        this.gameController = gameController;
        setOnMouseClicked( );
        setOnKeyPressed( );
        setOnKeyReleased( );
    }

    private void setOnMouseClicked( ) {
        setOnMouseClicked( event -> {
            if ( game.getCellSize( ) == 0) {
                return;
            }

            double xx = event.getX( );
            double yy = event.getY( );
            if ( game.getShowTV( )) {
                xx -= Game.PADDING_SIDE;
                yy -= Game.PADDING_TOP;
            }

            int x = ( int)Math.floor( xx / game.getCellSize( ));
            if ( x < 0 || x >= game.getWidth( )) {
                return;
            }

            int y = ( int)Math.floor( yy / game.getCellSize( ));
            if ( y < 0 || y >= game.getHeight( )) {
                return;
            }

            switch ( event.getButton( )) {
                case PRIMARY -> gameController.onMouseLeftClick( x, y);
                case SECONDARY -> gameController.onMouseRightClick( x, y);
            }
        });
    }

    private void setOnKeyReleased( ) {
        setOnKeyReleased( event -> gameController.onKeyReleased( event.getCode( )));
    }

    private void setOnKeyPressed( ) {
        /*
        setOnKeyPressed( event -> {
            gameController.onKeyPress( event.getCode( ));
        });
        }
    */
        setOnKeyPressed( event -> gameController.onKeyPress( event.getCode( )));
    }
}
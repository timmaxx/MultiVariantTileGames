package timmax.tilegameenginejfx;

import javafx.scene.Parent;
import javafx.scene.Scene;

import static javafx.scene.input.KeyCode.SPACE;

public class GameScene extends Scene {
    private final GameScreenController gameScreenController;
    private final Game game;


    public GameScene( Parent root, Game game, GameScreenController gameScreenController) {
        super( root);
        this.game = game;
        this.gameScreenController = gameScreenController;
        setOnMouseClicked( );
        setOnKeyPressed( );
        setOnKeyReleased( );
    }

    private void setOnMouseClicked( ) {
        setOnMouseClicked( event -> {
            if ( game.isMessageShown( )) {
                game.setMessageShown( false);
                game.setDialogContainerVisible( false);
            }

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
                case PRIMARY -> gameScreenController.onMouseLeftClick( x, y);
                case SECONDARY -> gameScreenController.onMouseRightClick( x, y);
            }
        });
    }

    private void setOnKeyReleased( ) {
        setOnKeyReleased( event -> {
            if ( !game.isMessageShown( )) {
                gameScreenController.onKeyReleased( event.getCode( ));
            }
        });
    }

    private void setOnKeyPressed( ) {
        setOnKeyPressed( event -> {
            if ( game.isMessageShown( ) && event.getCode( ) == SPACE) {
                game.setMessageShown( false);
                game.setDialogContainerVisible( false);
            }
            gameScreenController.onKeyPress( event.getCode( ));
        });
    }
}
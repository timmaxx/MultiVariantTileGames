package timmax.tilegameenginejfx;

import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
import timmax.basetilemodel.BaseModel;

public abstract class Game extends Application implements GameScreen {
    public final static int APP_WIDTH = 800; // 1600;
    public final static int APP_HEIGHT = 600; // 1200

    private int cellSize;

    private BaseModel baseModel;
    private GameBorderImage gameBorderImage;
    // private ViewGameOverMessage viewGameOverMessage;


    @Override
    public void start( Stage primaryStage) {
        baseModel = initModel( );
        GameController gameController = initGameController( baseModel, this);

        Pane root = new Pane( );
        GameScene scene = new GameScene(root, this, gameController);

        primaryStage.setTitle( initTitle( ));
        primaryStage.setResizable( false);
        if ( GameBorderImage.showTV( )) {
            if ( !primaryStage.isShowing( )) {
                primaryStage.initStyle( StageStyle.TRANSPARENT);
            }
            scene.setFill( Color.TRANSPARENT);
        }
        primaryStage.setScene( scene);

        gameBorderImage = new GameBorderImage(root);

        // viewGameOverMessage = new ViewGameOverMessage( baseModel);

        ViewJfx viewMainFieldJfx = initViewMainField(baseModel);
        viewMainFieldJfx.setRoot(root);
        viewMainFieldJfx.setPrimaryStage( primaryStage);

        initialize( );
    }

    @Override
    public void initialize( ) {
        baseModel.createNewGame( );

        cellSize = Math.min( APP_WIDTH / baseModel.getWidth( ), APP_HEIGHT / baseModel.getHeight( ));
        gameBorderImage.setWidthAndHeight( baseModel.getWidth( ), baseModel.getHeight( ), cellSize);

        // viewGameOverMessage.initRootFromModel( root);
    }

    public int getCellSize( ) {
        return cellSize;
    }
}
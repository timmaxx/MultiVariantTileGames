package timmax.tilegameenginejfx;

import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
import timmax.basetilemodel.BaseModel;

public abstract class Game extends Application implements GameScreen {
    public final static int APP_WIDTH = 800; // 1600;
    public final static int APP_HEIGHT = 600; // 1200
    private final static boolean showTV = true; // false; // true;

    private int cellSize;
    private Pane root;
    private Stage primaryStage;

    private BaseModel baseModel;
    private GameBorderImage gameBorderImage;
    private ViewMainAreaJfx viewMainAreaJfx;
    private ViewGameOverMessage viewGameOverMessage;


    @Override
    public void start( Stage primaryStage) {
        this.primaryStage = primaryStage;

        baseModel = initModel( );
        GameController gameController = initGameController( baseModel, this);

        root = new Pane( );
        GameScene scene = new GameScene( root, this, gameController);

        primaryStage.setTitle( initTitle( ));
        primaryStage.setResizable( false);
        if ( showTV) {
            if ( !primaryStage.isShowing( )) {
                primaryStage.initStyle( StageStyle.TRANSPARENT);
            }
            scene.setFill( Color.TRANSPARENT);
        }
        primaryStage.setScene( scene);

        gameBorderImage = new GameBorderImage( root);

        viewGameOverMessage = new ViewGameOverMessage( baseModel, root);
        viewMainAreaJfx = initViewMainArea( baseModel);
        initialize( );
    }

    @Override
    public void initialize( ) {
        baseModel.createNewGame( );

        cellSize = Math.min( APP_WIDTH / baseModel.getWidth( ), APP_HEIGHT / baseModel.getHeight( ));

        primaryStage.hide( );
        root.setPrefSize( 1, 1);
        root.setPrefSize(
                baseModel.getWidth( ) * cellSize + GameBorderImage.getPaddingSide( ) + GameBorderImage.getPaddingSide( ),
                baseModel.getHeight( ) * cellSize + GameBorderImage.getPaddingTop( ) + GameBorderImage.getPaddingDown( )
        );
        primaryStage.show( );
        gameBorderImage.setWidthAndHeight( baseModel.getWidth( ), baseModel.getHeight( ), cellSize);

        viewMainAreaJfx.initRootFromModel( root);
        viewGameOverMessage.initRootFromModel( root);

        baseModel.notifyViews( );
    }

    public int getCellSize( ) {
        return cellSize;
    }

    public int getWidth( ) {
        return baseModel.getWidth( );
    }

    public int getHeight( ) {
        return baseModel.getHeight( );
    }

    public static boolean showTV( ) {
        return showTV;
    }
}
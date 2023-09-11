package timmax.tilegameenginejfx;

import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
import timmax.basetilemodel.BaseModel;

public abstract class Game extends Application implements GameScreen {
    public final static int APP_WIDTH = 800; // 1600;
    public final static int APP_HEIGHT = 600; // 1200
    private final static int MIN_WIDTH = 3;
    private final static int MIN_HEIGHT = 3;
    private final static int MAX_WIDTH = 100;
    private final static int MAX_HEIGHT = 100;
    private final static boolean showTV = true; // false; // true;

    private int width;
    private int height;
    private int cellSize;
    private Pane root;
    private Stage primaryStage;

    private BaseModel baseModel;
    private GameBorderImage gameBorderImage;
    private ViewMainAreaJfx viewMainAreaJfx;


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

        // new ViewGameOverMessage( baseModel, root);

        initialize( );
    }

    @Override
    public void initialize( ) {
        baseModel.createNewGame( );

        setScreenSize( baseModel.getWidth( ), baseModel.getHeight( ));

        // initViewMainArea( baseModel, root);
        viewMainAreaJfx = initViewMainArea( baseModel);
        viewMainAreaJfx.initRootFromModel( root);
        new ViewGameOverMessage( baseModel, root);

        baseModel.notifyViews( );
    }

    @Override
    public void setScreenSize( int width, int height) {
        if ( width < MIN_WIDTH || width > MAX_WIDTH || height < MIN_HEIGHT || height > MAX_HEIGHT) {
            throw new RuntimeException(
                    "Width must be more " + MIN_WIDTH + " and less " + MAX_WIDTH +
                            " and height must be more " + MIN_HEIGHT + " and less " + MAX_HEIGHT + "! " +
                            "But width = " + width + ", height = " + height + ".");
        }
        this.width = width;
        this.height = height;

        cellSize = Math.min( APP_WIDTH / width, APP_HEIGHT / height);

        primaryStage.hide( );
        root.setPrefSize(
                width * cellSize + GameBorderImage.getPaddingSide( ) + GameBorderImage.getPaddingSide( ),
                height * cellSize + GameBorderImage.getPaddingTop( ) + GameBorderImage.getPaddingDown( )
        );
        primaryStage.show( );
        gameBorderImage.setWidthAndHeight( width, height, cellSize);
    }

    public int getCellSize( ) {
        return cellSize;
    }

    public int getWidth( ) {
        return width;
    }

    public int getHeight( ) {
        return height;
    }

    public static boolean showTV( ) {
        return showTV;
    }
}
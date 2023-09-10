package timmax.tilegameenginejfx;

import javafx.application.Application;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
import timmax.basetilemodel.BaseModel;

import java.io.InputStream;

public abstract class Game extends Application implements GameScreen {
    public final static int APP_WIDTH = 800; // 1600;
    public final static int APP_HEIGHT = 600; // 1200

    // These constants (PADDING_X) for screen.png:
    public final static int PADDING_TOP = 110;
    public final static int PADDING_DOWN = 140;
    public final static int PADDING_SIDE = 125;

    private final static int MIN_WIDTH = 3;
    private final static int MIN_HEIGHT = 3;
    private final static int MAX_WIDTH = 100;
    private final static int MAX_HEIGHT = 100;

    private final boolean showTV = true;

    private int width;
    private int height;
    private int cellSize;
    private Pane root;
    private Stage primaryStage;
    // private final boolean showGrid = true;
    // private final boolean showCoordinates = false;

    private BaseModel baseModel;
    private GameController gameController;


    @Override
    public void start( Stage primaryStage) {
        this.primaryStage = primaryStage;

        baseModel = initModel( );
        gameController = initGameController( baseModel, this);

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

        initialize( );
    }

    @Override
    public void initialize( ) {
        baseModel.createNewGame( );
        setScreenSize( baseModel.getWidth( ), baseModel.getHeight( ));
        initViewMainArea( baseModel, root);
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

        root.setPrefSize(
                width * cellSize + PADDING_SIDE + PADDING_SIDE,
                height * cellSize + PADDING_TOP + PADDING_DOWN
        );
//  \
        createBorderImage( );
//  /

        primaryStage.show( );
    }

    private void createBorderImage( ) {
        InputStream inputStream = Game.class.getResourceAsStream( "/screen.png");
        assert inputStream != null;
        Image image = new Image( inputStream);
        ImageView imageView = new ImageView( image);
        imageView.setFitWidth( width * cellSize + PADDING_SIDE + PADDING_SIDE);
        imageView.setFitHeight( height * cellSize + PADDING_TOP + PADDING_DOWN);
        root.getChildren( ).add( imageView);
    }

    public int getCellSize( ) {
        return cellSize;
    }

    public boolean getShowTV( ) {
        return showTV;
    }

    public int getWidth( ) {
        return width;
    }

    public int getHeight( ) {
        return height;
    }
}
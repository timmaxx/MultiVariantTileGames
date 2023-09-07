package timmax.tilegameenginejfx;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.*;
import timmax.basetilemodel.BaseModel;

import java.io.InputStream;

public abstract class Game extends Application implements GameScreen, GameStackPaneSetCell {
    private final static int APP_WIDTH = 800; // 1600;
    private final static int APP_HEIGHT = 600; // 1200

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
    private GameStackPane[ ][ ] cells;
    private Pane root;
    private Stage primaryStage;
    private final boolean showGrid = true;
    private final boolean showCoordinates = false;
    private boolean isMessageShown = false;
    private TextFlow dialogContainer;
    private BaseModel baseModel;
    private GameScreenController gameScreenController;


    @Override
    public void start( Stage primaryStage) {
        this.primaryStage = primaryStage;
        baseModel = initModel( );
        initialize( );
    }

    @Override
    public void initialize( ) {
        baseModel.createNewGame( );
        gameScreenController = initGameScreenController( baseModel, this);
        initViewMainArea( baseModel, this);
        new ViewGameOverMessage( baseModel, this);
        baseModel.notifyViews( );
    }

    protected BaseModel getModel( ) {
        if ( baseModel == null) {
            baseModel = initModel( );
        }
        return baseModel;
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
        cells = new GameStackPane[ height][ width];

        root = new Pane( );
        root.setPrefSize(
                width * cellSize + PADDING_SIDE + PADDING_SIDE,
                height * cellSize + PADDING_TOP + PADDING_DOWN
        );
        createBorderImage( );

        for( int y = 0; y < height; ++y) {
            for( int x = 0; x < width; ++x) {
                cells[ y][ x] = new GameStackPane( x, y, cellSize, showGrid, showCoordinates, PADDING_SIDE, PADDING_TOP);
                root.getChildren( ).add( cells[ y][ x]);
            }
        }

        GameScene scene = new GameScene( root, this, gameScreenController);

        primaryStage.setTitle( initTitle( ));
        primaryStage.setResizable( false);
        if ( showTV) {
            if ( !primaryStage.isShowing( )) {
                primaryStage.initStyle( StageStyle.TRANSPARENT);
            }
            scene.setFill( Color.TRANSPARENT);
        }

        primaryStage.setScene( scene);
        primaryStage.show( );

        dialogContainer = new TextFlow( );
        root.getChildren( ).add( dialogContainer);
    }

    @Override
    public void setCellColor( int x, int y, Color cellColor) {
        cells[ y][ x].setCellColor( cellColor);
    }

    @Override
    public void setCellValue( int x, int y, String textValue) {
        cells[ y][ x].setCellValue( textValue, cellSize);
    }

    @Override
    public void setCellNumber( int x, int y, int numberValue) {
        cells[ y][ x].setCellValue( String.valueOf( numberValue), cellSize);
    }

    @Override
    public void setCellTextColor( int x, int y, Color textColor) {
        cells[ y][ x].setCellTextColor( textColor);
    }

    @Override
    public void setCellValueEx( int x, int y, Color cellColor, String textValue) {
        cells[ y][ x].setCellValueEx( cellColor, textValue, cellSize);
    }

    @Override
    public void showMessageDialog( Color cellColor, String message, Color textColor, int textSize) {
        dialogContainer.getChildren( ).clear( );
        Text messageText = new Text( );
        messageText.setFont( Font.font( "Verdana", FontWeight.BOLD, textSize));
        messageText.setText( message);
        double preferredWidth = messageText.getLayoutBounds( ).getWidth( );
        messageText.setFill( textColor);
        dialogContainer.setLayoutX( ( root.getWidth( ) - preferredWidth) / 2.);
        dialogContainer.setLayoutY( root.getHeight( ) / 2. - 30);
        dialogContainer.setBackground( new Background( new BackgroundFill( cellColor, CornerRadii.EMPTY, Insets.EMPTY)));
        dialogContainer.setVisible( true);
        dialogContainer.getChildren( ).add( messageText);
        isMessageShown = true;
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

    public boolean isMessageShown( ) {
        return isMessageShown;
    }

    public void setMessageShown( boolean isMessageShown) {
        this.isMessageShown = isMessageShown;
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

    public void setDialogContainerVisible( boolean isVisible) {
        dialogContainer.setVisible( isVisible);
    }
}
package timmax.tilegameenginejfx;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.*;
import timmax.basetilemodel.BaseModel;

import java.io.InputStream;

import static javafx.scene.input.KeyCode.SPACE;

public abstract class Game extends Application implements GameScreen {
    private final static int APP_WIDTH = 800; // 1600;
    private final static int APP_HEIGHT = 600; // 1200

    // These constants (PADDING_X) for screen.png:
    private final static int PADDING_TOP = 110;
    private final static int PADDING_DOWN = 140;
    private final static int PADDING_SIDE = 125;

    private final static int MIN_WIDTH = 3;
    private final static int MIN_HEIGHT = 3;
    private final static int MAX_WIDTH = 100;
    private final static int MAX_HEIGHT = 100;

    private final boolean showTV = true;

    private int width;
    private int height;
    private int cellSize;
    private StackPane[ ][ ] cells;
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
        // log.debug( "initialize");

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
        cells = new StackPane[ height][ width];

        for( int y = 0; y < height; ++y) {
            for( int x = 0; x < width; ++x) {
                cells[ y][ x] = new StackPane( new Rectangle( ), new Text( ), new Text( ));
            }
        }

        reCreateContent();
    }

    @Override
    public void setCellColor( int x, int y, Color cellColor) {
        if ( cellColor != null && cellColor != Color.TRANSPARENT) {
            ObservableList< Node> children = cells[ y][ x].getChildren( );
            if ( !cellColor.equals( ( ( Rectangle)children.get( 0)).getFill( ))
            ) {
                ( ( Rectangle)children.get( 0)).setFill( cellColor);
            }
        }
    }

    @Override
    public void setCellValue( int x, int y, String textValue) {
        // Повторяющийся блок кода в setCellValue и в setCellTextColor
        ObservableList< Node> children = cells[ y][ x].getChildren( );
        Text text = ( Text)children.get( 1);
        // Конец блока
        if ( text.getText( ).equals( textValue)) {
            return;
        }

        if ( textValue.length( ) < 5) {
            double fontSize = cellSize * 0.4;
            text.setFont( Font.font( fontSize));
        } else {
            int fontSize = cellSize / textValue.length( );
            text.setFont( Font.font( fontSize));
        }

        text.setText( textValue);
    }

    @Override
    public void setCellNumber( int x, int y, int numberValue) {
        setCellValue( x, y, String.valueOf( numberValue));
    }

    @Override
    public void setCellTextColor( int x, int y, Color textColor) {
        // Повторяющийся блок кода в setCellValue и в setCellTextColor
        ObservableList< Node> children = cells[ y][ x].getChildren( );
        Text text = ( Text)children.get( 1);
        // Конец блока
        if ( !textColor.equals( text.getFill( ))) {
            text.setFill( textColor);
        }
    }

    @Override
    public void setCellValueEx( int x, int y, Color cellColor, String textValue) {
        setCellValue( x, y, textValue);
        setCellColor( x, y, cellColor);
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

    private void reCreateContent( ) {
        Scene scene = new Scene( createContent( ));
        setOnMouseClicked( scene);
        setOnKeyReleased( scene);
        setOnKeyPressed( scene);
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

    private Parent createContent( ) {
        root = new Pane( );
        root.setPrefSize(
                width * cellSize + PADDING_SIDE + PADDING_SIDE,
                height * cellSize + PADDING_TOP + PADDING_DOWN);
        createBorderImage( );

        for( int y = 0; y < height; ++y) {
            for( int x = 0; x < width; ++x) {
                ObservableList< Node> children = cells[ y][ x].getChildren( );
                Rectangle cell;
                if ( showGrid) {
                    cell = ( Rectangle)children.get( 0);
                    cell.setWidth( cellSize - 1);
                    cell.setHeight( cellSize - 1);
                    cell.setStroke( Color.BLACK);
                }

                if ( showCoordinates) {
                    Text coordinate = ( Text)children.get( 2);
                    coordinate.setFont( Font.font( cellSize * 0.15));
                    StackPane.setAlignment( coordinate, Pos.TOP_LEFT);
                    coordinate.setText( x + " - " + y);
                }

                {
                    cell = ( Rectangle)children.get( 0);
                    cell.setWidth( cellSize);
                    cell.setHeight( cellSize);
                    cells[ y][ x].setLayoutX( x * cellSize + PADDING_SIDE);
                    cells[ y][ x].setLayoutY( y * cellSize + PADDING_TOP);
                    root.getChildren( ).add( cells[ y][ x]);
                }
            }
        }

        return root;
    }

    private void setOnMouseClicked( Scene scene) {
        scene.setOnMouseClicked( event -> {
            if ( isMessageShown) {
                isMessageShown = false;
                dialogContainer.setVisible( false);
            }

            if ( cellSize == 0) {
                return;
            }

            double xx = event.getX( );
            double yy = event.getY( );
            if ( showTV) {
                xx -= PADDING_SIDE;
                yy -= PADDING_TOP;
            }
            int x = ( int)Math.floor( xx / cellSize);
            if ( x < 0 || x >= width) {
                return;
            }
            int y = ( int)Math.floor( yy / cellSize);
            if ( y < 0 || y >= height) {
                return;
            }
            switch ( event.getButton( )) {
                case PRIMARY -> gameScreenController.onMouseLeftClick( x, y);
                case SECONDARY -> gameScreenController.onMouseRightClick( x, y);
            }
        });
    }

    private void setOnKeyReleased( Scene scene) {
        scene.setOnKeyReleased( event -> {
            if ( !isMessageShown) {
                gameScreenController.onKeyReleased( event.getCode( ));
            }
        });
    }

    private void setOnKeyPressed( Scene scene) {
        scene.setOnKeyPressed( event -> {
            if ( isMessageShown && event.getCode( ) == SPACE) {
                isMessageShown = false;
                dialogContainer.setVisible( false);
            }
            gameScreenController.onKeyPress( event.getCode( ));
        });
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
}
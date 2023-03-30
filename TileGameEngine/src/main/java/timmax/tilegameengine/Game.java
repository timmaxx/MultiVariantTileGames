package timmax.tilegameengine;

import javafx.animation.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.util.Duration;

import java.io.InputStream;
import java.util.Random;

import static javafx.scene.input.KeyCode.SPACE;

public abstract class Game extends Application implements GameScreen {

    private final static Random random = new Random( );
    private int width;
    private int height;
    private int cellSize;
    // private Timeline timeline = new Timeline( );
    // private int timerStep = 0;
    private StackPane[ ][ ] cells;
    private Pane root;
    private Stage primaryStage;
    private boolean showGrid = true;
    private boolean showCoordinates = false;
    private final boolean showTV = true;
    private boolean isMessageShown = false;
    private final static int APP_WIDTH = 800; // 1600;
    private final static int APP_HEIGHT = 600; // 1200

    // These constants (PADDING_X) for screen.png:
    private final static int PADDING_TOP = 110;
    private final static int PADDING_DOWN = 140;
    private final static int PADDING_SIDE = 125;

    private final static int MAX_WIDTH = 100;
    private final static int MAX_HEIGHT = 100;
    // private Text scoreText;
    // private Text livesText;
    private TextFlow dialogContainer;

    public StackPane[ ][ ] getCells() {
        return cells;
    }

    @Override
    public void start( Stage primaryStage) {
        this.primaryStage = primaryStage;
        //scoreText = new Text( "Score: 0");
        initialize( );
    }

    @Override
    public void setScreenSize( int width, int height) {
        // this.width = width < 3 ? 3 : (Math.min(width, MAX_WIDTH)); this.height = height < 3 ? 3 : (Math.min(height, MAX_HEIGHT));

        if (width < 3 || width > MAX_WIDTH || height < 3 || height > MAX_HEIGHT) {
            throw new RuntimeException(
                    "Width must be more 2 and less " + MAX_WIDTH + " and height must be more 2 and less " + MAX_HEIGHT + "! " +
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


    private void reCreateContent( ) {
        Scene scene = new Scene( createContent( ));
        setOnMouseClicked( scene);
        setOnKeyReleased( scene);
        setOnKeyPressed( scene);
        primaryStage.setTitle( "JavaRush Game");
        primaryStage.setResizable( false);
        if ( showTV) {
            if ( !primaryStage.isShowing( )) {
                primaryStage.initStyle( StageStyle.TRANSPARENT);
            }
            scene.setFill( Color.TRANSPARENT);
        }

        primaryStage.setScene( scene);
        primaryStage.show( );
        // timeline.playFromStart( );

        dialogContainer = new TextFlow( );
        root.getChildren( ).add( dialogContainer);
/*
        livesText = new Text( );
        livesText.setFont( Font.font(20));
        livesText.setFill( Color.ORANGE);
        livesText.setY( 160);
        livesText.setX( 127);
        root.getChildren( ).addAll( livesText);
        */
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
                if ( showGrid && children.size( ) > 0) {
                    cell = ( Rectangle)children.get( 0);
                    cell.setWidth( cellSize - 1);
                    cell.setHeight( cellSize - 1);
                    cell.setStroke( Color.BLACK);
                }

                if ( showCoordinates && children.size( ) > 2) {
                    Text coordinate = ( Text)children.get( 2);
                    coordinate.setFont( Font.font( cellSize * 0.15));
                    StackPane.setAlignment( coordinate, Pos.TOP_LEFT);
                    coordinate.setText( x + " - " + y);
                }

                if ( children.size( ) > 0) {
                    cell = ( Rectangle)children.get( 0);
                    cell.setWidth( cellSize);
                    cell.setHeight( cellSize);
                    cells[ y][ x].setLayoutX( x * cellSize + PADDING_SIDE);
                    cells[ y][ x].setLayoutY( y * cellSize + PADDING_TOP);
                    root.getChildren( ).add( cells[ y][ x]);
                }
            }
        }
/*
        scoreText = new Text( "Score: 0");
        createScorePanel( );

        timeline.setCycleCount( -1);
        */
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
                case PRIMARY -> onMouseLeftClick( x, y);
                case SECONDARY -> onMouseRightClick( x, y);
            }
        });
    }

    private void setOnKeyReleased( Scene scene) {
        scene.setOnKeyReleased( event -> {
            if ( !isMessageShown) {
                onKeyReleased( event.getCode( ));
            }
        });
    }

    private void setOnKeyPressed( Scene scene) {
        scene.setOnKeyPressed( event -> {
            if ( isMessageShown && event.getCode( ) == SPACE) {
                isMessageShown = false;
                dialogContainer.setVisible( false);
            }
            onKeyPress( event.getCode( ));
        });
    }

    private void createBorderImage( ) {
        InputStream inputStream = Game.class.getResourceAsStream( "/screen.png");
        Image image = new Image( inputStream);
        ImageView imageView = new ImageView( image);
        imageView.setFitWidth( width * cellSize + PADDING_SIDE + PADDING_SIDE);
        imageView.setFitHeight( height * cellSize + PADDING_TOP + PADDING_DOWN);
        root.getChildren( ).add( imageView);
    }
/*
    private void createScorePanel( ) {
        scoreText.setFont( Font.font("Verdana", FontWeight.BOLD, 16));
        scoreText.setFill( Color.BLACK);
        StackPane scorePane = new StackPane(scoreText);
        scorePane.setBorder( new Border( new BorderStroke(
                Color.BLACK,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));
        scorePane.setLayoutY( height * cellSize + PADDING_TOP + 6);
        int scoreHeight = 20;
        Rectangle rectangle;
        if ( showGrid) {
            rectangle = new Rectangle( ( ( width * cellSize - 1) / 2.), scoreHeight, Color.WHITE);
            scorePane.setLayoutX( PADDING_SIDE + (width * cellSize - 1) / 4.);
        } else {
            rectangle = new Rectangle( ( width * cellSize / 2.), scoreHeight, Color.WHITE);
            scorePane.setLayoutX( PADDING_SIDE - 1 + width * cellSize / 4.);
        }

        scorePane.getChildren( ).add( 0, rectangle);
        root.getChildren( ).add( scorePane);
    }
*/
    @Override
    public void setCellColor( int x, int y, Color color) {
        if ( color != null && color != Color.TRANSPARENT) {
            ObservableList< Node> children = cells[ y][ x].getChildren( );
            if (        children.size( ) > 0
                    &&  !color.equals( ( ( Rectangle)children.get( 0)).getFill( ))
            ) {
                ( ( Rectangle)children.get( 0)).setFill( color);
            }
        }
    }

    @Override
    public void showGrid( boolean isShow) {
        showGrid = isShow;
    }

    @Override
    public void showCoordinates( boolean isShow) {
        showCoordinates = isShow;
    }

    @Override
    public void setCellValue( int x, int y, String textValue) {
        ObservableList< Node> children = cells[ y][ x].getChildren( );
        if ( children.size( ) < 2) {
            return;
        }
        Text text = ( Text)children.get( 1);
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
    public void setCellTextColor( int x, int y, Color color) {
        ObservableList< Node> children = cells[ y][ x].getChildren( );
        if ( children.size( ) < 2) {
            return;
        }
        Text text = ( Text)children.get( 1);
        if ( !color.equals( text.getFill( ))) {
            text.setFill( color);
        }
    }
/*
    @Override
    public void setTurnTimer( int timeMs) {
        timeline.stop( );
        KeyFrame frame = new KeyFrame(
                Duration.millis( timeMs),
                event -> {
                    if ( !isMessageShown) {
                        onTurn( ++timerStep);
                    }
                },
                new KeyValue[ 0]
        );
        timeline.getKeyFrames( ).clear( );
        timeline.getKeyFrames( ).add( frame);
        timeline.play( );
    }
*/
/*
    @Override
    public void stopTurnTimer( ) {
        timeline.stop( );
    }
*/
    @Override
    public int getRandomNumber( int max) {
        return random.nextInt( max);
    }

    @Override
    public int getRandomNumber( int min, int max) {
        return random.nextInt(max - min) + min;
    }

    @Override
    public void onMouseLeftClick( int x, int y) {
    }

    @Override
    public void onMouseRightClick( int x, int y) {
    }

    @Override
    public void onKeyPress( KeyCode keyCode) {
    }

    @Override
    public void onKeyReleased( KeyCode keyCode) {
    }
/*
    @Override
    public void onTurn( int step) {
    }
*/
    @Override
    public void setCellTextSize( int x, int y, int textSize) {
        ObservableList< Node> children = cells[ y][ x].getChildren();
        if ( children.size( ) < 2) {
            return;
        }
        Text text = ( Text)children.get( 1);
        textSize = Math.min( textSize, 100);
        double fontSize = cellSize * (textSize / 100.0);
        if ( !Font.font( fontSize).equals( text.getFont( ))) {
            text.setFont( Font.font( fontSize));
        }
    }

    @Override
    public void setCellValueEx( int x, int y, Color cellColor, String textValue) {
        setCellValue( x, y, textValue);
        setCellColor( x, y, cellColor);
    }

    @Override
    public void setCellValueEx( int x, int y, Color cellColor, String textValue, Color textColor) {
        setCellValueEx( x, y, cellColor, textValue);
        setCellTextColor( x, y, textColor);
    }
/*
    @Override
    public void setCellValueEx( int x, int y, Color cellColor, String value, Color textColor, int textSize) {
        setCellValueEx( x, y, cellColor, value, textColor);
        setCellTextSize( x, y, textSize);
    }
*/
    @Override
    public void showMessageDialog( Color cellColor, String message, Color textColor, int textSize) {
        dialogContainer.getChildren( ).clear( );
        Text messageText = new Text( );
        messageText.setFont( Font.font("Verdana", FontWeight.BOLD, textSize));
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
/*
    @Override
    public void setScore( int score) {
        scoreText.setText( "Score: " + score);
    }
*/
/*
    @Override
    public void setLives( int lives) {
        livesText.setText( "❤: " + lives);
    }
*/
}
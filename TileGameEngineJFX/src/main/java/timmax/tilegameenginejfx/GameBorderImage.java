package timmax.tilegameenginejfx;

import javafx.scene.image.*;
import javafx.scene.layout.Pane;

import java.io.InputStream;

// ToDo: gameBorderImage сделать реализующим ViewJfx.
public class GameBorderImage extends ImageView {
    // These constants (PADDING_X) for screen.png:
    private final static int PADDING_TOP = 110;
    private final static int PADDING_DOWN = 140;
    private final static int PADDING_SIDE = 125;
    private final static boolean showTV = true; // false; // true;

    // ToDo: Странно видеть здесь imageView. А иначе зачем было этот класс делать наследником ImageView?
    private final ImageView imageView;


    protected GameBorderImage( Pane root) {
        if ( !showTV) {
            return;
        }

        InputStream inputStream = Game.class.getResourceAsStream( "/screen.png");
        assert inputStream != null;
        imageView = new ImageView( new Image( inputStream));

        root.getChildren( ).add( imageView);
    }

    public void setWidthAndHeight( int width, int height, int cellSize) {
        if ( !showTV) {
            return;
        }
        imageView.setFitWidth( width * cellSize + PADDING_SIDE + PADDING_SIDE);
        imageView.setFitHeight( height * cellSize + PADDING_TOP + PADDING_DOWN);
    }

    public static int getPaddingSide( ) {
        return showTV ? PADDING_SIDE : 0;
    }

    public static int getPaddingTop( ) {
        return showTV ? PADDING_TOP : 0;
    }

    public static int getPaddingDown( ) {
        return showTV ? PADDING_DOWN : 0;
    }

    public static boolean showTV( ) {
        return showTV;
    }
}
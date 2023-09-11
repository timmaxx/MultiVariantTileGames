package timmax.tilegameenginejfx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.InputStream;

public class GameBorderImage extends ImageView {
    // These constants (PADDING_X) for screen.png:
    private final static int PADDING_TOP = 110;
    private final static int PADDING_DOWN = 140;
    private final static int PADDING_SIDE = 125;

    private ImageView imageView;
    // private Pane root;

    protected GameBorderImage( Pane root) {
        if ( !Game.showTV( )) {
            return;
        }

        // this.root = root;

        InputStream inputStream = Game.class.getResourceAsStream( "/screen.png");
        assert inputStream != null;
        imageView = new ImageView( new Image( inputStream));

        root.getChildren( ).add( imageView);
    }

    public void setWidthAndHeight( int width, int height, int cellSize) {
        if ( !Game.showTV( )) {
            return;
        }
        imageView.setFitWidth( width * cellSize + PADDING_SIDE + PADDING_SIDE);
        imageView.setFitHeight( height * cellSize + PADDING_TOP + PADDING_DOWN);
    }

    public static int getPaddingSide( ) {
        return Game.showTV( ) ? PADDING_SIDE : 0;
    }

    public static int getPaddingTop( ) {
        return Game.showTV( ) ? PADDING_TOP : 0;
    }

    public static int getPaddingDown( ) {
        return Game.showTV( ) ? PADDING_DOWN : 0;
    }
}
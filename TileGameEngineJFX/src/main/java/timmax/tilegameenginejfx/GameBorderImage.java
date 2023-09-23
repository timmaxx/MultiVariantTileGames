package timmax.tilegameenginejfx;

import javafx.scene.image.*;
import timmax.basetilemodel.gameevent.GameEvent;
import timmax.basetilemodel.gameevent.GameEventNewGame;
import java.io.InputStream;
import java.util.NoSuchElementException;

// ToDo: gameBorderImage сделать реализующим ViewJfx.
public class GameBorderImage extends ImageView implements ViewJfxInterface {
    // These constants (PADDING_X) for screen.png:
    private final static int PADDING_TOP = 110;
    private final static int PADDING_DOWN = 140;
    private final static int PADDING_SIDE = 125;
    private final static boolean showTV = false; // true;


    // ViewJfxInterface viewJfx; // ToDo: Должно так работать.
    ViewJfx viewJfx;

    protected GameBorderImage( /*BaseModel baseModel*/) {
        super( );
        if ( !showTV) {
            return;
        }
        // viewJfx = new ViewJfx( baseModel);
        InputStream inputStream = Game.class.getResourceAsStream( "/screen.png");
        assert inputStream != null;
        this.setImage( new Image( inputStream));

        // root.getChildren( ).add( imageView); // Это было здесь, т.к. root был параметром конструктора.
        // Но вероятно это и не понадобится, т.к. JFX сам это сделает,
        // когда будет привязывать GameBorderImage к какому-нибудь Pane (например VBox).
    }
/*
    protected GameBorderImage( Parent root) {
        if ( !showTV) {
            return;
        }

        InputStream inputStream = Game.class.getResourceAsStream( "/screen.png");
        assert inputStream != null;
        imageView = new ImageView( new Image( inputStream));

        root.getChildren( ).add( imageView);
    }
*/

    public void setWidthAndHeight( int width, int height, int cellSize) {
        if ( !showTV) {
            return;
        }
/*
        imageView.setFitWidth( width * cellSize + PADDING_SIDE + PADDING_SIDE);
        imageView.setFitHeight( height * cellSize + PADDING_TOP + PADDING_DOWN);
*/
        setFitWidth( width * cellSize + PADDING_SIDE + PADDING_SIDE);
        setFitHeight( height * cellSize + PADDING_TOP + PADDING_DOWN);
    }

    public static int getPaddingSide( ) {
        return showTV ? PADDING_SIDE : 0;
    }

    public static int getPaddingTop( ) {
        return showTV ? PADDING_TOP : 0;
    }
/*
    public static int getPaddingDown( ) {
        return showTV ? PADDING_DOWN : 0;
    }

    public static boolean showTV( ) {
        return showTV;
    }
*/
    @Override
    public void update( ) {
        GameEvent gameEvent;
        while ( true) {
            try {
                // Есть проблема: Считывать очередное событие о начале новой игры
                // (в нём есть информация о высоте и ширине в клетках главного поля игры) нужно по крайней мере после аналогичных событий,
                // которые влияют на размеры выборок.
                // С другой стороны, похоже, это всё-же не совсем "игровая выборка" - т.е. зависящая от модели.
                // Эта выборка зависит не от модели, а от размеров и компоновки игровых выборок,
                // и уже совокупная их ширина и высота должны стать внутренней шириной и высотой BorderImage.
                // 22.09.2023: Пока приостанавливаю разработку этого класса.
                gameEvent = viewJfx.removeFromGameQueueForOneView( );
            } catch ( NoSuchElementException nsee) {
                break;
            }

            if ( !showTV) {
                return;
            }

            if ( gameEvent instanceof GameEventNewGame gameEventNewGame) {
                // ToDo: 53 нужно доставать из root, который привязан к Scene.
                setWidthAndHeight( gameEventNewGame.getWidth( ), gameEventNewGame.getHeight( ), 53);
            }
        }
    }

    @Override
    public GameEvent removeFromGameQueueForOneView( ) {
        return viewJfx.removeFromGameQueueForOneView( );
    }
}
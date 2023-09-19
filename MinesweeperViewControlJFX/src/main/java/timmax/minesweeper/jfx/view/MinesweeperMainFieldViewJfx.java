package timmax.minesweeper.jfx.view;

import javafx.scene.paint.Color;
import timmax.basetilemodel.*;
import timmax.basetilemodel.gameevent.*;
import timmax.minesweeper.model.gameevent.*;
import timmax.tilegameenginejfx.*;
import java.util.NoSuchElementException;

import static javafx.scene.paint.Color.*;

public class MinesweeperMainFieldViewJfx extends ViewJfx {
    private static final Color UNOPENED_CELL_COLOR = ORANGE;
    private static final Color OPENED_CELL_COLOR = GREEN;

    private static final String FLAG = "🚩"; // "\uD83D\uDEA9";
    private static final Color FLAG_CELL_COLOR = YELLOW;

    private static final String MINE = "💣"; // "\uD83D\uDCA3";
    private static final Color MINE_CELL_COLOR = RED;

    private final boolean showGrid = true;
    private final boolean showCoordinates = false;


    public MinesweeperMainFieldViewJfx( BaseModel baseModel) {
        super( baseModel);
    }

    // Почти идентичное содержимое этого метода с одноименным методом в SokobanMainFieldViewJfx.
    // ToDo:
    //  1. Перенести в родительский класс.
    //  2. И там через абстракцию разрулить неодинаковость.
    @Override
    public void update( ) {
        GameEvent gameEvent;
        while ( true) {
            try {
                // Единственное место, где обращение к модели можно оставить.
                // Или даже не к модели, а к очереди, которая внутри модели, привязана к этому представлению.
                gameEvent = baseModel.getNextGameEventForView( this);
            } catch ( NoSuchElementException nsee) {
                break;
            }

            if ( gameEvent instanceof GameEventNewGame) {
                initMainField( ( GameEventNewGame) gameEvent);
            } else if ( gameEvent instanceof GameEventOneTile) {
                drawTile( ( ( GameEventOneTile) gameEvent));
            }
        }
    }

    private void initMainField( GameEventNewGame gameEventNewGame) {
        // ToDo: Свойства модели нужно брать не напрямую из модели, а из события!
        int width = gameEventNewGame.getWidth( );
        int height = gameEventNewGame.getHeight( );
        cellSize = Math.min( Game.APP_WIDTH / width, Game.APP_HEIGHT / height);

        primaryStage.hide( );
        root.setPrefSize(
                width * cellSize + GameBorderImage.getPaddingSide( ) + GameBorderImage.getPaddingSide( ),
                height * cellSize + GameBorderImage.getPaddingTop( ) + GameBorderImage.getPaddingDown( )
        );

        cells = new GameStackPane[ height][ width];
        for( int y = 0; y < height; ++y) {
            for( int x = 0; x < width; ++x) {
                cells[ y][ x] = new GameStackPane( x, y, cellSize, showGrid, showCoordinates);
                {   // Наличием этого блока этот метод и отличается от одноименной функции в SokobanMainFieldViewJfx
                    // ToDo:
                    //  1. Сделать initMainField в родительском классе с вызовом абстрактного метода (п. 2).
                    //  2. Сделать абстрактный метод там для возможной реализации подобного блока.
                    //  3. В этом классе только перегрузить абстрактный метод.
                    cells[ y][ x].setCellValue( "", cellSize);
                    cells[ y][ x].setCellColor( UNOPENED_CELL_COLOR);
                }
                root.getChildren( ).add( cells[ y][ x]);
            }
        }
        primaryStage.show( );
    }

    // ToDo: drawTile в родительский класс и пусть там будет абстрактной. А здесь будет реализация.
    private void drawTile( GameEventOneTile gameEventOneTile) {
        int x = gameEventOneTile.getX( );
        int y = gameEventOneTile.getY( );
        GameStackPane cell = cells[ y][ x];

        if ( gameEventOneTile instanceof GameEventOneTileOpenMine) {
            cell.setCellValueEx( MINE_CELL_COLOR, MINE, cellSize);
        } else if ( gameEventOneTile instanceof GameEventOneTileOpenNoMine) {
            int countOfMineNeighbors = ( (GameEventOneTileOpenNoMine) gameEventOneTile).getCountOfMineNeighbors( );
            cell.setCellNumber( countOfMineNeighbors, cellSize);
            cell.setCellColor( OPENED_CELL_COLOR);
        } else if ( gameEventOneTile instanceof GameEventOneTileChangeFlag) {
            String flag;
            Color tile_cell_color;
            if ( ( ( GameEventOneTileChangeFlag) gameEventOneTile).isFlag( )) {
                flag = FLAG;
                tile_cell_color = FLAG_CELL_COLOR;
            } else {
                flag = "";
                tile_cell_color = UNOPENED_CELL_COLOR;
            }
            cell.setCellValue( flag, cellSize);
            cell.setCellColor( tile_cell_color);
        }
    }
}
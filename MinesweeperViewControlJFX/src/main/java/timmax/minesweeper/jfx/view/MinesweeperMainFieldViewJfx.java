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
                // ToDo: Свойства модели нужно брать не напрямую из модели, а из события!
                cellSize = Math.min( Game.APP_WIDTH / baseModel.getWidth( ), Game.APP_HEIGHT / baseModel.getHeight( ));

                primaryStage.hide( );
                root.setPrefSize(
                        baseModel.getWidth( ) * cellSize + GameBorderImage.getPaddingSide( ) + GameBorderImage.getPaddingSide( ),
                        baseModel.getHeight( ) * cellSize + GameBorderImage.getPaddingTop( ) + GameBorderImage.getPaddingDown( )
                );

                cells = new GameStackPane[ baseModel.getHeight( )][ baseModel.getWidth( )];
                for( int y = 0; y < baseModel.getHeight( ); ++y) {
                    for( int x = 0; x < baseModel.getWidth( ); ++x) {
                        cells[ y][ x] = new GameStackPane( x, y, cellSize, showGrid, showCoordinates);
                        cells[ y][ x].setCellValue( "", cellSize);
                        cells[ y][ x].setCellColor( UNOPENED_CELL_COLOR);
                        root.getChildren( ).add( cells[ y][ x]);
                    }
                }
                primaryStage.show( );
            } else if ( gameEvent instanceof GameEventOneTile) {
                int x = ( ( GameEventOneTile) gameEvent).getX( );
                int y = ( ( GameEventOneTile) gameEvent).getY( );

                if ( gameEvent instanceof GameEventOneTileMine) {
                    cells[ y][ x].setCellValueEx( MINE_CELL_COLOR, MINE, cellSize);
                } else if ( gameEvent instanceof GameEventOneTileNoMine) {
                    int countOfMineNeighbors = ( ( GameEventOneTileNoMine) gameEvent).getCountOfMineNeighbors( );
                    cells[ y][ x].setCellNumber( countOfMineNeighbors, cellSize);
                    cells[ y][ x].setCellColor( OPENED_CELL_COLOR);
                } else if ( gameEvent instanceof GameEventOneTileChangeFlag) {
                    String flag;
                    Color tile_cell_color;
                    if ( ( ( GameEventOneTileChangeFlag) gameEvent).isFlag( )) {
                        flag = FLAG;
                        tile_cell_color = FLAG_CELL_COLOR;
                    } else {
                        flag = "";
                        tile_cell_color = UNOPENED_CELL_COLOR;
                    }
                    cells[ y][ x].setCellValue( flag, cellSize);
                    cells[ y][ x].setCellColor( tile_cell_color);
                }
            }
        }
    }
}
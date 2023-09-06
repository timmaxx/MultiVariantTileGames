package timmax.tilegameenginejfx;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameStackPane extends StackPane {
    private final Rectangle rectangle;
    private final Text generalText;
    private final Text coordinateText;


    public GameStackPane( ) {
        super( new Rectangle( ), new Text( ), new Text( ));
        rectangle = ( ( Rectangle)getChildren( ).get( 0));
        generalText = ( ( Text)getChildren( ).get( 1));
        coordinateText = ( ( Text)getChildren( ).get( 2));
    }

    public void setCellValue( String textValue, int cellSize) {
        if ( generalText.getText( ).equals( textValue)) {
            return;
        }
        if ( textValue.length( ) < 5) {
            double fontSize = cellSize * 0.4;
            generalText.setFont( Font.font( fontSize));
        } else {
            int fontSize = cellSize / textValue.length( );
            generalText.setFont( Font.font( fontSize));
        }
        generalText.setText( textValue);
    }

    public void setCellColor( Color cellColor) {
        if ( cellColor != null && cellColor != Color.TRANSPARENT) {
            if ( !cellColor.equals( rectangle.getFill( ))) {
                rectangle.setFill( cellColor);
            }
        }
    }

    public void setCellTextColor( Color textColor) {
        if ( !textColor.equals( generalText.getFill( ))) {
            generalText.setFill( textColor);
        }
    }

    public void createContent( int x, int y, int cellSize, boolean showGrid, boolean showCoordinates, int PADDING_SIDE, int PADDING_TOP) {
        if ( showGrid) {
            rectangle.setWidth( cellSize - 1);
            rectangle.setHeight( cellSize - 1);
            rectangle.setStroke( Color.BLACK);
        }
        if ( showCoordinates) {
            coordinateText.setFont( Font.font( cellSize * 0.15));
            StackPane.setAlignment( coordinateText, Pos.TOP_LEFT);
            coordinateText.setText( x + " - " + y);
        }
        rectangle.setWidth( cellSize);
        rectangle.setHeight( cellSize);
        setLayoutX( x * cellSize + PADDING_SIDE);
        setLayoutY( y * cellSize + PADDING_TOP);
    }

    public void setCellValueEx( Color cellColor, String textValue, int cellSize) {
        setCellValue( textValue, cellSize);
        setCellColor( cellColor);
    }
}
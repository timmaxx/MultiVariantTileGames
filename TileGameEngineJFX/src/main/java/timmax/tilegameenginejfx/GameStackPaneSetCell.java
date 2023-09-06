package timmax.tilegameenginejfx;

import javafx.scene.paint.Color;

public interface GameStackPaneSetCell {
    void setCellColor( int x, int y, Color cellColor);

    void setCellValue( int x, int y, String textValue);

    void setCellNumber( int x, int y, int numberValue);

    void setCellTextColor( int x, int y, Color textColor);

    void setCellValueEx( int x, int y, Color cellColor, String textValue);
}

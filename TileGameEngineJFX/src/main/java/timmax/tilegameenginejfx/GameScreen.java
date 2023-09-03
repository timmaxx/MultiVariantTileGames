package timmax.tilegameenginejfx;

import javafx.scene.paint.Color;

public interface GameScreen {
    void setScreenSize( int width, int height);
    // void showGrid( boolean isShow);

    // void showCoordinates( boolean isShow);

    void setCellColor( int x, int y, Color cellColor);

    // void setCellTextSize( int x, int y, int textSize);

    void setCellValue( int x, int y, String textValue);

    void setCellNumber( int x, int y, int numberValue);

    void setCellTextColor( int x, int y, Color textColor);

    void setCellValueEx( int x, int y, Color color, String textValue);

    // void setCellValueEx( int x, int y, Color cellColor, String textValue, Color textColor);

    void showMessageDialog( Color cellColor, String message, Color textColor, int textSize);

    // int getRandomNumber( int max);

    // int getRandomNumber( int min, int max);

    void initialize( );

    void createGame( );
}
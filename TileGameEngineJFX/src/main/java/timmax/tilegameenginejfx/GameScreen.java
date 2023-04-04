package timmax.tilegameenginejfx;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public interface GameScreen {
    void setScreenSize( int width, int height);
    void showGrid( boolean isShow);

    void showCoordinates( boolean isShow);

    void setCellColor( int x, int y, Color color);

    void setCellTextSize( int x, int y, int textSize);

    void setCellValue( int x, int y, String textValue);

    void setCellNumber( int x, int y, int numberValue);

    void setCellTextColor( int x, int y, Color color);

    void setCellValueEx( int x, int y, Color color, String textValue);

    void setCellValueEx( int x, int y, Color cellColor, String textValue, Color textColor);

    // void setCellValueEx( int x, int y, Color cellColor, String value, Color textColor, int textSize);

    void showMessageDialog( Color cellColor, String message, Color textColor, int textSize);

    // void setScore( int score);

    // void setLives( int lives);

    // void setTurnTimer( int timeMs);

    // void stopTurnTimer( );

    int getRandomNumber( int max);

    int getRandomNumber( int min, int max);

    void initialize( );

    void onMouseLeftClick( int x, int y);

    void onMouseRightClick( int x, int y);

    void onKeyPress( KeyCode keyCode);

    void onKeyReleased( KeyCode keyCode);

    // void onTurn( int step);
}
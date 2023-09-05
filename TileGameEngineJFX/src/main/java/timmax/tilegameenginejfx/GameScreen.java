package timmax.tilegameenginejfx;

import javafx.scene.paint.Color;
import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.ViewMainArea;

public interface GameScreen {
    void setScreenSize( int width, int height);

    void setCellColor( int x, int y, Color cellColor);

    void setCellValue( int x, int y, String textValue);

    void setCellNumber( int x, int y, int numberValue);

    void setCellTextColor( int x, int y, Color textColor);

    void setCellValueEx( int x, int y, Color color, String textValue);

    void showMessageDialog( Color cellColor, String message, Color textColor, int textSize);

    void initialize( );

    BaseModel initModel( );

    GameScreenController initGameScreenController( BaseModel baseModel, Game game);

    ViewMainArea initViewMainArea( BaseModel baseModel, Game game);
}
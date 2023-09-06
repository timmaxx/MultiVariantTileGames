package timmax.tilegameenginejfx;

import javafx.scene.paint.Color;
import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.ViewMainArea;

public interface GameScreen {
    void setScreenSize( int width, int height);

    void showMessageDialog( Color cellColor, String message, Color textColor, int textSize);

    void initialize( );

    BaseModel initModel( );

    GameScreenController initGameScreenController( BaseModel baseModel, Game game);

    ViewMainArea initViewMainArea( BaseModel baseModel, Game game);

    String initTitle( );
}
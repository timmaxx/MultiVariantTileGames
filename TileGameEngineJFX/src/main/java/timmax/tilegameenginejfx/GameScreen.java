package timmax.tilegameenginejfx;

import javafx.scene.paint.Color;
import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.ViewMainArea;

public interface GameScreen {
    void initialize( );

    void setScreenSize( int width, int height);

    void showGameOverMessage( Color cellColor, String message, Color textColor, int textSize);

    BaseModel initModel( );

    GameScreenController initGameScreenController( BaseModel baseModel, Game game);

    ViewMainArea initViewMainArea( BaseModel baseModel, Game game);

    String initTitle( );
}
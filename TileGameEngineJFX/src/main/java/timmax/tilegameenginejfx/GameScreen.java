package timmax.tilegameenginejfx;

import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.ViewMainArea;

public interface GameScreen {
    void initialize( );

    void setScreenSize( int width, int height);

    BaseModel initModel( );

    GameController initGameController( BaseModel baseModel, Game game);

    ViewMainArea initViewMainArea( BaseModel baseModel, Game game);

    String initTitle( );
}
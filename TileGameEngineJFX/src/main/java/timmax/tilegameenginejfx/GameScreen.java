package timmax.tilegameenginejfx;

import timmax.basetilemodel.BaseModel;
// import timmax.basetilemodel.ViewMainArea;

// Этот интерфейс изначально не должен был ориентироваться только на JFX.
// Но сейчас, для простоты, он уже зависит от JFX.
// Стоит разделить этот интерфейс на предка (не зависящего от JFX) и потомка (зависящего от JFX).
public interface GameScreen {
    void initialize( );

    BaseModel initModel( );

    GameController initGameController( BaseModel baseModel, Game game);

    // ViewMainArea initViewMainArea( BaseModel baseModel);
    ViewJfx initViewMainField( BaseModel baseModel);

    String initTitle( );
}
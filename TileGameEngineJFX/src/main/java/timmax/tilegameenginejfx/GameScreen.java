package timmax.tilegameenginejfx;

import timmax.basetilemodel.BaseModel;

// Этот интерфейс изначально не должен был ориентироваться только на JFX.
// Но сейчас, для простоты, он уже зависит от JFX.
// Стоит разделить этот интерфейс на предка (не зависящего от JFX) и потомка (зависящего от JFX).
public interface GameScreen {
    BaseModel initModel( );

    // GameController initGameController( BaseModel baseModel, Game game);

    ViewJfx initViewMainField( BaseModel baseModel);

    String initTitle( );
}
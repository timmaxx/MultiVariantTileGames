package timmax.tilegameenginejfx;

// import timmax.basetilemodel.BaseModel;
import timmax.tilegame.basemodel.ConnectionToBaseModel;

// Содержит контролеры по принятию событий от мыши над GameStackPane
public abstract class GameStackPaneController {
    // protected BaseModel baseModel;
    protected ConnectionToBaseModel connectionToBaseModel;

    // public GameStackPaneController( BaseModel baseModel)
    public GameStackPaneController( ConnectionToBaseModel connectionToBaseModel)
    {
        // this.baseModel = baseModel;
        this.connectionToBaseModel = connectionToBaseModel;
    }

    abstract protected void onMousePrimaryClick( int x, int y);

    abstract protected void onMouseSecondaryClick( int x, int y);
}
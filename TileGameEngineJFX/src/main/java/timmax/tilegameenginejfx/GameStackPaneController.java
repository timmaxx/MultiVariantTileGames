package timmax.tilegameenginejfx;

import timmax.basetilemodel.BaseModel;

// Содержит контролеры по принятию событий от мыши над GameStackPane
public abstract class GameStackPaneController {
    protected BaseModel baseModel;

    public GameStackPaneController( BaseModel baseModel) {
        this.baseModel = baseModel;
    }

    abstract protected void onMousePrimaryClick( int x, int y);

    abstract protected void onMouseSecondaryClick( int x, int y);
}
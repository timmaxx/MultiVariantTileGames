package timmax.tilegameenginejfx;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import timmax.basetilemodel.*;
import timmax.basetilemodel.gameevent.GameEvent;

public abstract class ViewJfx extends Pane implements ViewJfxInterface {
    protected GameQueueForOneView gameQueueForOneView;
    // private ViewInterface view;

    protected BaseModel baseModel;

    protected GameStackPaneController gameStackPaneController;


    public ViewJfx( BaseModel baseModel, GameStackPaneController gameStackPaneController) {
        super( );
        this.baseModel = baseModel;
        this.gameStackPaneController = gameStackPaneController;

        // view = new View( baseModel);
        gameQueueForOneView = baseModel.addViewListener( this); // К модели привязать это представление
    }

    @Override
    public Node getStyleableNode( ) {
        return super.getStyleableNode( );
    }

    @Override
    public GameEvent removeFromGameQueueForOneView( ) {
        return gameQueueForOneView.remove( );
    }
}
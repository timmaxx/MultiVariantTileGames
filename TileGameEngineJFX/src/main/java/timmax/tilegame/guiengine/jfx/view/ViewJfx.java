package timmax.tilegame.guiengine.jfx.view;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.transport.GameEventQueue;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.baseview.View;

import timmax.tilegame.guiengine.jfx.controller.GameStackPaneController;

public abstract class ViewJfx extends Pane implements View {
    private final GameEventQueue gameEventQueue;
    protected final GameStackPaneController gameStackPaneController;


    public ViewJfx( BaseModel baseModel, GameStackPaneController gameStackPaneController) {
        super( );
        this.gameStackPaneController = gameStackPaneController;
        gameEventQueue = baseModel.addViewListener( this);
        // gameEventQueue = new GameEventQueue( baseModel, this);
    }

    @Override
    public Node getStyleableNode( ) {
        return super.getStyleableNode( );
    }

    @Override
    public GameEvent removeFromGameQueueForOneView( ) {
        return gameEventQueue.remove( );
    }
}
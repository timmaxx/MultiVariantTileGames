package timmax.tilegame.guiengine.jfx.view;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.GameQueueForOneView;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.baseview.View;

import timmax.tilegame.guiengine.jfx.controller.GameStackPaneController;

public abstract class ViewJfx extends Pane implements View {
    private final GameQueueForOneView gameQueueForOneView;
    protected final GameStackPaneController gameStackPaneController;


    public ViewJfx( BaseModel baseModel, GameStackPaneController gameStackPaneController) {
        super( );
        this.gameStackPaneController = gameStackPaneController;
        gameQueueForOneView = baseModel.addViewListener( this);
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

/*
import timmax.tilegame.baseview.BaseView;

public abstract class ViewJfx extends Pane implements View {
    private final View view;
    protected final GameStackPaneController gameStackPaneController;


    public ViewJfx( BaseModel baseModel, GameStackPaneController gameStackPaneController) {
        super( );
        view = new BaseView( baseModel);
        this.gameStackPaneController = gameStackPaneController;
    }

    @Override
    public Node getStyleableNode( ) {
        return super.getStyleableNode( );
    }

    @Override
    public GameEvent removeFromGameQueueForOneView( ) {
        return view.removeFromGameQueueForOneView( );
    }
}
*/
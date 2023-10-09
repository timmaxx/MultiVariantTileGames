package timmax.tilegameenginejfx;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import timmax.tilegame.basemodel.ConnectionToBaseModel;
import timmax.tilegame.basemodel.GameQueueForOneView;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.baseview.ViewInterface;

public abstract class ViewJfx extends Pane implements ViewInterface {
    protected GameQueueForOneView gameQueueForOneView;
    // private ViewInterface view;

    // protected BaseModel baseModel;
    protected ConnectionToBaseModel connectionToBaseModel;

    protected GameStackPaneController gameStackPaneController;


    public ViewJfx( ConnectionToBaseModel connectionToBaseModel, GameStackPaneController gameStackPaneController) {
        super( );
        this.connectionToBaseModel = connectionToBaseModel;
        this.gameStackPaneController = gameStackPaneController;
        gameQueueForOneView = connectionToBaseModel.addView( this); // К модели привязать это представление
    }

    @Override
    public Node getStyleableNode( ) {
        return getStyleableNode( );
    }

    @Override
    public GameEvent removeFromGameQueueForOneView( ) {
        return gameQueueForOneView.remove( );
    }
}
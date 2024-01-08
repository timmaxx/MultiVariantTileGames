package timmax.tilegame.guiengine.jfx.view;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.baseview.View;

import timmax.tilegame.guiengine.jfx.controller.GameSceneController;
import timmax.tilegame.guiengine.jfx.controller.GameStackPaneController;

public abstract class ViewJfx extends Pane implements View {
    protected final GameStackPaneController gameStackPaneController;
    protected /*final*/ GameSceneController gameSceneController;


    public ViewJfx(BaseModel baseModel, GameStackPaneController gameStackPaneController) {
        super();
        this.gameStackPaneController = gameStackPaneController;
        baseModel.addView(this);
    }

    public ViewJfx(BaseModel baseModel, GameStackPaneController gameStackPaneController, GameSceneController gameSceneController) {
        super();
        this.gameStackPaneController = gameStackPaneController;
        this.gameSceneController = gameSceneController;
        baseModel.addView(this);
    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}

package timmax.tilegame.guiengine.jfx.view;

import javafx.scene.layout.Pane;

import timmax.tilegame.basemodel.protocol.client.IModelOfClient;
import timmax.tilegame.basecontroller.BaseController;
import timmax.tilegame.baseview.View;

public abstract class ViewJfx extends Pane implements View {
    protected final BaseController baseController;

    public ViewJfx(IModelOfClient iModelOfClient, BaseController baseController) {
        super();
        this.baseController = baseController;
        iModelOfClient.addView(this);
    }
}

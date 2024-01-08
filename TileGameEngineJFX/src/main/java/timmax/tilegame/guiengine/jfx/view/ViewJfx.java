package timmax.tilegame.guiengine.jfx.view;

import javafx.scene.layout.Pane;

import timmax.tilegame.basecontroller.BaseController;
import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.baseview.View;

public abstract class ViewJfx extends Pane implements View {
    protected final BaseController baseController;

    public ViewJfx(BaseModel baseModel, BaseController baseController) {
        super();
        this.baseController = baseController;
        baseModel.addView(this);
    }
}

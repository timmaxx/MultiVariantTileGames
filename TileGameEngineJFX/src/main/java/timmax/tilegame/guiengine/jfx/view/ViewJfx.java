package timmax.tilegame.guiengine.jfx.view;

import javafx.scene.layout.Pane;

import timmax.tilegame.transport.TransportOfClient;
import timmax.tilegame.basecontroller.BaseController;
import timmax.tilegame.baseview.View;

public abstract class ViewJfx<ClientId> extends Pane implements View {
    private final String viewName;
    protected final BaseController baseController;

    public ViewJfx(
            TransportOfClient<ClientId> transportOfClient,
            BaseController baseController,
            String viewName) {
        super();
        this.baseController = baseController;
        this.viewName = viewName;
        transportOfClient.getLocalClientState().addView(this);
    }

    @Override
    public String getViewName() {
        return viewName;
    }

    @Override
    public String toString() {
        return "ViewJfx{" +
                "viewName='" + viewName + '\'' +
                ", baseController=" + baseController +
                '}';
    }
}

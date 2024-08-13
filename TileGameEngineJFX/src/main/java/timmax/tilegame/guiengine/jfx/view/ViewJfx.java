package timmax.tilegame.guiengine.jfx.view;

import javafx.scene.layout.Pane;

import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.transport.TransportOfClient;
import timmax.tilegame.basecontroller.BaseController;
import timmax.tilegame.baseview.View;

public abstract class ViewJfx extends Pane implements View {
    private final String viewName;
    protected final BaseController baseController;
    protected final GameType gameType;

    // ToDo: Код сделать так, что-бы увязать:
    //       - перечень параметров конструктора ViewJfx
    //       - с перечнями типов параметров в ViewJfxClass
    //       - и с перечнем параметров в GameClientPaneJfx.
    public ViewJfx(
            TransportOfClient transportOfClient,
            BaseController baseController,
            String viewName,
            GameType gameType) {
        super();
        this.baseController = baseController;
        this.viewName = viewName;
        this.gameType = gameType;
        transportOfClient.getLocalClientStateAutomaton().addView(this);
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
                ", gameType=" + gameType +
                '}';
    }
}

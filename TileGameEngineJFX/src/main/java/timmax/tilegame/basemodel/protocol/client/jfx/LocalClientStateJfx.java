package timmax.tilegame.basemodel.protocol.client.jfx;

import java.lang.reflect.Constructor;

import timmax.tilegame.basecontroller.BaseController;
import timmax.tilegame.basemodel.protocol.client.IModelOfClient;
import timmax.tilegame.basemodel.protocol.client.LocalClientState;
import timmax.tilegame.baseview.View;
import timmax.tilegame.baseview.ViewMainField;
import timmax.tilegame.guiengine.jfx.view.ViewJfx;
import timmax.tilegame.guiengine.jfx.view.ViewMainFieldJfx;

public class LocalClientStateJfx extends LocalClientState {
    public LocalClientStateJfx(IModelOfClient iModelOfClient, BaseController baseController) {
        super(iModelOfClient, baseController);
    }

    @Override
    public Constructor<? extends View> getViewConstructor(Class<? extends View> classOfView, BaseController baseController, String viewName) {
        Constructor<? extends ViewJfx> constructorOfViewJfx;
        try {
            Class<? extends ViewJfx> classOfViewJfx;
            if (classOfView.equals(ViewMainField.class)) {
                classOfViewJfx = ViewMainFieldJfx.class;
            } else if (classOfView.equals(View.class)) {
                classOfViewJfx = ViewJfx.class;
            } else {
                throw new RuntimeException("Unknown class");
            }
            constructorOfViewJfx = classOfViewJfx.getConstructor(IModelOfClient.class, BaseController.class, String.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return constructorOfViewJfx;
    }
}

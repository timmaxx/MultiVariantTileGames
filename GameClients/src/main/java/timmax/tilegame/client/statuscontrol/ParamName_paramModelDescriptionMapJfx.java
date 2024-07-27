package timmax.tilegame.client.statuscontrol;

import timmax.tilegame.basemodel.protocol.server.IControlSet;
import timmax.tilegame.basemodel.protocol.server.ParamName_paramModelDescriptionMap;
import timmax.tilegame.transport.TransportOfClient;

import static timmax.tilegame.guiengine.jfx.view.ViewMainFieldJfx.DIFFERENCE_OF_LAYOUT_Y;
import static timmax.tilegame.guiengine.jfx.view.ViewMainFieldJfx.LAYOUT_Y_OF_FIRST_ROW;

public class ParamName_paramModelDescriptionMapJfx extends ParamName_paramModelDescriptionMap {
/*
    @Override
    public IControlSet getIControlSet(TransportOfClient transportOfClient) {
        ControlSetJfx controlSetJfx = new ControlSetJfx();
        int y = LAYOUT_Y_OF_FIRST_ROW;
        for (String paramName : transportOfClient.getLocalClientStateAutomaton().getParamName_paramModelDescriptionMap().keySet()) {
            ControlJfx controlJfx = new ControlJfx(paramName, y, transportOfClient);
            controlSetJfx.add(controlJfx);
            y += DIFFERENCE_OF_LAYOUT_Y;
        }
        return controlSetJfx;
    }
*/
}

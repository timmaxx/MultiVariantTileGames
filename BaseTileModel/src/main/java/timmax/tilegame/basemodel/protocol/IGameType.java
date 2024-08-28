package timmax.tilegame.basemodel.protocol;

import java.util.Map;

import timmax.tilegame.basemodel.protocol.server.ParamOfModelDescription;

public interface IGameType {
    String getId();
    Map<String, ParamOfModelDescription> getParamName_paramModelDescriptionMap();
}

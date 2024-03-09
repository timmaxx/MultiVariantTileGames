package timmax.tilegame.basemodel.protocol;

import java.util.Map;

import timmax.tilegame.basemodel.protocol.server.ParamOfModelDescription;

public interface IModelOfServerDescriptor {
    String getGameName();
    int getCountOfGamers();
    Map<String, ParamOfModelDescription> getMapOfParamsOfModelDescription();
}

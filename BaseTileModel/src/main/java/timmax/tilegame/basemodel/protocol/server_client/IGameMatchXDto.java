package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.GameMatchStatus;

import java.util.Map;

public interface IGameMatchXDto {
    String PARAM_NAME_WIDTH = "Width";
    String PARAM_NAME_HEIGHT = "Height";

    String getId();
    GameMatchStatus getStatus();
    Map<String, Integer> getParamsOfModelValueMap();
}

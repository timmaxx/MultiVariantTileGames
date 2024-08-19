package timmax.tilegame.basemodel.protocol.server_client;

import java.util.Map;

public interface IGameMatchXDto {
    String PARAM_NAME_WIDTH = "Width";
    String PARAM_NAME_HEIGHT = "Height";

    String getId();
    boolean isPlaying();
    Map<String, Integer> getParamsOfModelValueMap();
}

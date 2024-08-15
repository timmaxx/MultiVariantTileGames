package timmax.tilegame.basemodel.protocol.server_client;

import java.util.Map;

public interface IGameMatchXDto {
    String getId();
    boolean isPlaying();
    Map<String, Integer> getParamsOfModelValueMap();
}

package timmax.tilegame.basemodel.protocol.server_client;

import java.util.Map;

public interface IGameMatchX {
    String getId();
    boolean isPlaying();
    void startGameMatch(Map<String, Integer> mapOfParamsOfModelValue);
    Map<String, Integer> getParamsOfModelValueMap();
}

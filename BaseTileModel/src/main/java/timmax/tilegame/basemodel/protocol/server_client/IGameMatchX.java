package timmax.tilegame.basemodel.protocol.server_client;

import java.util.Map;

public interface IGameMatchX {
    String getId();
    boolean isPlaying();
    // ToDo: Удалить отсюда
    void startGameMatch(Map<String, Integer> mapOfParamsOfModelValue);
    // ToDo: Удалить отсюда
    void resumeGameMatch();
    Map<String, Integer> getParamsOfModelValueMap();
}

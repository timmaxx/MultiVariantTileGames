package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.GameMatchStatus;
import timmax.tilegame.basemodel.protocol.server.GameMatch;

import java.util.Map;

public interface IGameMatchX {
    String PARAM_NAME_WIDTH = "Width";
    String PARAM_NAME_HEIGHT = "Height";

    String getId();
    GameMatchStatus getStatus();
    Map<String, Integer> getParamsOfModelValueMap();

    int getWidth();
    int getHeight();

    <ClientId> GameMatch<ClientId> start(GameMatch<ClientId> gameMatch);
    // ToDo: Вероятно возвращаемый параметр должен быть GameMatch
    void resume();
}

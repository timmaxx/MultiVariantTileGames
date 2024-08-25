package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.GameMatchStatus;

import java.util.Map;

public interface IGameMatchX {
    String PARAM_NAME_WIDTH = "Width";
    String PARAM_NAME_HEIGHT = "Height";

    String getId();
    GameMatchStatus getStatus();
    Map<String, Integer> getParamsOfModelValueMap();

    int getWidth();
    int getHeight();

    GameMatchExtendedDto start(GameMatchExtendedDto gameMatchExtendedDto);
    // ToDo: Вероятно возвращаемый параметр должен быть GameMatchExtendedDto
    void resume();
}

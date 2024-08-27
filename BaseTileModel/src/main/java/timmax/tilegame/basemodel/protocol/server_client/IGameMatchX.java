package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.GameMatchStatus;

import java.util.Map;

//  Интерфейс должен быть реализован как для серверных матчей, так и для клиентских (X в имени).
public interface IGameMatchX {
    String PARAM_NAME_WIDTH = "Width";
    String PARAM_NAME_HEIGHT = "Height";

    String getId();
    GameMatchStatus getStatus();
    Map<String, Integer> getParamsOfModelValueMap();

    int getWidth();
    int getHeight();

    GameMatchExtendedDto start(GameMatchExtendedDto gameMatchExtendedDto);
}

package timmax.tilegame.basemodel.protocol.server_client;

import java.util.Map;

public interface IGameMatchX extends IGameMatchXDto {
    int getWidth();
    int getHeight();

    void setParamsOfModelValueMap(Map<String, Integer> paramsOfModelValueMap);

    void start();
    void resume();
}

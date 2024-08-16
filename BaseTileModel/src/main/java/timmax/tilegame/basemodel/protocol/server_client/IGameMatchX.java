package timmax.tilegame.basemodel.protocol.server_client;

import java.util.Map;

public interface IGameMatchX extends IGameMatchXDto {
    void start();
    void start(int width, int height);
    void start(Map<String, Integer> paramsOfModelValueMap);

    void resume();
}

package timmax.tilegame.basemodel.protocol.server_client;

import java.util.Map;

public interface IGameMatchX extends IGameMatchXDto {
    int getWidth();
    int getHeight();

    void start();
    void start(int width, int height, Map<String, Integer> paramsOfModelValueMap);

    void resume();
}

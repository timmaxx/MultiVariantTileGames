package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.gamecommand.GameCommandKeyPressed;
import timmax.tilegame.basemodel.gamecommand.GameCommandMouseClick;
import timmax.tilegame.basemodel.protocol.server_client.IGameMatchX;

import java.util.Map;

public interface IGameMatch extends IGameMatchX {
    void executeMouseCommand(GameCommandMouseClick gameCommandMouseClick);
    void executeKeyboardCommand(GameCommandKeyPressed gameCommandKeyPressed);

    void win();
    void restart();

    void setParamsOfModelValueMap(int width, int height, Map<String, Integer> mapOfParamsOfModelValue);
    int paramsOfModelValueMapGet(String paramName);
}

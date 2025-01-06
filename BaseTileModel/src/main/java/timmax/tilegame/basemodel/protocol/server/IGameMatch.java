package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.gamecommand.GameCommandKeyPressed;
import timmax.tilegame.basemodel.gamecommand.GameCommandMouseClick;
import timmax.tilegame.basemodel.dto.GameMatchDto;
import timmax.tilegame.basemodel.protocol.server_client.IGameMatchX;

import java.util.Map;

//  Интерфейс должен быть реализован только для серверных матчей (в имени нет суффиксов).
public interface IGameMatch extends IGameMatchX {
    void executeMouseCommand(GameCommandMouseClick gameCommandMouseClick);
    void executeKeyboardCommand(GameCommandKeyPressed gameCommandKeyPressed);

    void win();
    void restart();

    void setParamsOfModelValueMap(Map<String, Integer> mapOfParamsOfModelValue);
    // ToDo: Избавиться от "Warning:(19, 9) Method 'getFromParamsOfModelValueMap(java.lang.String)' is never used"
    int getFromParamsOfModelValueMap(String paramName);
    GameMatchDto getGameMatchDto();
}

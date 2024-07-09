package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.gamecommand.GameCommandKeyPressed;
import timmax.tilegame.basemodel.gamecommand.GameCommandMouseClick;

import java.util.Map;

public interface IGameMatch {
    void createNewGame();

    void executeMouseCommand(GameCommandMouseClick gameCommandMouseClick);
    void executeKeyboardCommand(GameCommandKeyPressed gameCommandKeyPressed);

    void win();
    void restart();

    // ToDo: Сеттер вероятно совсем не нужен, т.к. пусть лучше конструктор инициализирует.
    void setParamsOfModelValueMap(Map<String, Integer> mapOfParamsOfModelValue);
    // ToDo: Геттер в чистом виде вряд-ли нужен. Вместо этого сделать доступ к
    //       paramsOfModelValueMap.get(...)
    Map<String, Integer> getParamsOfModelValueMap();
}

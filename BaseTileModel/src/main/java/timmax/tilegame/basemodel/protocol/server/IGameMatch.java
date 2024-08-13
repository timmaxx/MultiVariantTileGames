package timmax.tilegame.basemodel.protocol.server;

import javafx.scene.paint.Color;
import timmax.tilegame.basemodel.gamecommand.GameCommandKeyPressed;
import timmax.tilegame.basemodel.gamecommand.GameCommandMouseClick;
import timmax.tilegame.basemodel.protocol.server_client.IGameMatchX;

import java.util.Map;

public interface IGameMatch extends IGameMatchX {
    // ToDo: Переименовать все "createNewGame" в "start"
    void createNewGame();
    void createNewGame(int width, int height);
    void resume();

    void executeMouseCommand(GameCommandMouseClick gameCommandMouseClick);
    void executeKeyboardCommand(GameCommandKeyPressed gameCommandKeyPressed);

    void win();
    void restart();

    // ToDo: Сеттер вероятно совсем не нужен, т.к. пусть лучше конструктор инициализирует.
    void setParamsOfModelValueMap(Map<String, Integer> mapOfParamsOfModelValue);
    int paramsOfModelValueMapGet(String paramName);
}

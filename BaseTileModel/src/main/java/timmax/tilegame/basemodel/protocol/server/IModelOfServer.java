package timmax.tilegame.basemodel.protocol.server;

import java.util.Map;

import timmax.tilegame.basemodel.gamecommand.GameCommandKeyPressed;
import timmax.tilegame.basemodel.gamecommand.GameCommandMouseClick;
import timmax.tilegame.basemodel.protocol.IGameType;

public interface IModelOfServer extends IGameType {
    void createNewGame();

    void executeMouseCommand(GameCommandMouseClick gameCommandMouseClick);
    void executeKeyboardCommand(GameCommandKeyPressed gameCommandKeyPressed);

    void win();
    void restart();

    // interface IGameType
    @Override
    String getGameName();

    @Override
    int getCountOfGamers();

    @Override
    Map<String, ParamOfModelDescription> getMapOfParamsOfModelDescription();
}

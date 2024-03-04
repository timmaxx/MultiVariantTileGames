package timmax.tilegame.basemodel.protocol.server;

import java.util.Map;

import timmax.tilegame.basemodel.gamecommand.GameCommandKeyPressed;
import timmax.tilegame.basemodel.gamecommand.GameCommandMouseClick;
import timmax.tilegame.basemodel.protocol.IModelOfServerDescriptor;

public interface IModelOfServer extends IModelOfServerDescriptor {
    void createNewGame();

    void executeMouseCommand(GameCommandMouseClick gameCommandMouseClick);
    void executeKeyboardCommand(GameCommandKeyPressed gameCommandKeyPressed);

    void win();
    void restart();

    // Overiden methods from interface IModelOfServerDescriptor
    @Override
    String getGameName();

    @Override
    int getCountOfGamers();

    @Override
    Map<String, Integer> getMapOfParamsOfModel();
}

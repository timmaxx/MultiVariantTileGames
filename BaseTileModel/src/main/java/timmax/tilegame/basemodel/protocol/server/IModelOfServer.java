package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.gamecommand.GameCommandKeyPressed;
import timmax.tilegame.basemodel.gamecommand.GameCommandMouseClick;

public interface IModelOfServer<ClienId> {
    String getGameName();
    int getCountOfGamers();

    void createNewGame();
    void addRemoteView(RemoteView<ClienId> remoteView);

    void executeMouseCommand(GameCommandMouseClick gameCommandMouseClick);
    void executeKeyboardCommand(GameCommandKeyPressed gameCommandKeyPressed);

    void win();
    void restart();
}

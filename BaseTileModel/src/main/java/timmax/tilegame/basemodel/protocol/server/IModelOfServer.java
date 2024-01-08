package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.gamecommand.GameCommandKeyPressed;
import timmax.tilegame.basemodel.gamecommand.GameCommandMouseClick;

public interface IModelOfServer<T> {
    void createNewGame();

    void addRemoteView(RemoteView<T> remoteView);

    /*
        void restart();

        void nextLevel();

        void prevLevel();
    */

    void win();

    void executeMouseCommand(GameCommandMouseClick gameCommandMouseClick);

    void executeKeyboardCommand(GameCommandKeyPressed gameCommandKeyPressed);
}

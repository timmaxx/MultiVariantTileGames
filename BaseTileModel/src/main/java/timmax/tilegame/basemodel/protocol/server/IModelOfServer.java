package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.gamecommand.GameCommandKeyPressed;
import timmax.tilegame.basemodel.gamecommand.GameCommandMouseClick;
import timmax.tilegame.basemodel.protocol.IModelOfServerDescriptor;

public interface IModelOfServer<ClientId> extends IModelOfServerDescriptor {
    void createNewGame();
    void addRemoteView(RemoteView<ClientId> remoteView);

    void executeMouseCommand(GameCommandMouseClick gameCommandMouseClick);
    void executeKeyboardCommand(GameCommandKeyPressed gameCommandKeyPressed);

    void win();
    void restart();

    // Overiden methods from interface IModelOfServerDescriptor
    @Override
    String getGameName();

    @Override
    int getCountOfGamers();
}

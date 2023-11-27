package timmax.tilegame.basemodel.protocol.server;

public interface IModelOfServer {
    void createNewGame();

    void addRemoteView(RemoteView remoteView);

    /*
        void restart();

        void nextLevel();

        void prevLevel();
    */
    void win();
}
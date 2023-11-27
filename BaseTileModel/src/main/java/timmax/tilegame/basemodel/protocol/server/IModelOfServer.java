package timmax.tilegame.basemodel.protocol.server;

public interface IModelOfServer<T> {
    void createNewGame();

    void addRemoteView(RemoteView<T> remoteView);

    /*
        void restart();

        void nextLevel();

        void prevLevel();
    */
    void win();
}
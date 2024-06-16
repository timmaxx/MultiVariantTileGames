package timmax.tilegame.basemodel.protocol.server_client;

public interface IClientState07GameMatchSelected<Model> extends IClientState00 {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:

    // ---- 7 (MatchSelected)
    Model getServerBaseModel();

    void forgetServerBaseModel();

    void setGameIsPlaying(Boolean gameIsPlaying);
}

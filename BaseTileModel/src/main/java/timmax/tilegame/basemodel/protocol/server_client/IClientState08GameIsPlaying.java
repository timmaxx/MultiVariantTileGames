package timmax.tilegame.basemodel.protocol.server_client;

//  Игра (экземпляр класса модели) в сотоянии "игра".
public interface IClientState08GameIsPlaying extends IClientState00 {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:

    // ---- 8 (GameIsPlaying)
    Boolean getGameIsPlaying();

    void forgetGameMatchPlaying();
}

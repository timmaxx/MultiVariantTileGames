package timmax.tilegame.basemodel.protocol.server_client;

import java.util.Map;

//  Выбрана партия (создан экземпляр класса модели и он в состоянии настройка).
public interface IClientState07GameMatchSelected<GameMatchX extends IGameMatchX> extends IClientState00 {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:

    // ---- 7 (MatchSelected)
    GameMatchX getGameMatchX();
    Boolean getGameMatchIsPlaying();


    void reselectGameMatch();
    //  interface TransportOfClient
    //      void reselectGameMatch();


    void setParamsOfModelValueMapOfGameMatchAndStart(Map<String, Integer> mapOfParamsOfModelValue);
    //  interface TransportOfClient
    //      void startGameMatch(Map<String, Integer> mapOfParamsOfModelValue);


    void resumeGameMatch();
    //  interface TransportOfClient
    //      void resumeGameMatch();
}

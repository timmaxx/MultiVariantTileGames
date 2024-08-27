package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.GameMatchStatus;
import timmax.tilegame.basemodel.protocol.server.GameMatch;

//  Выбрана партия (создан экземпляр класса модели и он в состоянии настройка).
public interface IClientState07GameMatchSelected extends IClientState00 {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:

    // ---- 7 (MatchSelected)
    GameMatch getGameMatch();
    GameMatchStatus getGameMatchStatus();


    void reselectGameMatch();
    //  interface TransportOfClient
    //      void reselectGameMatch();

    GameMatch startGameMatch(GameMatch gameMatch);

    void resumeGameMatch();
    //  interface TransportOfClient
    //      void resumeGameMatch();
}

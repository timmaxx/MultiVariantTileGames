package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.GameMatchStatus;
import timmax.tilegame.basemodel.dto.GameMatchExtendedDto;

//  Выбрана партия (создан экземпляр класса модели и он в состоянии настройка).
public interface IClientState07GameMatchWasSet extends IClientState00 {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:

    // ---- 7 (MatchSelected)
    IGameMatchX getGameMatchX();
    GameMatchStatus getGameMatchStatus();


    void resetGameMatch();
    //  interface TransportOfClient
    //      void resetGameMatch();

    void startGameMatch(GameMatchExtendedDto gameMatchExtendedDto);
}

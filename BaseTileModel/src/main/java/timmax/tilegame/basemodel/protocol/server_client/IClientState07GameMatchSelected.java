package timmax.tilegame.basemodel.protocol.server_client;

import java.util.Map;

//  Выбрана партия (создан экземпляр класса модели и он в состоянии настройка).
public interface IClientState07GameMatchSelected<GameMatchX extends IGameMatchX> extends IClientState00 {
    // Нумерация приведена соответствующая классам Pane0Х... пакета timmax.tilegame.client.statuscontrol:

    // ---- 7 (MatchSelected)
    // ToDo: Переименовать в reselectGameMatchX()
    void resetGameMatchX();
    GameMatchX getGameMatchX();
    void startGameMatch(Map<String, Integer> mapOfParamsOfModelValue);
    void resumeGameMatch();
    Boolean getGameMatchIsPlaying();
}

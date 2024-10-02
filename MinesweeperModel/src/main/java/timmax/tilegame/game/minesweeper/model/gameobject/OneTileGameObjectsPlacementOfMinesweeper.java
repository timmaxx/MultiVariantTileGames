package timmax.tilegame.game.minesweeper.model.gameobject;

import timmax.tilegame.basemodel.gameobject.*;
import timmax.tilegame.basemodel.protocol.server.GameType;

public class OneTileGameObjectsPlacementOfMinesweeper extends OneTileGameObjectsPlacement {
    public OneTileGameObjectsPlacementOfMinesweeper(OneTileGameObjectsPlacement oneTileGameObjectsPlacement) {
        super(oneTileGameObjectsPlacement);
    }

    public OneTileGameObjectsPlacementOfMinesweeper(
            GameType gameType,
            WidthHeightSizes widthHeightSizes,
            int playerIndexOfCurrentMove
    ) {
        //  ToDo:   Вместо gsmeType лучше брать его из фабрики (синглтон).
        super(gameType, widthHeightSizes, playerIndexOfCurrentMove);
    }

    @Override
    public void add(OneTileGameObjectStateAutomaton oneTileGameObjectStateAutomaton) {
        super.add(oneTileGameObjectStateAutomaton);
    }
}

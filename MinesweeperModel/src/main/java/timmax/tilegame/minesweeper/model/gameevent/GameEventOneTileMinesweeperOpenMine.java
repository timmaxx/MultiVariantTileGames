package timmax.tilegame.minesweeper.model.gameevent;

import timmax.tilegame.basemodel.gameevent.GameEventOneTile;
import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;
import timmax.tilegame.basemodel.protocol.server_client.GuiCellValues;

import static timmax.tilegame.minesweeper.model.GameMatchOfMinesweeper.*;

public class GameEventOneTileMinesweeperOpenMine extends GameEventOneTile {
    public GameEventOneTileMinesweeperOpenMine() {
    }

    public GameEventOneTileMinesweeperOpenMine(XYCoordinate xyCoordinate) {
        super(xyCoordinate);

        //  ToDo:   Ниже относится к визуализации. Удалить это отсюда.
        guiCellValues = new GuiCellValues(MINE_BACKGROUND_COLOR, MINE_TEXT_COLOR, MINE_TEXT);
    }
}

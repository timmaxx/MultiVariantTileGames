package timmax.tilegame.minesweeper.model.gameevent;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.gameevent.GameEventOneTile;
import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;
import timmax.tilegame.visualization.GuiCellValues;

import static timmax.tilegame.minesweeper.model.GameMatchOfMinesweeper.NOMINE_BACKGROUND_COLOR;
import static timmax.tilegame.minesweeper.model.GameMatchOfMinesweeper.NOMINE_TEXT_COLOR;

public class GameEventOneTileMinesweeperOpenNoMine extends GameEventOneTile {
    private int countOfMineNeighbors;

    public GameEventOneTileMinesweeperOpenNoMine() {
    }

    public GameEventOneTileMinesweeperOpenNoMine(XYCoordinate xyCoordinate, int countOfMineNeighbors) {
        super(xyCoordinate);

        this.countOfMineNeighbors = countOfMineNeighbors;

        // ToDo: Ниже относится к визуализации. Удалить это отсюда.
        guiCellValues = new GuiCellValues(NOMINE_BACKGROUND_COLOR, NOMINE_TEXT_COLOR, String.valueOf(countOfMineNeighbors));
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeInt(countOfMineNeighbors);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        countOfMineNeighbors = in.readInt();
    }
}

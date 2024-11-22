package timmax.tilegame.minesweeper.model.gameevent;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.gameevent.GameEventOneTile;
import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;

import static timmax.tilegame.minesweeper.model.GameTypeOfMinesweeper.NOMINE_BACKGROUND_COLOR;
import static timmax.tilegame.minesweeper.model.GameTypeOfMinesweeper.NOMINE_TEXT_COLOR;

public class GameEventOneTileMinesweeperOpenNoMine extends GameEventOneTile {
    private int countOfMineNeighbors;

    public GameEventOneTileMinesweeperOpenNoMine() {
    }

    public GameEventOneTileMinesweeperOpenNoMine(XYCoordinate xyCoordinate, int countOfMineNeighbors) {
        super(xyCoordinate);

        this.countOfMineNeighbors = countOfMineNeighbors;

        // ToDo: Ниже относится к визуализации. Удалить это отсюда.
        // ToDo: Хотя-бы в GameType эти реквизиты можно было-бы переместить.
        cellBackgroundColor = NOMINE_BACKGROUND_COLOR;
        cellTextColor = NOMINE_TEXT_COLOR;
        cellText = String.valueOf(countOfMineNeighbors);
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

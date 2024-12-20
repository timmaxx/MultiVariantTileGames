package timmax.tilegame.minesweeper.model.gameevent;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.gameevent.GameEventOneTile;
import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;

import static timmax.tilegame.minesweeper.model.GameTypeOfMinesweeper.*;

public class GameEventOneTileMinesweeperOpenMine extends GameEventOneTile {
    public GameEventOneTileMinesweeperOpenMine() {
    }

    public GameEventOneTileMinesweeperOpenMine(XYCoordinate xyCoordinate) {
        super(xyCoordinate);

        // ToDo: Ниже относится к визуализации. Удалить это отсюда.
        // ToDo: Хотя-бы в GameType эти реквизиты можно было-бы переместить.
        cellBackgroundColor = MINE_BACKGROUND_COLOR;
        cellTextColor = MINE_TEXT_COLOR;
        cellText = MINE;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
    }
}

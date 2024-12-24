package timmax.tilegame.minesweeper.model.gameevent;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.gameevent.GameEventOneTile;
import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;

import static timmax.tilegame.minesweeper.model.GameMatchOfMinesweeper.*;

public class GameEventOneTileMinesweeperChangeFlag extends GameEventOneTile {
    private boolean isFlag;

    public GameEventOneTileMinesweeperChangeFlag() {
    }

    public GameEventOneTileMinesweeperChangeFlag(XYCoordinate xyCoordinate, boolean isFlag) {
        super(xyCoordinate);
        this.isFlag = isFlag;

        //  ToDo:   Ниже относится к визуализации. Удалить это отсюда.
        cellTextColor = UNOPENED_TEXT_COLOR;
        if (isFlag) {
            cellBackgroundColor = FLAG_BACKGROUND_COLOR;
            cellText = FLAG_TEXT;
        } else {
            cellBackgroundColor = UNOPENED_BACKGROUND_COLOR;
            cellText = UNOPENED_TEXT;
        }
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeBoolean(isFlag);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        isFlag = in.readBoolean();
    }
}

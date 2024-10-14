package timmax.tilegame.game.minesweeper.model.gameevent;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.scene.paint.Color;

import timmax.tilegame.basemodel.gameevent.GameEventOneTile;
import timmax.tilegame.basemodel.gameobject.XYCoordinate;

import static timmax.tilegame.game.minesweeper.model.GameTypeOfMinesweeper.*;

public class GameEventOneTileMinesweeperChangeFlag extends GameEventOneTile {
    private boolean isFlag;

    public GameEventOneTileMinesweeperChangeFlag() {
    }

    public GameEventOneTileMinesweeperChangeFlag(XYCoordinate xyCoordinate, boolean isFlag) {
        super(xyCoordinate);
        this.isFlag = isFlag;

        // ToDo: Ниже относится к визуализации. Удалить это отсюда.
        // ToDo: Хотя-бы в GameType эти реквизиты можно было-бы переместить.
        cellTextColor = Color.BLACK;
        if (isFlag) {
            this.cellBackgroundColor = FLAG_CELL_COLOR;
            cellText = FLAG;
        } else {
            this.cellBackgroundColor = UNOPENED_CELL_COLOR;
            cellText = "";
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

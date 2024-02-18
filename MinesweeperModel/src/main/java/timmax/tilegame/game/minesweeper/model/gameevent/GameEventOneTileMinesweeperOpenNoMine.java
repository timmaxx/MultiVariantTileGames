package timmax.tilegame.game.minesweeper.model.gameevent;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.scene.paint.Color;

import timmax.tilegame.basemodel.gameevent.GameEventOneTile;

import static timmax.tilegame.game.minesweeper.model.ModelOfServerOfMinesweeper.OPENED_CELL_COLOR;

public class GameEventOneTileMinesweeperOpenNoMine extends GameEventOneTile {
    private int countOfMineNeighbors;

    public GameEventOneTileMinesweeperOpenNoMine() {
    }

    public GameEventOneTileMinesweeperOpenNoMine(int x, int y, int countOfMineNeighbors) {
        super(x, y);
        this.countOfMineNeighbors = countOfMineNeighbors;

        // ToDo: Ниже относится к визуализации. Удалить это отсюда.
        cellBackgroundColor = OPENED_CELL_COLOR;
        cellTextColor = Color.BLACK;
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

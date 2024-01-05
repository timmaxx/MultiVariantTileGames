package timmax.tilegame.game.minesweeper.model.gameevent;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.scene.paint.Color;

import timmax.tilegame.basemodel.gameevent.GameEventOneTile;

import static timmax.tilegame.game.minesweeper.model.ModelOfServerOfMinesweeper.MINE;
import static timmax.tilegame.game.minesweeper.model.ModelOfServerOfMinesweeper.MINE_CELL_COLOR;

public class GameEventOneTileOpenMine extends GameEventOneTile {
    public GameEventOneTileOpenMine() {
    }

    public GameEventOneTileOpenMine(int x, int y) {
        super(x, y);
        cellBackgroundColor = MINE_CELL_COLOR;
        cellTextColor = Color.BLACK;
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

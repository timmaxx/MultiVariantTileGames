package timmax.tilegame.game.minesweeper.model.gameevent;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.scene.paint.Color;

import timmax.tilegame.basemodel.gameevent.GameEventOneTile;

import static timmax.tilegame.game.minesweeper.model.ModelOfServerOfMinesweeper.OPENED_CELL_COLOR;

public class GameEventOneTileOpenNoMine extends GameEventOneTile {
    // final (в этом классе и в любом, который реализует Externalizable) пришлось убрать из-за readExternal.
    // Было-бы лучше конечно final оставить!
    // Да и конструктор без параметров - тоже для Externalizable, и лучше-бы без такого конструктора обойтись.
    // А так можно было-бы свой интерфейс сделать с конструктором, у которого был-бы параметром массив объектов!
    private /*final*/ int countOfMineNeighbors;

    public GameEventOneTileOpenNoMine() {
    }

    public GameEventOneTileOpenNoMine(int x, int y, int countOfMineNeighbors) {
        super(x, y);
        this.countOfMineNeighbors = countOfMineNeighbors;

        // ToDo: Ниже относится к визуализации. Удалить это отсюда.
        cellBackgroundColor = OPENED_CELL_COLOR;
        cellTextColor = Color.BLACK;
        cellText = String.valueOf(countOfMineNeighbors);
    }
/*
    public int getCountOfMineNeighbors() {
        return countOfMineNeighbors;
    }
*/
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

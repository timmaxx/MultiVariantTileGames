package timmax.tilegame.game.sokoban.model.gameevent;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.scene.paint.Color;

import timmax.tilegame.basemodel.gameevent.GameEventOneTile;

import timmax.tilegame.basemodel.gameobject.XYCoordinate;
import timmax.tilegame.game.sokoban.model.gameobject.WhoMovableInTile;
import timmax.tilegame.game.sokoban.model.gameobject.WhoPersistentInTile;

import static timmax.tilegame.game.sokoban.model.GameTypeOfSokoban.*;
import static timmax.tilegame.game.sokoban.model.gameobject.WhoMovableInTile.*;
import static timmax.tilegame.game.sokoban.model.gameobject.WhoPersistentInTile.*;

public class GameEventOneTileSokobanChangeable extends GameEventOneTile {
    private WhoPersistentInTile whoPersistentInTile;
    private WhoMovableInTile whoMovableInTile;

    public GameEventOneTileSokobanChangeable() {
        super();
    }

    public GameEventOneTileSokobanChangeable(
            XYCoordinate xyCoordinate,
            // ToDo: Избавиться от "Warning:(30, 13) Class 'WhoPersistentInTile' is not exported from module 'timmax.tilegame.game.sokoban.model'"
            WhoPersistentInTile whoPersistentInTile,
            // ToDo: Избавиться от "Warning:(32, 13) Class 'WhoMovableInTile' is not exported from module 'timmax.tilegame.game.sokoban.model'"
            WhoMovableInTile whoMovableInTile) {
        super(xyCoordinate);
        this.whoPersistentInTile = whoPersistentInTile;
        this.whoMovableInTile = whoMovableInTile;

        // ToDo: Ниже относится к визуализации. Удалить это отсюда.
        // ToDo: Хотя-бы в GameType эти реквизиты можно было-бы переместить.
        if (whoPersistentInTile == IS_WALL) {
            cellBackgroundColor = WALL_CELL_COLOR;
        } else if (whoPersistentInTile == IS_HOME) {
            cellBackgroundColor = HOME_CELL_COLOR;
        } else if (whoPersistentInTile == IS_EMPTY) {
            cellBackgroundColor = EMPTY_CELL_COLOR;
        }

        if (whoMovableInTile == IS_PLAYER) {
            cellText = PLAYER;
            cellTextColor = PLAYER_TEXT_COLOR;
        } else if (whoMovableInTile == IS_BOX) {
            cellText = BOX;
            cellTextColor = BOX_TEXT_COLOR;
        } else if (whoMovableInTile == IS_NOBODY) {
            cellText = "";
            cellTextColor = Color.BLACK;
        }
    }

    @Override
    public String toString() {
        return "GameEventOneTileSokobanChangeable{" +
                super.toString() + " " +
                "whoPersistentInTile=" + whoPersistentInTile +
                ", whoMovableInTile=" + whoMovableInTile +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeObject(whoPersistentInTile);
        out.writeObject(whoMovableInTile);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        whoPersistentInTile = (WhoPersistentInTile) in.readObject();
        whoMovableInTile = (WhoMovableInTile) in.readObject();
    }
}

package timmax.tilegame.game.sokoban.model.gameevent;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.scene.paint.Color;

import timmax.tilegame.basemodel.gameevent.GameEventOneTile;

import timmax.tilegame.game.sokoban.model.gameobject.WhoMovableInTile;
import timmax.tilegame.game.sokoban.model.gameobject.WhoPersistentInTile;

import static timmax.tilegame.game.sokoban.model.ModelOfServerOfSokoban.*;
import static timmax.tilegame.game.sokoban.model.gameobject.WhoMovableInTile.*;
import static timmax.tilegame.game.sokoban.model.gameobject.WhoPersistentInTile.*;

public class GameEventOneTileSokobanChangeable extends GameEventOneTile {
    // final (в этом классе и в любом, который реализует Externalizable) пришлось убрать из-за readExternal.
    // Было-бы лучше конечно final оставить!
    // Да и конструктор без параметров - тоже для Externalizable, и лучше-бы без такого конструктора обойтись.
    // А так можно было-бы сделать:
    // - (вместо readExternal) свой дополнительный класс с конструктором, у которого был-бы параметром массив объектов,
    // - (writeExternal) в этом-же классе или всё таки в интерфейсе.
    private /*final*/ WhoPersistentInTile whoPersistentInTile;
    private /*final*/ WhoMovableInTile whoMovableInTile;

    public GameEventOneTileSokobanChangeable() {
        super();
    }

    public GameEventOneTileSokobanChangeable(
            int x,
            int y,
            WhoPersistentInTile whoPersistentInTile,
            WhoMovableInTile whoMovableInTile) {
        super(x, y);
        this.whoPersistentInTile = whoPersistentInTile;
        this.whoMovableInTile = whoMovableInTile;

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
/*
    public WhoPersistentInTile getWhoPersistentInTile() {
        return whoPersistentInTile;
    }

    public WhoMovableInTile getWhoMovableInTile() {
        return whoMovableInTile;
    }
*/
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

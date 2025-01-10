package timmax.tilegame.sokoban.model.gameevent;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.scene.paint.Color;
import timmax.tilegame.basemodel.gameevent.GameEventOneTile;
import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;
import timmax.tilegame.basemodel.protocol.server_client.GuiDefaultConstants;
import timmax.tilegame.sokoban.model.placement.gameobject.WhoMovableInTile;
import timmax.tilegame.sokoban.model.placement.gameobject.WhoPersistentInTile;

import static timmax.tilegame.sokoban.model.GameMatchOfSokoban.*;
import static timmax.tilegame.sokoban.model.placement.gameobject.WhoMovableInTile.*;
import static timmax.tilegame.sokoban.model.placement.gameobject.WhoPersistentInTile.*;

public class GameEventOneTileSokobanChangeable extends GameEventOneTile {
    private WhoPersistentInTile whoPersistentInTile;
    private WhoMovableInTile whoMovableInTile;

    public GameEventOneTileSokobanChangeable() {
        super();
    }

    public GameEventOneTileSokobanChangeable(
            XYCoordinate xyCoordinate,
            //  Warning:(27, 13) Class 'WhoPersistentInTile' is not exported from module 'timmax.tilegame.sokoban.model'
            WhoPersistentInTile whoPersistentInTile,
            //  Warning:(29, 13) Class 'WhoMovableInTile' is not exported from module 'timmax.tilegame.sokoban.model'
            WhoMovableInTile whoMovableInTile) {
        super(xyCoordinate);
        this.whoPersistentInTile = whoPersistentInTile;
        this.whoMovableInTile = whoMovableInTile;

        //  ToDo:   Ниже относится к визуализации. Удалить это отсюда.
        Color cellBackgroundColor = null;
        if (whoPersistentInTile == IS_WALL) {
            cellBackgroundColor = WALL_BACKGROUND_COLOR;
        } else if (whoPersistentInTile == IS_HOME) {
            cellBackgroundColor = HOME_BACKGROUND_COLOR;
        } else if (whoPersistentInTile == IS_EMPTY) {
            cellBackgroundColor = EMPTY_BACKGROUND_COLOR;
        }

        String cellText = null;
        Color cellTextColor = null;
        if (whoMovableInTile == IS_PLAYER) {
            cellText = PLAYER_TEXT;
            cellTextColor = PLAYER_TEXT_COLOR;
        } else if (whoMovableInTile == IS_BOX) {
            cellText = BOX_TEXT;
            cellTextColor = BOX_TEXT_COLOR;
        } else if (whoMovableInTile == IS_NOBODY) {
            cellText = NOBODY_TEXT;
            cellTextColor = NOBODY_TEXT_COLOR;
        }
        guiValues = new GuiDefaultConstants(cellBackgroundColor, cellTextColor, cellText);
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

    @Override
    public String toString() {
        return
                GameEventOneTileSokobanChangeable.class.getSimpleName()
                        // getClass().getSimpleName()
                        + "{" +
                        (super.toString().equals(getClass().getName() + "@" + Integer.toHexString(hashCode()))
                                ? ""
                                : ("{" + super.toString() + "}, ")
                        ) +
                "whoPersistentInTile=" + whoPersistentInTile +
                ", whoMovableInTile=" + whoMovableInTile +
                '}';
    }
}

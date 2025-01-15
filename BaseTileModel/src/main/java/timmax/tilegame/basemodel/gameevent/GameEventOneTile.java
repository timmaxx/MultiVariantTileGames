package timmax.tilegame.basemodel.gameevent;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;
import timmax.tilegame.visualization.GuiCellValues;

public abstract class GameEventOneTile extends GameEvent {
    private XYCoordinate xyCoordinate;

    //  ToDo:   Было-бы лучше вынести из этого класса логику визуализации.
    protected GuiCellValues guiCellValues;

    public GameEventOneTile() {
    }

    public GameEventOneTile(XYCoordinate xyCoordinate) {
        this.xyCoordinate = xyCoordinate;
    }

    public XYCoordinate getXyCoordinate() {
        return xyCoordinate;
    }

    public GuiCellValues getGuiCellValues() {
        return guiCellValues;
    }

    @Override
    public String toString() {
        return
                GameEventOneTile.class.getSimpleName()
                        // getClass().getSimpleName()
                        + "{" +
                        (super.toString().equals(getClass().getName() + "@" + Integer.toHexString(hashCode()))
                                ? ""
                                : ("{" + super.toString() + "}, ")
                        ) +
                        "xyCoordinate=" + xyCoordinate +
                        ", guiCellValues=" + guiCellValues +
                        '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(xyCoordinate);
        out.writeObject(guiCellValues);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        xyCoordinate = (XYCoordinate) in.readObject();
        guiCellValues = (GuiCellValues) in.readObject();
    }
}

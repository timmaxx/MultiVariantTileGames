package timmax.tilegame.basemodel.gameevent;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.scene.paint.Color;

import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;
import timmax.tilegame.basemodel.protocol.server_client.GuiDefaultConstants;

public abstract class GameEventOneTile extends GameEvent {
    private XYCoordinate xyCoordinate;

    //  Поле нужно для визуализации. И сейчас оно добавлено сюда для работы универсального клиента.
    //  Но:
    //  ToDo:   Было-бы лучше вынести из этого класса логику визуализации.
    protected GuiDefaultConstants guiValues;

    public GameEventOneTile() {
    }

    public GameEventOneTile(XYCoordinate xyCoordinate) {
        this.xyCoordinate = xyCoordinate;
    }

    public XYCoordinate getXyCoordinate() {
        return xyCoordinate;
    }

    public Color getCellBackgroundColor() {
        return guiValues.getDefaultCellBackgroundColor();
    }

    public Color getCellTextColor() {
        return guiValues.getDefaultCellTextColor();
    }

    public String getCellText() {
        return guiValues.getDefaultCellTextValue();
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
                        ", guiValues=" + guiValues +
                        '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(xyCoordinate);
        out.writeObject(guiValues);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        xyCoordinate = (XYCoordinate) in.readObject();
        guiValues = (GuiDefaultConstants) in.readObject();
    }
}

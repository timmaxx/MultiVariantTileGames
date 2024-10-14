package timmax.tilegame.basemodel.gamecommand;

import timmax.tilegame.basemodel.gameobject.XYCoordinate;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public abstract class GameCommandOneTile extends GameCommand {
    private XYCoordinate xyCoordinate;

    public GameCommandOneTile() {
        super();
    }

    public GameCommandOneTile(XYCoordinate xyCoordinate) {
        this.xyCoordinate = xyCoordinate;
    }

    public XYCoordinate getXYCoordinate() {
        return xyCoordinate;
    }

    @Override
    public String toString() {
        return "GameCommandOneTile{" +
                "xyCoordinate=" + xyCoordinate +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(xyCoordinate);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        xyCoordinate = (XYCoordinate) in.readObject();
    }
}

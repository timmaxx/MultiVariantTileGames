package timmax.tilegame.basemodel.gameevent;

import timmax.tilegame.basemodel.placement.primitives.WidthHeightSizes;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class GameEventNewGame extends GameEvent {
    private WidthHeightSizes widthHeightSizes;

    public GameEventNewGame() {
    }

    public GameEventNewGame(WidthHeightSizes widthHeightSizes) {
        this.widthHeightSizes = widthHeightSizes;
    }

    public WidthHeightSizes getWidthHeightSizes() {
        return widthHeightSizes;
    }

    @Override
    public String toString() {
        return "GameEventNewGame{" +
                "widthHeightSizes=" + widthHeightSizes +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(widthHeightSizes);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        widthHeightSizes = (WidthHeightSizes) in.readObject();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameEventNewGame that = (GameEventNewGame) o;

        return widthHeightSizes.equals(that.widthHeightSizes);
    }

    @Override
    public int hashCode() {
        return widthHeightSizes.hashCode();
    }
}

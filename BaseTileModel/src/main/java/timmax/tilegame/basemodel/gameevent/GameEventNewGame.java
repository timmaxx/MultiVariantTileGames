package timmax.tilegame.basemodel.gameevent;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class GameEventNewGame extends GameEvent {
    private int width;
    private int height;

    // ToDo: Было-бы лучше вынести из этого класса логику визуализации.
    //       Эти реквизиты уже перемещены в GameType, но пока в этом классе не удалены.
    // private boolean isThereCellSettingDefault;

    public GameEventNewGame() {
    }

    public GameEventNewGame(int width, int height) {
        this.width = width;
        this.height = height;
        // this.isThereCellSettingDefault = true;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
/*
    public boolean isThereCellSettingDefault() {
        return isThereCellSettingDefault;
    }
*/
    @Override
    public String toString() {
        return "GameEventNewGame{" +
                "width=" + width +
                ", height=" + height +
//                ", isThereCellSettingDefault=" + isThereCellSettingDefault +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(width);
        out.writeInt(height);
//        out.writeBoolean(isThereCellSettingDefault);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        width = in.readInt();
        height = in.readInt();
//        isThereCellSettingDefault = in.readBoolean();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameEventNewGame that = (GameEventNewGame) o;

        if (width != that.width) return false;
        return height == that.height;
    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + height;
        return result;
    }
}

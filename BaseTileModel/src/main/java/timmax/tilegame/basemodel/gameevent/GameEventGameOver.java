package timmax.tilegame.basemodel.gameevent;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.GameMatchStatus;

public class GameEventGameOver extends GameEvent {
    private GameMatchStatus gameMatchStatus;

    public GameEventGameOver() {
    }

    public GameEventGameOver(GameMatchStatus gameMatchStatus) {
        this.gameMatchStatus = gameMatchStatus;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(gameMatchStatus);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        gameMatchStatus = (GameMatchStatus) in.readObject();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameEventGameOver that = (GameEventGameOver) o;

        return gameMatchStatus == that.gameMatchStatus;
    }

    @Override
    public int hashCode() {
        return gameMatchStatus.hashCode();
    }

    @Override
    public String toString() {
        return
                GameEventGameOver.class.getSimpleName()
                        // getClass().getSimpleName()
                        + "{" +
                        (super.toString().equals(getClass().getName() + "@" + Integer.toHexString(hashCode()))
                                ? ""
                                : ("{" + super.toString() + "}, ")
                        ) +
                        "gameMatchStatus=" + gameMatchStatus +
                        '}';
    }
}

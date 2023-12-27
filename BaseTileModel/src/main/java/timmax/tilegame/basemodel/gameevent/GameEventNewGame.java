package timmax.tilegame.basemodel.gameevent;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.transport.TransportOfServer;

public class GameEventNewGame extends GameEvent {
    private int width;
    private int height;

    public GameEventNewGame() {
    }

    public GameEventNewGame(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public <T> void executeOnServer(TransportOfServer<T> transportOfServer, T clientId) {
        System.out.println("    onNewGame");
        transportOfServer.getModelOfServer().createNewGame();
    }

    @Override
    public String toString() {
        return "GameEventNewGame{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(width);
        out.writeInt(height);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        width = in.readInt();
        height = in.readInt();
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

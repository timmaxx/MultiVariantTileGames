package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.transport.TransportOfController;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class TransportPackageOfServer92CreateNewGame<T> extends TransportPackageOfServer<T> {
    private String viewId;
    private GameEvent gameEvent;


    public TransportPackageOfServer92CreateNewGame() {
        super();
    }

    public TransportPackageOfServer92CreateNewGame(String viewId, GameEvent gameEvent) {
        this();
        this.viewId = viewId;
        this.gameEvent = gameEvent;
    }

    @Override
    public void execute(TransportOfController<T> transportOfModel) {
        System.out.println("  onGameEvent");

        System.out.println("    viewId = " + viewId);
        System.out.println("    gameEvent = " + gameEvent);
    }

    @Override
    public String toString() {
        return "TransportPackageOfServer92CreateNewGame{" +
                "viewId='" + viewId + '\'' +
                ", gameEvent=" + gameEvent +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(viewId);
        out.writeObject(gameEvent);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        viewId = (String) in.readObject();
        gameEvent = (GameEvent) in.readObject();
    }
}
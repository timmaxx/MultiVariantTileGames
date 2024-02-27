package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.baseview.View;
import timmax.tilegame.basemodel.protocol.client.IModelOfClient;

public class EventOfServer92GameEvent extends EventOfServer {
    private String viewName;
    private GameEvent gameEvent;

    public EventOfServer92GameEvent() {
        super();
    }

    public EventOfServer92GameEvent(String viewName, GameEvent gameEvent) {
        this();
        this.viewName = viewName;
        this.gameEvent = gameEvent;
    }

    @Override
    public void executeOnClient(IModelOfClient iModelOfClient) {
        logger.debug("  onGameEvent");
        logger.debug("    viewName = {}", viewName);
        View view = iModelOfClient.getLocalClientState().getMapOfViewName_View().get(viewName);
        view.update(gameEvent);
    }

    @Override
    public String toString() {
        return "EventOfServer92GameEvent{" +
                "viewName='" + viewName + '\'' +
                ", gameEvent=" + gameEvent +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(viewName);
        out.writeObject(gameEvent);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        viewName = (String) in.readObject();
        gameEvent = (GameEvent) in.readObject();
    }
}

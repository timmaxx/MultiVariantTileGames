package timmax.tilegame.transport;

import java.util.LinkedList;
import java.util.Queue;

import timmax.tilegame.basemodel.gamecommand.GameCommand;

public abstract class GameCommandQueue<T> {
    protected Queue<GameCommand> commandQueue = new LinkedList<>();
    protected TransportOfServer<T> transportOfServer;

    public GameCommandQueue(TransportOfServer<T> transportOfServer) {
        this.transportOfServer = transportOfServer;
    }

    public boolean add(GameCommand gameCommand, T clientId) {
        boolean result = commandQueue.add(gameCommand);
        if (!result) {
            return false;
        }
        transport(clientId);
        return true;
    }

    public GameCommand remove() {
        return commandQueue.remove();
    }

    public int size() {
        return commandQueue.size();
    }

    private void transport(T clientId) {
        while (size() != 0) {
            GameCommand gameCommand = remove();
            whatToDoWithCommand(gameCommand, clientId);
        }
    }

    protected abstract void whatToDoWithCommand(GameCommand gameCommand, T clientId);
}

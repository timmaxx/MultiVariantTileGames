package timmax.tilegame.transport;

import timmax.tilegame.basemodel.gamecommand.GameCommand;

public class GameCommandQueueOfController<T> extends GameCommandQueue<T> {
    public GameCommandQueueOfController(TransportOfServer<T> transportOfServer) {
        super(transportOfServer);
    }

    @Override
    protected void whatToDoWithCommand(GameCommand gameCommand, T clientId) {
    }
}

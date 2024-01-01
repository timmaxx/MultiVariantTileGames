package timmax.tilegame.transport;

import timmax.tilegame.basemodel.gamecommand.GameCommand;

public class GameCommandQueueOfModel<T> extends GameCommandQueue<T> {
    public GameCommandQueueOfModel(TransportOfServer<T> transportOfServer) {
        super(transportOfServer);
    }

    @Override
    protected void whatToDoWithCommand(GameCommand gameCommand, T clientId) {
        gameCommand.executeOnServer(transportOfServer, clientId);
    }
}

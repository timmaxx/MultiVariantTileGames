package timmax.tilegame.transport;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.gamecommand.GameCommand;

public class GameCommandQueueOfModel extends GameCommandQueue {
    public GameCommandQueueOfModel( BaseModel baseModel) {
        super( baseModel);
    }

    @Override
    protected void whatToDoWithCommand( GameCommand gameCommand) {
        gameCommand.execute( baseModel);
    }
}
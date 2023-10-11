package timmax.tilegame.transport;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.gamecommand.GameCommand;

public class GameCommandQueueOfController extends GameCommandQueue {
    public GameCommandQueueOfController( BaseModel baseModel) {
        super( baseModel);
    }

    @Override
    protected void whatToDoWithCommand( GameCommand gameCommand) {
        baseModel.addCommandIntoQueue( gameCommand);
    }
}
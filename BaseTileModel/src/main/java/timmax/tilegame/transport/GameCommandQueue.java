package timmax.tilegame.transport;

import java.util.LinkedList;
import java.util.Queue;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.gamecommand.GameCommand;

public abstract class GameCommandQueue {
    protected Queue< GameCommand> commandQueue = new LinkedList< >( );
    protected BaseModel baseModel;


    public GameCommandQueue( BaseModel baseModel) {
        this.baseModel = baseModel;
    }

    public boolean add( GameCommand gameCommand) {
        boolean result = commandQueue.add( gameCommand);
        transport( );
        return result;
    }

    public GameCommand remove( ) {
        return commandQueue.remove( );
    }

    public int size( ) {
        return commandQueue.size( );
    }

    private void transport( ) {
        while ( size( ) != 0) {
            GameCommand gameCommand = remove( );
            whatToDoWithCommand( gameCommand);
        }
    }

    protected abstract void whatToDoWithCommand( GameCommand gameCommand);
}
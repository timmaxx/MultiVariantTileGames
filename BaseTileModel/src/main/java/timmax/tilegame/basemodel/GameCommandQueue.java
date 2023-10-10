package timmax.tilegame.basemodel;

import java.util.LinkedList;
import java.util.Queue;

import timmax.tilegame.basemodel.gamecommand.GameCommand;

public class GameCommandQueue {
    Queue< GameCommand> commandQueue = new LinkedList< >( );


    public boolean add( GameCommand gameCommand) {
        return commandQueue.add( gameCommand);
    }

    public GameCommand remove( ) {
        return commandQueue.remove( );
    }

    public int size( ) {
        return commandQueue.size( );
    }
}
package timmax.tilegame.game.sokoban.model.gamecommand;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.gamecommand.GameCommand;
import timmax.tilegame.basemodel.tile.Direction;
import timmax.tilegame.transport.TransportOfServer;

import timmax.tilegame.game.sokoban.model.SokobanModel;

public class GameCommandSokobanMove extends GameCommand {
    private /*final*/ Direction direction;

    public GameCommandSokobanMove() {
        super();
    }

    public GameCommandSokobanMove(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public <T> void executeOnServer(TransportOfServer<T> transportOfServer, T clientId) {
        ((SokobanModel<T>) transportOfServer).move(direction);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(direction);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        direction = (Direction) in.readObject();
    }
}

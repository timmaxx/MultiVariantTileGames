package timmax.tilegame.basemodel.gamecommand;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.protocol.server.IGameMatch;

public abstract class GameCommand implements Externalizable {
    public abstract void executeOnServer(IGameMatch iGameMatch);

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    }
}

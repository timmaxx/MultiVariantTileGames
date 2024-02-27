package timmax.tilegame.basemodel.gamecommand;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import timmax.tilegame.basemodel.protocol.server.IModelOfServer;

public abstract class GameCommand implements Externalizable {
    protected static final Logger logger = LoggerFactory.getLogger(GameCommand.class);

    public abstract void executeOnServer(IModelOfServer modelOfServer);

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    }
}

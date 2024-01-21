package timmax.tilegame.basemodel.gamecommand;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.transport.TransportOfServer;

public abstract class GameCommand implements Externalizable {
    public abstract <ClientId> void executeOnServer(TransportOfServer<ClientId> transportOfServer, ClientId clientId);


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    }
}

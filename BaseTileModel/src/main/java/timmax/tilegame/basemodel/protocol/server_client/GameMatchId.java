package timmax.tilegame.basemodel.protocol.server_client;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class GameMatchId implements Externalizable, IGameMatchX {
    private String id;

    public GameMatchId() {
    }

    public GameMatchId(String id) {
        this();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean isNullOrEmpty() {
        return id == null || id.equals("");
    }
    
    @Override
    public String toString() {
        return "GameMatchId{" +
                "id='" + id + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(id);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = (String) in.readObject();
    }
}

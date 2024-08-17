package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Map;

public class EventOfServer71StartGameMatch extends EventOfServer {
    private int width;
    private int height;
    private Map<String, Integer> paramsOfModelValueMap;

    public EventOfServer71StartGameMatch() {
        super();
    }

    public EventOfServer71StartGameMatch(int width, int height, Map<String, Integer> paramsOfModelValueMap) {
        this();
        this.width = width;
        this.height = height;
        this.paramsOfModelValueMap = paramsOfModelValueMap;
    }

    // class EventOfServer
    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        localClientStateAutomaton.startGameMatch(width, height, paramsOfModelValueMap);
    }

    // class Object
    @Override
    public String toString() {
        return "EventOfServer71StartGameMatch{" +
                "width=" + width +
                ", height=" + height +
                ", paramsOfModelValueMap=" + paramsOfModelValueMap +
                '}';
    }

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(width);
        out.writeInt(height);
        out.writeObject(paramsOfModelValueMap);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        width = in.readInt();
        height = in.readInt();
        // ToDo: Избавиться от "Warning:(55, 33) Unchecked cast: 'java.lang.Object' to 'java.util.Map<java.lang.String,java.lang.Integer>'"
        paramsOfModelValueMap = (Map<String, Integer>) in.readObject();
    }
}

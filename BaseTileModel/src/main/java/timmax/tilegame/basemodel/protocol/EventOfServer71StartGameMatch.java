package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Map;

public class EventOfServer71StartGameMatch extends EventOfServer {
    private Map<String, Integer> paramsOfModelValueMap;

    public EventOfServer71StartGameMatch() {
        super();
    }

    public EventOfServer71StartGameMatch(Map<String, Integer> paramsOfModelValueMap) {
        this();
        this.paramsOfModelValueMap = paramsOfModelValueMap;
    }

    // class EventOfServer
    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        localClientStateAutomaton.startGameMatch(paramsOfModelValueMap);
    }

    // class Object
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "paramsOfModelValueMap=" + paramsOfModelValueMap +
                '}';
    }

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(paramsOfModelValueMap);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        // ToDo: Избавиться от "Warning:(46, 33) Unchecked cast: 'java.lang.Object' to 'java.util.Map<java.lang.String,java.lang.Integer>'"
        paramsOfModelValueMap = (Map<String, Integer>) in.readObject();
    }
}

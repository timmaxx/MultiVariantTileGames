package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Map;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient71SetGameMatchPlaying<ClientId> extends EventOfClient<ClientId> {
    private Map<String, Integer> paramsOfModelValueMap;

    public EventOfClient71SetGameMatchPlaying() {
        super();
    }

    public EventOfClient71SetGameMatchPlaying(Map<String, Integer> paramsOfModelValueMap) {
        this();
        this.paramsOfModelValueMap = paramsOfModelValueMap;
    }

    @Override
    public void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton, ClientId clientId) {
        remoteClientStateAutomaton.setParamsOfModelValueMap(paramsOfModelValueMap);
        remoteClientStateAutomaton.setGameMatchPlaying(true);
    }

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
        // ToDo: Избавиться от "Warning:(44, 33) Unchecked cast: 'java.lang.Object' to 'java.util.Map<java.lang.String,java.lang.Integer>'"
        paramsOfModelValueMap = (Map<String, Integer>) in.readObject();
    }
}

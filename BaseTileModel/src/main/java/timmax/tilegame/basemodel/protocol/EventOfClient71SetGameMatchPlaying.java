package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Map;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient71SetGameMatchPlaying<ClientId> extends EventOfClient<ClientId> {
    private Map<String, Integer> mapOfParamsOfModelValue;

    public EventOfClient71SetGameMatchPlaying() {
        super();
    }

    public EventOfClient71SetGameMatchPlaying(Map<String, Integer> mapOfParamsOfModelValue) {
        this();
        this.mapOfParamsOfModelValue = mapOfParamsOfModelValue;
    }

    @Override
    public void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton, ClientId clientId) {
        logger.debug("  onSetGameMatchPlaying");
        remoteClientStateAutomaton.setMapOfParamsOfModelValue(mapOfParamsOfModelValue);
        remoteClientStateAutomaton.setGameMatchPlaying(true);
    }

    @Override
    public String toString() {
        return "EventOfClient71SetGameMatchPlaying{" +
                "mapOfParamsOfModelValue=" + mapOfParamsOfModelValue +
                '}';
    }

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(mapOfParamsOfModelValue);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        mapOfParamsOfModelValue = (Map<String, Integer>) in.readObject();
    }
}

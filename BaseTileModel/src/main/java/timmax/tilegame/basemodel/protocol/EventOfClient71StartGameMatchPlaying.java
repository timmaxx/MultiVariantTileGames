package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Map;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient71StartGameMatchPlaying<ClientId> extends EventOfClient<ClientId> {
    private Map<String, Integer> mapOfParamsOfModelValue;

    public EventOfClient71StartGameMatchPlaying() {
        super();
    }

    public EventOfClient71StartGameMatchPlaying(Map<String, Integer> mapOfParamsOfModelValue) {
        this();
        this.mapOfParamsOfModelValue = mapOfParamsOfModelValue;
    }

    @Override
    public void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientState) {
        logger.debug("  onStartGameMatchPlaying");
        remoteClientState.setMapOfParamsOfModelValue(mapOfParamsOfModelValue);
        remoteClientState.setGameIsPlaying(true);
    }

    @Override
    public String toString() {
        return "EventOfClient71StartGameMatchPlaying{" +
                "mapOfParamsOfModelValue=" + mapOfParamsOfModelValue +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(mapOfParamsOfModelValue);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        mapOfParamsOfModelValue = (Map<String, Integer>) in.readObject();
    }
}

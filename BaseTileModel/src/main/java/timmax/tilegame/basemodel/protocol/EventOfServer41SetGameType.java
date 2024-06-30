package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server.GameType;

public class EventOfServer41SetGameType extends EventOfServer {
    private String modelOfServerDescriptorGameTypeName;

    public EventOfServer41SetGameType() {
        super();
    }

    public EventOfServer41SetGameType(String modelOfServerDescriptorGameTypeName) {
        this();
        this.modelOfServerDescriptorGameTypeName = modelOfServerDescriptorGameTypeName;
    }

    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        GameType gameType =
                localClientStateAutomaton
                        .getGameTypeSet()
                        .stream()
                        .filter(x -> x.getGameName().equals(modelOfServerDescriptorGameTypeName))
                        .findAny()
                        .orElse(null);

        localClientStateAutomaton.setGameType(gameType);
        // ToDo: Разобраться, для чего нужно это?
        // localClientStateAutomaton.getGameMatchSet();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "modelOfServerDescriptorGameTypeName='" + modelOfServerDescriptorGameTypeName + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(modelOfServerDescriptorGameTypeName);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        modelOfServerDescriptorGameTypeName = (String) in.readObject();
    }
}

package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient41SetGameType<ClientId> extends EventOfClient<ClientId> {
    private String modelOfServerDescriptorGameTypeName;

    public EventOfClient41SetGameType() {
        super();
    }

    public EventOfClient41SetGameType(String modelOfServerDescriptorGameTypeName) {
        this();
        this.modelOfServerDescriptorGameTypeName = modelOfServerDescriptorGameTypeName;
    }

    @Override
    public void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton, ClientId clientId) {
        if (modelOfServerDescriptorGameTypeName == null) {
            logger.error("Client sent empty name of model classes.");
            remoteClientStateAutomaton.forgetGameType();
            return;
        }
        // От клиента поступило символическое имя типа игры (оно должно быть одно из тех, которые ему направлялись множеством).

        GameType gameType = remoteClientStateAutomaton
                .getGameTypeSet()
                .stream()
                // В том перечне ищется modelOfServerDescriptor с таким-же именем:
                .filter(x -> x.getGameName().equals(modelOfServerDescriptorGameTypeName))
                .findAny()
                .orElse(null);

        remoteClientStateAutomaton.setGameType(gameType);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "modelOfServerDescriptorGameTypeName='" + modelOfServerDescriptorGameTypeName + '\'' +
                '}';
    }

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(modelOfServerDescriptorGameTypeName);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        modelOfServerDescriptorGameTypeName = (String) in.readObject();
    }
}

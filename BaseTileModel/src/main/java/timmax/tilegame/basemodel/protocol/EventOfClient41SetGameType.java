package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
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
    public void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientState) {
        logger.debug("  onGameTypeSelect");
        logger.debug("  modelOfServerDescriptorGameTypeName = {}", modelOfServerDescriptorGameTypeName);
        if (modelOfServerDescriptorGameTypeName == null) {
            logger.error("Client sent empty name of model classes.");
            remoteClientState.forgetGameType();
            return;
        }
        // От клиента поступило символическое имя типа игры (оно должно быть одно из тех, которые ему направлялись множеством).

        //  ToDo: Код в следующем после этого блоке предпочтительнее, но он не компилируется...
//
/*
        ModelOfServerDescriptor modelOfServerDescriptor = null;
        for (int i = 0; i < remoteClientState.getGameTypeSet().size(); i++) {
            modelOfServerDescriptor = remoteClientState.getGameTypeSet().stream().toList().get(i);
            if (modelOfServerDescriptor.getGameName().equals(modelOfServerDescriptorGameTypeName)) {
                break;
            }
        }
*/
//
        ModelOfServerDescriptor modelOfServerDescriptor = remoteClientState
                .getGameTypeSet()
                .stream()
                // В том перечне ищется modelOfServerDescriptor с таким-же именем:
                .filter(x -> x.getGameName().equals(modelOfServerDescriptorGameTypeName))
                .findAny()
                .orElse(null);

        remoteClientState.setGameType(modelOfServerDescriptor);
    }

    @Override
    public String toString() {
        return "EventOfClient41SetGameType{" +
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

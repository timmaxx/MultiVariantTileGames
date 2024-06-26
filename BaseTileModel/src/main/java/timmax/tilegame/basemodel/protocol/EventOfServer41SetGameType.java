package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;

public class EventOfServer41SetGameType<Model> extends EventOfServer<Model> {
    private String modelOfServerDescriptorGameTypeName;

    public EventOfServer41SetGameType() {
        super();
    }

    public EventOfServer41SetGameType(String modelOfServerDescriptorGameTypeName) {
        this();
        this.modelOfServerDescriptorGameTypeName = modelOfServerDescriptorGameTypeName;
    }

    @Override
    public void executeOnClient(LocalClientStateAutomaton<Model> localClientStateAutomaton) {
        logger.debug("  onGameTypeSelect");
        //  ToDo: Код, в следующем после этого блока, предпочтительнее, но он не компилируется
        //        (перестал после замены класса LocalClientState на ClientStateAutomaton)...
        ModelOfServerDescriptor modelOfServerDescriptor = null;
        for (int i = 0; i < localClientStateAutomaton.getGameTypeSet().size(); i++) {
            modelOfServerDescriptor = localClientStateAutomaton.getGameTypeSet().stream().toList().get(i);
            if (modelOfServerDescriptor.getGameName().equals(modelOfServerDescriptorGameTypeName)) {
                break;
            }
        }
//

/*
        ModelOfServerDescriptor modelOfServerDescriptor =
                localClientStateAutomaton
                .getGameTypeSet()
                .stream()
                .filter(x -> x.getGameName().equals(modelOfServerDescriptorGameTypeName))
                .findAny()
                .orElse(null);
*/

        localClientStateAutomaton.setGameType(modelOfServerDescriptor);
        localClientStateAutomaton.getGameMatchSet();
    }

    @Override
    public String toString() {
        return "EventOfServer41SetGameType{" +
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

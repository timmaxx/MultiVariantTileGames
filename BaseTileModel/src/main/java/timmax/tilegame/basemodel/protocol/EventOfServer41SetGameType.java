package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.client.IModelOfClient;

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
    public void executeOnClient(IModelOfClient iModelOfClient) {
        logger.debug("  onGameTypeSelect");

//  ToDo: Код в следующем блоке предпочтительнее, но он не компилируется
//        (перестал после замены класса LocalClientState на ClientStateAutomaton)...
        ModelOfServerDescriptor modelOfServerDescriptor = null;
        for (int i = 0; i < iModelOfClient.getLocalClientState().getGameTypeSet().size(); i++) {
            modelOfServerDescriptor = ((ModelOfServerDescriptor)(iModelOfClient.getLocalClientState().getGameTypeSet().stream().toList().get(i)));
            if (modelOfServerDescriptor.getGameName().equals(modelOfServerDescriptorGameTypeName)) {
                break;
            }
        }
//

/*
        ModelOfServerDescriptor modelOfServerDescriptor =
                iModelOfClient
                .getLocalClientState()
                .getGameTypeSet()
                .stream()
                .filter(x -> x.getGameName().equals(modelOfServerDescriptorGameTypeName))
                .findAny()
                .orElse(null);
*/

        iModelOfClient.getLocalClientState().setGameType(modelOfServerDescriptor);
        iModelOfClient.getGameMatchSet();
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

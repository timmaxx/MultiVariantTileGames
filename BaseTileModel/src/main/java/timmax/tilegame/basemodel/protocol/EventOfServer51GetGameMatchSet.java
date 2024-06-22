package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Set;

import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.basemodel.protocol.client.IModelOfClient;

public class EventOfServer51GetGameMatchSet extends EventOfServer {
    private Set<InstanceIdOfModel> setOfInstanceIdOfModel;

    public EventOfServer51GetGameMatchSet() {
        super();
    }

    public EventOfServer51GetGameMatchSet(Set<InstanceIdOfModel> setOfInstanceIdOfModel) {
        this();
        this.setOfInstanceIdOfModel = setOfInstanceIdOfModel;
    }

    @Override
    public void executeOnClient(IModelOfClient iModelOfClient) {
        logger.debug("  onGetGameMatchSet");

        // Ниже сигнатура setGameMatchSet:
        // void setGameMatchSet(Set<Model> setOfServerBaseModel)
        // но
        // Set<InstanceIdOfModel> setOfInstanceIdOfModel
        // Поэтому Set<InstanceIdOfModel> нужно преобразовать в Set<IModelOfClient>
        iModelOfClient
                .getLocalClientState()
                .setGameMatchSet(setOfInstanceIdOfModel);
    }

    @Override
    public String toString() {
        return "EventOfServer51GetGameMatchSet{" +
                "setOfInstanceIdOfModel=" + setOfInstanceIdOfModel +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(setOfInstanceIdOfModel);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        // ToDo: Исправить Warning:(48, 35) Unchecked cast: 'java.lang.Object' to 'java.util.List<timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel>'
        //       Например как в readExternal в EventOfServer31GetGameTypeSet
        setOfInstanceIdOfModel = (Set<InstanceIdOfModel>) in.readObject();
    }
}

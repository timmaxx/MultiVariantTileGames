package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Set;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;

public class EventOfServer51SetGameMatchSet extends EventOfServer {
    private Set<InstanceIdOfModel> setOfInstanceIdOfModel;

    public EventOfServer51SetGameMatchSet() {
        super();
    }

    public EventOfServer51SetGameMatchSet(Set<InstanceIdOfModel> setOfInstanceIdOfModel) {
        this();
        this.setOfInstanceIdOfModel = setOfInstanceIdOfModel;
    }

    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        logger.debug("  onGetGameMatchSet");
        localClientStateAutomaton.setGameMatchSet(setOfInstanceIdOfModel);
    }

    @Override
    public String toString() {
        return "EventOfServer51SetGameMatchSet{" +
                "setOfInstanceIdOfModel=" + setOfInstanceIdOfModel +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(setOfInstanceIdOfModel);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        // ToDo: Исправить Warning:(45, 34) Unchecked cast: 'java.lang.Object' to 'java.util.Set<timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel>'
        //       Например как в readExternal в EventOfServer31SetGameTypeSet
        setOfInstanceIdOfModel = (Set<InstanceIdOfModel>) in.readObject();
    }
}

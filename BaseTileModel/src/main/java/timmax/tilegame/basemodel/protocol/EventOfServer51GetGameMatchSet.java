package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Set;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;

// ToDo: Почему ...51Get...? Ведь в
//       void executeOnClient(...)
//       в последней строке
//       ...setGameMatchSet(...);
//       Т.е. нужно переименовать класс в ...51Set...
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
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        logger.debug("  onGetGameMatchSet");
        localClientStateAutomaton.setGameMatchSet(setOfInstanceIdOfModel);
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
        // ToDo: Исправить Warning:(50, 34) Unchecked cast: 'java.lang.Object' to 'java.util.Set<timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel>'
        //       Например как в readExternal в EventOfServer31SetGameTypeSet
        setOfInstanceIdOfModel = (Set<InstanceIdOfModel>) in.readObject();
    }
}

package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Map;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient71SetGameMatchIsPlaying extends EventOfClient {
    private Map<String, Integer> paramsOfModelValueMap;

    public EventOfClient71SetGameMatchIsPlaying() {
        super();
    }

    public EventOfClient71SetGameMatchIsPlaying(Map<String, Integer> paramsOfModelValueMap) {
        this();
        this.paramsOfModelValueMap = paramsOfModelValueMap;
    }

    // ToDo: Этот класс (и все последующие и некоторые предыдущие) уже может работать не только с
    //       RemoteClientStateAutomaton, но и с экземпляром GameMatch.
    //       Нужно пересмотреть архитектуру и передавать сюда GameMatch.
    @Override
    public void executeOnServer(RemoteClientStateAutomaton remoteClientStateAutomaton) {
        remoteClientStateAutomaton.getGameMatchX().setParamsOfModelValueMap(paramsOfModelValueMap);
        remoteClientStateAutomaton.setGameMatchIsPlaying(true);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "paramsOfModelValueMap=" + paramsOfModelValueMap +
                '}';
    }

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(paramsOfModelValueMap);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        // ToDo: Избавиться от "Warning:(47, 33) Unchecked cast: 'java.lang.Object' to 'java.util.Map<java.lang.String,java.lang.Integer>'"
        paramsOfModelValueMap = (Map<String, Integer>) in.readObject();
/*
        // Так не работает...
        Object object = in.readObject();
        if (object instanceof Map<String, Integer> objectMap) {
            paramsOfModelValueMap = (Map<String, Integer>)objectMap;
        } else {
            throw new RuntimeException("In ObjectInput there is no Map. But must be Map<String, Integer>.");
        }
*/
    }
}

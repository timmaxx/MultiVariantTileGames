package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Map;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient71StartGameMatch extends EventOfClient {
    private int width;
    private int height;
    private Map<String, Integer> paramsOfModelValueMap;

    public EventOfClient71StartGameMatch() {
        super();
    }

    public EventOfClient71StartGameMatch(int width, int height, Map<String, Integer> paramsOfModelValueMap) {
        this();
        this.width = width;
        this.height = height;
        this.paramsOfModelValueMap = paramsOfModelValueMap;
    }

    // ToDo: Этот класс (и все последующие и некоторые предыдущие) уже может работать не только с
    //       RemoteClientStateAutomaton, но и с экземпляром GameMatch.
    //       Нужно пересмотреть архитектуру и передавать сюда GameMatch.
    // class EventOfClient
    @Override
    public void executeOnServer(RemoteClientStateAutomaton remoteClientStateAutomaton) {
        // ToDo: Вызов спрятать во внутрь startGameMatch.
        remoteClientStateAutomaton.getGameMatchX().setParamsOfModelValueMap(width, height, paramsOfModelValueMap);
        remoteClientStateAutomaton.startGameMatch(width, height, paramsOfModelValueMap);
    }

    // class Object
    @Override
    public String toString() {
        return "EventOfClient71StartGameMatch{" +
                "width=" + width +
                ", height=" + height +
                ", paramsOfModelValueMap=" + paramsOfModelValueMap +
                '}';
    }

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(width);
        out.writeInt(height);
        out.writeObject(paramsOfModelValueMap);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        width = in.readInt();
        height = in.readInt();
        // ToDo: Избавиться от "Warning:(61, 33) Unchecked cast: 'java.lang.Object' to 'java.util.Map<java.lang.String,java.lang.Integer>'"
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

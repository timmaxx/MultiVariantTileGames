package timmax.tilegame.basemodel.protocol.server_client;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Map;

// DTO - Data Transfer Object
public class GameMatchDto implements Externalizable, IGameMatchX {
    private String id;
    private boolean isPlaying;
    private Map<String, Integer> paramsOfModelValueMap;

    public GameMatchDto() {
        super();
    }

    public GameMatchDto(String id, boolean isPlaying, Map<String, Integer> paramsOfModelValueMap) {
        this();
        this.id = id;
        this.isPlaying = isPlaying;
        setParamsOfModelValueMap(paramsOfModelValueMap);
    }

    public boolean isNullOrEmpty() {
        return id == null || id.equals("");
    }

    // class Object
    @Override
    public String toString() {
        return "GameMatchDto{" +
                "id='" + id + '\'' +
                ", isPlaying=" + isPlaying +
                ", paramsOfModelValueMap=" + paramsOfModelValueMap +
                '}';
    }

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(id);
        out.writeBoolean(isPlaying);
        out.writeObject(paramsOfModelValueMap);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = (String) in.readObject();
        isPlaying = in.readBoolean();
        // ToDo: Избавиться от "Warning:(53, 33) Unchecked cast: 'java.lang.Object' to 'java.util.Map<java.lang.String,java.lang.Integer>'"
        paramsOfModelValueMap = (Map<String, Integer>) in.readObject();
    }

    // interface IGameMatchXDto
    @Override
    public String getId() {
        return id;
    }

    @Override
    public int getWidth() {
        return paramsOfModelValueMap.get(PARAM_NAME_WIDTH);
    }

    @Override
    public int getHeight() {
        return paramsOfModelValueMap.get(PARAM_NAME_HEIGHT);
    }

    @Override
    public void setParamsOfModelValueMap(Map<String, Integer> paramsOfModelValueMap) {
        this.paramsOfModelValueMap = paramsOfModelValueMap;
    }

    @Override
    public boolean isPlaying() {
        return isPlaying;
    }

    @Override
    public Map<String, Integer> getParamsOfModelValueMap() {
        return paramsOfModelValueMap;
    }

    // interface IGameMatchX
    // ToDo: Удалить, т.к. вероятно метод не нужен. Да и этот метод противоречит концепции DTO.
    @Override
    public void start() {
        System.out.println("GameMatchDto :: void start()");
        System.out.println("ToDo: Удалить, т.к. вероятно метод не нужен. Да и этот метод противоречит концепции DTO.");
    }

    // ToDo: Удалить, т.к. вероятно метод не нужен. Да и этот метод противоречит концепции DTO.

    @Override
    public void resume() {
        System.out.println("GameMatchDto:: void resume()");
        System.out.println("ToDo: Удалить, т.к. вероятно метод не нужен. Да и этот метод противоречит концепции DTO.");
    }

}

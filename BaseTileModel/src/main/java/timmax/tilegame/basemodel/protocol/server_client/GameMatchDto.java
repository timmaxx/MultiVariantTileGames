package timmax.tilegame.basemodel.protocol.server_client;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Map;

// DTO - Data Transfer Object
public class GameMatchDto implements Externalizable, IGameMatchX {
    private String id;
    private int width;
    private int height;
    private boolean isPlaying;
    private Map<String, Integer> paramsOfModelValueMap;

    public GameMatchDto() {
        super();
    }

    public GameMatchDto(String id, int width, int height, boolean isPlaying, Map<String, Integer> paramsOfModelValueMap) {
        this();
        this.id = id;
        this.width = width;
        this.height = height;
        this.isPlaying = isPlaying;
        this.paramsOfModelValueMap = paramsOfModelValueMap;
    }

    public boolean isNullOrEmpty() {
        return id == null || id.equals("");
    }

    // class Object
    @Override
    public String toString() {
        return "GameMatchDto{" +
                "id='" + id + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", isPlaying=" + isPlaying +
                ", paramsOfModelValueMap=" + paramsOfModelValueMap +
                '}';
    }

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(id);
        out.writeInt(width);
        out.writeInt(height);
        out.writeBoolean(isPlaying);
        out.writeObject(paramsOfModelValueMap);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = (String) in.readObject();
        width = in.readInt();
        height = in.readInt();
        isPlaying = in.readBoolean();
        // ToDo: Избавиться от "Warning:(63, 33) Unchecked cast: 'java.lang.Object' to 'java.util.Map<java.lang.String,java.lang.Integer>'"
        paramsOfModelValueMap = (Map<String, Integer>) in.readObject();
    }

    // interface IGameMatchXDto
    @Override
    public String getId() {
        return id;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
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
    public void start(int width, int height, Map<String, Integer> paramsOfModelValueMap) {
        // throw new RuntimeException("GameMatchDto :: void startGameMatch(Map<String, Integer> mapOfParamsOfModelValue)");
        System.out.println("GameMatchDto :: void start(Map<String, Integer> mapOfParamsOfModelValue)");
        System.out.println("ToDo: Удалить, т.к. вероятно метод не нужен. Да и этот метод противоречит концепции DTO.");
    }

    // ToDo: Удалить, т.к. вероятно метод не нужен. Да и этот метод противоречит концепции DTO.
    @Override
    public void resume() {
        System.out.println("GameMatchDto:: void resume()");
        System.out.println("ToDo: Удалить, т.к. вероятно метод не нужен. Да и этот метод противоречит концепции DTO.");
    }

}

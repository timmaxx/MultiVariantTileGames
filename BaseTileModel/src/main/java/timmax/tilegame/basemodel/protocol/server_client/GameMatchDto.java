package timmax.tilegame.basemodel.protocol.server_client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timmax.tilegame.basemodel.GameMatchStatus;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Map;

// DTO - Data Transfer Object
public class GameMatchDto implements Externalizable, IGameMatchX {
    protected static final Logger logger = LoggerFactory.getLogger(GameMatchDto.class);

    private String id;
    private GameMatchStatus status;
    private Map<String, Integer> paramsOfModelValueMap;

    public GameMatchDto() {
        super();
    }

    public GameMatchDto(String id, GameMatchStatus status, Map<String, Integer> paramsOfModelValueMap) {
        this();
        this.id = id;
        this.status = status;
        this.paramsOfModelValueMap = paramsOfModelValueMap;
    }

    public boolean isNullOrEmpty() {
        return id == null || id.equals("");
    }

    public void setParamsOfModelValueMap(Map<String, Integer> paramsOfModelValueMap) {
        this.paramsOfModelValueMap = paramsOfModelValueMap;
    }

    // interface IGameMatchX
    @Override
    public String getId() {
        return id;
    }

    @Override
    public GameMatchStatus getStatus() {
        return status;
    }

    @Override
    public Map<String, Integer> getParamsOfModelValueMap() {
        return paramsOfModelValueMap;
    }

    @Override
    public int getWidth() {
        return paramsOfModelValueMap.get(PARAM_NAME_WIDTH);
    }

    @Override
    public int getHeight() {
        return paramsOfModelValueMap.get(PARAM_NAME_HEIGHT);
    }

    //  ToDo:   Удалить метод.
    //          Для DTO - этод метод не должен использоваться.
    @Override
    public void start(GameMatchExtendedDto gameMatchExtendedDto) {
        logger.info("Этот вывод не должен проявиться. Т.к. start() для DTO не должен вызываться! (gameMatchExtendedDto = {})", gameMatchExtendedDto);
    }

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(id);
        out.writeObject(status);
        out.writeObject(paramsOfModelValueMap);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = (String) in.readObject();
        status = (GameMatchStatus) in.readObject();
        // ToDo: Избавиться от "Warning:(88, 33) Unchecked cast: 'java.lang.Object' to 'java.util.Map<java.lang.String,java.lang.Integer>'"
        paramsOfModelValueMap = (Map<String, Integer>) in.readObject();
    }

    // class Object
    @Override
    public String toString() {
        return "GameMatchDto{" +
                "id='" + id + '\'' +
                ", status=" + status +
                ", paramsOfModelValueMap=" + paramsOfModelValueMap +
                '}';
    }
}

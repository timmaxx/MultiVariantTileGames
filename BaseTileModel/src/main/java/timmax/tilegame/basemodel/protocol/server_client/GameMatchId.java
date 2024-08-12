package timmax.tilegame.basemodel.protocol.server_client;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Map;

// ToDo: Сделать промежуточный абстрактый класс, реализующий interface IGameMatchX. А Классы GameMatch и GameMatchId
//       сделать его наследниками.
//       Классы GameMatch и GameMatchId имеют несколько одинаковых переменных и методов (в т.ч. см interface IGameMatchX)
public class GameMatchId implements Externalizable, IGameMatchX {
    private String id;
    private boolean isPlaying;
    private Map<String, Integer> paramsOfModelValueMap;

    // Нужен только потому, что implements Externalizable
    public GameMatchId() {
        super();
    }

    public GameMatchId(String id, boolean isPlaying, Map<String, Integer> paramsOfModelValueMap) {
        this();
        this.id = id;
        this.isPlaying = isPlaying;
        this.paramsOfModelValueMap = paramsOfModelValueMap;
    }

    public boolean isNullOrEmpty() {
        return id == null || id.equals("");
    }

    // class Object
    @Override
    public String toString() {
        return "GameMatchId{" +
                "id='" + id + '\'' +
                ", gameIsPlaying=" + isPlaying +
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
        paramsOfModelValueMap = (Map<String, Integer>) in.readObject();
    }

    // interface IGameMatchX
    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isPlaying() {
        // throw new RuntimeException("GameMatchId :: boolean getGameMatchIsPlaying()");
        // System.out.println("throw new RuntimeException(\"GameMatchId :: boolean getGameMatchIsPlaying()\");");
        System.out.println("GameMatchId :: boolean getGameMatchIsPlaying()");
        return isPlaying;
    }

    @Override
    public void startGameMatch(Map<String, Integer> mapOfParamsOfModelValue) {
        // throw new RuntimeException("GameMatchId :: void startGameMatch(Map<String, Integer> mapOfParamsOfModelValue)");
        System.out.println("throw new RuntimeException(\"GameMatchId :: void startGameMatch(Map<String, Integer> mapOfParamsOfModelValue)\");");
    }

    @Override
    public Map<String, Integer> getParamsOfModelValueMap() {
        return paramsOfModelValueMap;
    }
}

package timmax.tilegame.basemodel.protocol;

import java.util.Map;

public interface IModelOfServerDescriptor {
    String getGameName();
    int getCountOfGamers();
    Map<String, Integer> getMapOfParamsOfModel();
}

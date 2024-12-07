package timmax.tilegame.basemodel.protocol.server;

import java.util.HashMap;
import java.util.Map;

//  В текущем виде, класс не функционален.
//  Вместо него можно было-бы использовать и
//  Map<String, ParamOfModelDescription>
//  См. комменты к Pane07GameMatchSelected :: void updateOnSetGameMatch()
//  git 'edit ParamName_paramModelDescriptionMap (unlikely)'
public class ParamName_paramModelDescriptionMap extends HashMap<String, ParamOfModelDescription> {
    public ParamName_paramModelDescriptionMap() {
        super();
    }

    // ToDo: Упростить метод.
    //       Почему-то не получилось использовать this.stream().
    public Map<String, Integer> getParamsOfModelValueMap() {
        Map<String, Integer> paramsOfModelValueMap = new HashMap<>();
        for (Map.Entry<String, ParamOfModelDescription> entry : entrySet()) {
            paramsOfModelValueMap.put(entry.getKey(), entry.getValue().getDefaultValue());
        }
        return paramsOfModelValueMap;
    }
}

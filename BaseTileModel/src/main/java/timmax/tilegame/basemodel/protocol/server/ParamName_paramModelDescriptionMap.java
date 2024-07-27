package timmax.tilegame.basemodel.protocol.server;

import java.util.HashMap;

//  В текущем виде, класс не функционален.
//  Вместо него можно было-бы использовать и
//  Map<String, ParamOfModelDescription>
// См. комменты к Pane07GameMatchSelected :: void updateOnSetGameMatch()
public class ParamName_paramModelDescriptionMap extends HashMap<String, ParamOfModelDescription> {
    public ParamName_paramModelDescriptionMap() {
        super();
    }

/*
//  Не удачная попытка. Должна была использоваться в
//  Pane07GameMatchSelected :: void updateOnSetGameMatch()
    IControlSet getIControlSet(TransportOfClient transportOfClient) {
        return null;
    }
*/

}

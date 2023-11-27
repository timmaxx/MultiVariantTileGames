package timmax.tilegame.basemodel.gamecommand;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import timmax.tilegame.basemodel.BaseModel;

// В этом модуле применил аннотации Jackson.
// ToDo: Но лучше было-бы не использовать их (аннотации Jackson) здесь, а только в транспорте!
//       Но тогда там нужно в модулях транспорта использовать не аннотации, а вызовы методов.
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "type")
public abstract class GameCommand {
    public abstract void execute(BaseModel baseModel);
}
package timmax.tilegame.basemodel.gameevent;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

// В этом модуле применил аннотации Jackson.
// ToDo: Но лучше было-бы не использовать их (аннотации Jackson) здесь, а только в транспорте!
//       Но тогда там нужно в модулях транспорта использовать не аннотации, а вызовы методов.
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "type")
public abstract class GameEvent {
}
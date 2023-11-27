package timmax.tilegame.basemodel.gameevent;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import timmax.tilegame.basemodel.GameStatus;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

// В этом модуле применил аннотации Jackson.
// ToDo: Но лучше было-бы не использовать их (аннотации Jackson) здесь, а только в транспорте!
//       Но тогда там нужно в модулях транспорта использовать не аннотации, а вызовы методов.
public class GameEventGameOver extends GameEvent {
    private final GameStatus gameStatus;


    @JsonCreator(mode = PROPERTIES)
    public GameEventGameOver(
            @JsonProperty("gameStatus") GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
}
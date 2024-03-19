package timmax.tilegame.basemodel.gameevent;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import timmax.tilegame.basemodel.GameStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayName("GameEventGameOver ClientState")
public class GameEventGameOverTest {
    @Test
    @DisplayName("Not null is not equals null")
    public void notNullIsNotEqualsNull() {
        GameEvent gameEvent = new GameEventNewGame(2, 3);
        assertNotEquals(gameEvent, null);
    }

    @Test
    public void constructorOfGameStatusDefeat() {
        GameEventGameOver gameEvent = new GameEventGameOver(GameStatus.DEFEAT);
        assertEquals(gameEvent.toString(),"GameEventGameOver{gameStatus=DEFEAT}");
    }
}

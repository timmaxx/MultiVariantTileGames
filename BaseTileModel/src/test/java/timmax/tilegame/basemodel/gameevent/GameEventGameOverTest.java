package timmax.tilegame.basemodel.gameevent;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import timmax.tilegame.basemodel.gameobject.WidthHeightSizes;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayName("GameEventGameOver Test")
public class GameEventGameOverTest {
    @Test
    @DisplayName("Not null is not equals null")
    public void notNullIsNotEqualsNull() {
        GameEvent gameEvent = new GameEventNewGame(new WidthHeightSizes(2, 3));
        assertNotEquals(gameEvent, null);
    }
/*
    @Test
    public void constructor() {
        GameEventGameOver gameEvent = new GameEventGameOver(GameMatchStatus.DEFEAT);
        assertEquals(gameEvent.toString(),"GameEventGameOver{gameStatus=DEFEAT}");
        //assertEquals(gameEvent.getGameStatus(), GameMatchStatus.DEFEAT);
    }
*/
}
package timmax.tilegame.game.minesweeper.model.gameevent;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import timmax.tilegame.basemodel.gameevent.GameEventOneTile;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

public class GameEventOneTileOpenMine extends GameEventOneTile {
    @JsonCreator( mode = PROPERTIES)
    public GameEventOneTileOpenMine(
            @JsonProperty( "x") int x,
            @JsonProperty( "y") int y) {
        super( x, y);
    }
}
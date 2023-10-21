package timmax.tilegame.game.minesweeper.model.gameevent;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import timmax.tilegame.basemodel.gameevent.GameEventOneTile;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

public class GameEventOneTileChangeFlag extends GameEventOneTile {
    private final boolean isFlag;

    @JsonCreator( mode = PROPERTIES)
    public GameEventOneTileChangeFlag(
            @JsonProperty( "x") int x,
            @JsonProperty( "y") int y,
            @JsonProperty( "flag") boolean isFlag) // Странно, не isFlag, а flag
    {
        super( x, y);
        this.isFlag = isFlag;
    }

    public boolean isFlag( ) {
        return isFlag;
    }
}
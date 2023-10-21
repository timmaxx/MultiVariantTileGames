package timmax.tilegame.game.minesweeper.model.gameevent;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import timmax.tilegame.basemodel.gameevent.GameEventOneTile;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

public class GameEventOneTileOpenNoMine extends GameEventOneTile {
    private final int countOfMineNeighbors;


    @JsonCreator( mode = PROPERTIES)
    public GameEventOneTileOpenNoMine(
            @JsonProperty( "x") int x,
            @JsonProperty( "y") int y,
            @JsonProperty( "countOfMineNeighbors") int countOfMineNeighbors) {
        super( x, y);
        this.countOfMineNeighbors = countOfMineNeighbors;
    }

    public int getCountOfMineNeighbors( ) {
        return countOfMineNeighbors;
    }
}
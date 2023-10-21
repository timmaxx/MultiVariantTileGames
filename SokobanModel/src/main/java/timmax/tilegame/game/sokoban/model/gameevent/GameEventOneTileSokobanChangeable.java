package timmax.tilegame.game.sokoban.model.gameevent;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import timmax.tilegame.basemodel.gameevent.GameEventOneTile;

import timmax.tilegame.game.sokoban.model.gameobject.WhoMovableInTile;
import timmax.tilegame.game.sokoban.model.gameobject.WhoPersistentInTile;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

public class GameEventOneTileSokobanChangeable extends GameEventOneTile {
    private final WhoPersistentInTile whoPersistentInTile;
    private final WhoMovableInTile whoMovableInTile;


    @JsonCreator( mode = PROPERTIES)
    public GameEventOneTileSokobanChangeable(
            @JsonProperty( "x") int x,
            @JsonProperty( "y") int y,
            @JsonProperty( "whoPersistentInTile") WhoPersistentInTile whoPersistentInTile,
            @JsonProperty( "whoMovableInTile") WhoMovableInTile whoMovableInTile) {
        super( x, y);
        this.whoPersistentInTile = whoPersistentInTile;
        this.whoMovableInTile = whoMovableInTile;
    }

    public WhoPersistentInTile getWhoPersistentInTile( ) {
        return whoPersistentInTile;
    }

    public WhoMovableInTile getWhoMovableInTile( ) {
        return whoMovableInTile;
    }
}
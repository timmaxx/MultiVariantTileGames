package timmax.tilegame.game.sokoban.model.gameevent;

import timmax.tilegame.basemodel.gameevent.GameEventOneTile;

import timmax.tilegame.game.sokoban.model.gameobject.WhoMovableInTile;
import timmax.tilegame.game.sokoban.model.gameobject.WhoPersistentInTile;

public class GameEventOneTileSokobanChangeable extends GameEventOneTile {
    private final WhoPersistentInTile whoPersistentInTile;
    private final WhoMovableInTile whoMovableInTile;


    public GameEventOneTileSokobanChangeable(
            int x,
            int y,
            WhoPersistentInTile whoPersistentInTile,
            WhoMovableInTile whoMovableInTile) {
        super(x, y);
        this.whoPersistentInTile = whoPersistentInTile;
        this.whoMovableInTile = whoMovableInTile;
    }

    public WhoPersistentInTile getWhoPersistentInTile() {
        return whoPersistentInTile;
    }

    public WhoMovableInTile getWhoMovableInTile() {
        return whoMovableInTile;
    }
}
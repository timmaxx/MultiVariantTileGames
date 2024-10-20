package timmax.tilegame.game.sokoban.model.gameobject;

import timmax.tilegame.basemodel.gameobject.*;

import java.util.Set;

public class SokobanPlacement extends GameObjectsPlacementVerified {

    public SokobanPlacement(GameObjectsPlacementNotVerified gameObjectsPlacementNotVerified) {
        super(gameObjectsPlacementNotVerified, 0);
    }

    public int getCountOfPairHomesAndBoxes() {
        return getBoxes().size();
    }

    public WhoMovableInTile getWhoMovableInTile(XYCoordinate xyCoordinate) {
        return getBoxes().stream()
                .filter(sgoBox -> sgoBox.getXyCoordinate().equals(xyCoordinate))
                .map(sgoBox -> WhoMovableInTile.IS_BOX)
                .findAny()
                .orElse(getPlayer().getXyCoordinate().equals(xyCoordinate)
                        ? WhoMovableInTile.IS_PLAYER
                        : WhoMovableInTile.IS_NOBODY
                );
    }

    public WhoPersistentInTile getWhoPersistentInTile(XYCoordinate xyCoordinate) {
        return getWalls().stream()
                .filter(sgoWall -> sgoWall.getXyCoordinate().equals(xyCoordinate))
                .map(sgoWall -> WhoPersistentInTile.IS_WALL)
                .findAny()
                .orElse(
                        getHomes().stream()
                                .filter(sgoHome -> sgoHome.getXyCoordinate().equals(xyCoordinate))
                                .map(sgoHome -> WhoPersistentInTile.IS_HOME)
                                .findAny()
                                .orElse(WhoPersistentInTile.IS_EMPTY)
                );
    }

    public SGOPlayer getPlayer() {
        return getGameObjectSetFilteredByGameObjectClass(SGOPlayer.class)
                .stream()
                .findAny()
                .orElseThrow(() -> new RuntimeException("Player is not found."));
    }

    public Set<SGOBox> getBoxes() {
        return getGameObjectSetFilteredByGameObjectClass(SGOBox.class);
    }

    public Set<SGOWall> getWalls() {
        return getGameObjectSetFilteredByGameObjectClass(SGOWall.class);
    }

    public Set<SGOHome> getHomes() {
        return getGameObjectSetFilteredByGameObjectClass(SGOHome.class);
    }
}

package timmax.tilegame.game.sokoban.model.gameobject;

import timmax.tilegame.basemodel.gameobject.*;

import java.util.Set;
import java.util.stream.Collectors;

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
                .map(go -> (SGOPlayer)go)
                .findAny()
                //  ToDo:   Если null, то исключение нужно сгенерить.
                .orElse(null);
    }

    public Set<SGOBox> getBoxes() {
        return getGameObjectSetFilteredByGameObjectClass(SGOBox.class)
                .stream()
                .map(go -> (SGOBox)go)
                .collect(Collectors.toSet());
    }

    public Set<SGOWall> getWalls() {
        return getGameObjectSetFilteredByGameObjectClass(SGOWall.class)
                .stream()
                .map(go -> (SGOWall)go)
                .collect(Collectors.toSet());
    }

    public Set<SGOHome> getHomes() {
        return getGameObjectSetFilteredByGameObjectClass(SGOHome.class)
                .stream()
                .map(go -> (SGOHome)go)
                .collect(Collectors.toSet());
    }
}

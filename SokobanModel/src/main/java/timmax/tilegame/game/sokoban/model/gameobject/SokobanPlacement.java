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
        for (SGOBox box : getBoxes()) {
            if (box.getXyCoordinate().equals(xyCoordinate)) {
                return WhoMovableInTile.IS_BOX;
            }
        }
        if (getPlayer().getXyCoordinate().equals(xyCoordinate)) {
            return WhoMovableInTile.IS_PLAYER;
        }
        return WhoMovableInTile.IS_NOBODY;
    }

    public WhoPersistentInTile getWhoPersistentInTile(XYCoordinate xyCoordinate) {
        for (SGOWall wall : getWalls()) {
            if (wall.getXyCoordinate().equals(xyCoordinate)) {
                return WhoPersistentInTile.IS_WALL;
            }
        }
        for (SGOHome home : getHomes()) {
            if (home.getXyCoordinate().equals(xyCoordinate)) {
                return WhoPersistentInTile.IS_HOME;
            }
        }
        return WhoPersistentInTile.IS_EMPTY;
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

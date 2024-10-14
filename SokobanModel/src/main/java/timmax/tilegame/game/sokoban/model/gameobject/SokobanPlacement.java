package timmax.tilegame.game.sokoban.model.gameobject;

import timmax.tilegame.basemodel.gameobject.GameObjectStateAutomaton;
import timmax.tilegame.basemodel.gameobject.GameObjectsPlacement;
import timmax.tilegame.basemodel.gameobject.GameObjectsPlacementNotVerified;
import timmax.tilegame.basemodel.gameobject.XYCoordinate;

import java.util.HashSet;
import java.util.Set;

public class SokobanPlacement extends GameObjectsPlacement {

    public SokobanPlacement(GameObjectsPlacementNotVerified gameObjectsPlacementNotVerified) {
        super(gameObjectsPlacementNotVerified, 0);
    }

    public SokobanPlacement(GameObjectsPlacement gameObjectsPlacement) {
        super(gameObjectsPlacement);
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
        for (GameObjectStateAutomaton gameObjectStateAutomaton : getGameObjectsPlacementNotVerified().getGameObjectStateAutomatonSet()) {
            if (gameObjectStateAutomaton.getGameObject() instanceof SGOPlayer sgoPlayer) {
                return sgoPlayer;
            }
        }
        //  ToDo:   Исключение нужно сгенерить.
        return null;
    }

    public Set<SGOBox> getBoxes() {
        Set<SGOBox> result = new HashSet<>();
        for (GameObjectStateAutomaton gameObjectStateAutomaton : getGameObjectsPlacementNotVerified().getGameObjectStateAutomatonSet()) {
            if (gameObjectStateAutomaton.getGameObject() instanceof SGOBox sgoBox) {
                result.add(sgoBox);
            }
        }
        return result;
    }

    public Set<SGOWall> getWalls() {
        Set<SGOWall> result = new HashSet<>();
        for (GameObjectStateAutomaton gameObjectStateAutomaton : getGameObjectsPlacementNotVerified().getGameObjectStateAutomatonSet()) {
            if (gameObjectStateAutomaton.getGameObject() instanceof SGOWall sgoWall) {
                result.add(sgoWall);
            }
        }
        return result;
    }

    public Set<SGOHome> getHomes() {
        Set<SGOHome> result = new HashSet<>();
        for (GameObjectStateAutomaton gameObjectStateAutomaton : getGameObjectsPlacementNotVerified().getGameObjectStateAutomatonSet()) {
            if (gameObjectStateAutomaton.getGameObject() instanceof SGOHome sgoHome) {
                result.add(sgoHome);
            }
        }
        return result;
    }

    @Override
    public SokobanPlacementNotVerified getGameObjectsPlacementNotVerified() {
        return (SokobanPlacementNotVerified) super.getGameObjectsPlacementNotVerified();
    }
}

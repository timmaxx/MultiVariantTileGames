package timmax.tilegame.game.minesweeper.model.gameobject;

import timmax.tilegame.basemodel.gameobject.GameObjectStateAutomaton;
import timmax.tilegame.basemodel.gameobject.GameObjectsPlacement;
import timmax.tilegame.basemodel.gameobject.GameObjectsPlacementNotVerified;
import timmax.tilegame.basemodel.gameobject.XYCoordinate;

import java.util.HashSet;
import java.util.Set;

public class MinesweeperPlacement extends GameObjectsPlacement {
    public MinesweeperPlacement(GameObjectsPlacementNotVerified gameObjectsPlacementNotVerified) {
        super(gameObjectsPlacementNotVerified, 0);
    }

    public MinesweeperPlacement(GameObjectsPlacement gameObjectsPlacement) {
        super(gameObjectsPlacement);
    }

    public Set<GameObjectStateAutomaton> getGameObjectStateAutomatonSetInXYCoordinate(XYCoordinate xyCoordinate) {
        Set<GameObjectStateAutomaton> result = new HashSet<>();
        //  Найдём объект по координатам
        for (GameObjectStateAutomaton gameObjectStateAutomaton : getGameObjectsPlacementNotVerified().getGameObjectStateAutomatonSet()) {
            if (gameObjectStateAutomaton.getXyCoordinate().equals(xyCoordinate)) {
                result.add(gameObjectStateAutomaton);
            }
        }
        return result;
    }
}

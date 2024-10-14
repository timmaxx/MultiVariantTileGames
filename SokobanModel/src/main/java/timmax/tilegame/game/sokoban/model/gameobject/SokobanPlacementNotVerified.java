package timmax.tilegame.game.sokoban.model.gameobject;

import timmax.tilegame.basemodel.gameobject.GameObjectsPlacementNotVerified;
import timmax.tilegame.basemodel.gameobject.WidthHeightSizes;
import timmax.tilegame.basemodel.protocol.server.GameMatch;

public class SokobanPlacementNotVerified extends GameObjectsPlacementNotVerified {
    public SokobanPlacementNotVerified(GameMatch gameMatch, WidthHeightSizes widthHeightSizes) {
        super(gameMatch, widthHeightSizes);
    }

    public SokobanPlacementNotVerified(GameMatch gameMatch) {
        super(gameMatch);
    }
}

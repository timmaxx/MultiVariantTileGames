package timmax.tilegame.game.sokoban.model;

import timmax.tilegame.basemodel.gameobject.GameObjectsPlacementStateAutomaton;
import timmax.tilegame.basemodel.gameobject.XYCoordinate;
import timmax.tilegame.basemodel.protocol.server.GameMatch;
import timmax.tilegame.game.sokoban.model.gameobject.*;

import static timmax.tilegame.game.sokoban.model.gameobject.WhoMovableInTile.IS_NOBODY;
import static timmax.tilegame.game.sokoban.model.gameobject.WhoMovableInTile.IS_PLAYER;
import static timmax.tilegame.game.sokoban.model.gameobject.WhoPersistentInTile.IS_EMPTY;

public class SokobanPlacementStateAutomaton extends GameObjectsPlacementStateAutomaton {
    //  Warning:(13, 43) Raw use of parameterized class 'GameMatch'
    public SokobanPlacementStateAutomaton(GameMatch gameMatch) {
        super(gameMatch);
    }

    WhoPersistentInTile getWhoPersistentInTile(XYCoordinate xyCoordinate) {
        return getGameObjectSetFilteredByXYCoordinate(xyCoordinate)
                .stream()
                .filter(sgo -> sgo instanceof SGOWall)
                .map(sgow -> WhoPersistentInTile.IS_WALL)
                .findAny()
                .orElse(getGameObjectSetFilteredByXYCoordinate(xyCoordinate)
                        .stream()
                        .filter(sgo -> sgo instanceof SGOHome)
                        .map(sgow -> WhoPersistentInTile.IS_HOME)
                        .findAny()
                        .orElse(IS_EMPTY)
                );
    }

    WhoMovableInTile getWhoMovableInTile(XYCoordinate xyCoordinate) {
        return getGameObjectSetFilteredByXYCoordinate(xyCoordinate)
                .stream()
                .filter(sgo -> sgo instanceof SGOBox)
                .map(sgoBox -> WhoMovableInTile.IS_BOX)
                .findAny()
                .orElse(
                        getGameObjectSetFilteredByXYCoordinate(xyCoordinate)
                                .stream()
                                .filter(sgo -> sgo instanceof SGOPlayer)
                                .map(sgoBox -> IS_PLAYER)
                                .findAny()
                                .orElse(IS_NOBODY)
                );
    }

    SGOPlayer getPlayer() {
        return (SGOPlayer) getGameObjectSetFilteredByGameObjectClass(SGOPlayer.class)
                .stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public GameMatchOfSokoban getGameMatch() {
        return (GameMatchOfSokoban) super.getGameMatch();
    }
}

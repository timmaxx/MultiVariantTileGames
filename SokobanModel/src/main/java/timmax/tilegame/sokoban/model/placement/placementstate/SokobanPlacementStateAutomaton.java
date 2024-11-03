package timmax.tilegame.sokoban.model.placement.placementstate;

import javafx.scene.input.KeyCode;
import timmax.tilegame.basemodel.exception.GameObjectAlreadyExistsException;
import timmax.tilegame.basemodel.placement.placementstate.GameObjectsPlacementStateAutomaton;
import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;
import timmax.tilegame.basemodel.protocol.server.GameMatch;

import timmax.tilegame.sokoban.model.GameMatchOfSokoban;
import timmax.tilegame.sokoban.model.gameevent.GameEventOneTileSokobanChangeable;
import timmax.tilegame.sokoban.model.placement.gameobject.*;
import timmax.tilegame.sokoban.model.placement.primitives.SokobanXYOffset;

import static timmax.tilegame.sokoban.model.placement.gameobject.WhoMovableInTile.*;
import static timmax.tilegame.sokoban.model.placement.gameobject.WhoPersistentInTile.*;
import static timmax.tilegame.sokoban.model.placement.primitives.SokobanXYOffset.*;

public class SokobanPlacementStateAutomaton extends GameObjectsPlacementStateAutomaton {
    //  Warning:(13, 43) Raw use of parameterized class 'GameMatch'
    public SokobanPlacementStateAutomaton(GameMatch gameMatch) {
        super(gameMatch);
    }

    public WhoPersistentInTile getWhoPersistentInTile(XYCoordinate xyCoordinate) {
        return getGameObjectSetFilteredByXYCoordinate(xyCoordinate)
                .stream()
                .filter(sgo -> sgo instanceof SGOWall)
                .map(sgow -> WhoPersistentInTile.IS_WALL)
                .findAny()
                .orElse(getGameObjectSetFilteredByXYCoordinate(xyCoordinate)
                        .stream()
                        .filter(sgo -> sgo instanceof SGOHome)
                        .map(sgow -> IS_HOME)
                        .findAny()
                        .orElse(IS_EMPTY)
                );
    }

    public WhoMovableInTile getWhoMovableInTile(XYCoordinate xyCoordinate) {
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

    public void sendGameEventToAllViews(XYCoordinate xyCoordinate) {
        WhoPersistentInTile whoPersistentInTileBefore = getWhoPersistentInTile(xyCoordinate);
        WhoMovableInTile whoMovableInTile = getWhoMovableInTile(xyCoordinate);
        getGameMatch().sendGameEventToAllViews(new GameEventOneTileSokobanChangeable(xyCoordinate, whoPersistentInTileBefore, whoMovableInTile));
    }

    private SGOPlayer getPlayer() {
        return (SGOPlayer) getGameObjectSetFilteredByGameObjectClass(SGOPlayer.class)
                .stream()
                .findAny()
                .orElse(null);
    }

    private void movePlayer(SokobanXYOffset sokobanXYOffset) {
        try {
            getPlayer().move(sokobanXYOffset);
        } catch (GameObjectAlreadyExistsException ignored) {
        }
    }

    public void movePlayerToMouseClick(XYCoordinate xyCoordinateOfMouseClick) {
        SGOPlayer player = getPlayer();
        XYCoordinate xyCoordinateOfPlayer = player.getXyCoordinate();

        if (xyCoordinateOfMouseClick.hasEqualY(xyCoordinateOfPlayer)) {
            if (xyCoordinateOfMouseClick.hasXLesser(xyCoordinateOfPlayer)) {
                movePlayer(TO_LEFT);
            } else if (xyCoordinateOfMouseClick.hasXGreater(xyCoordinateOfPlayer)) {
                movePlayer(TO_RIGHT);
            }
        } else if ((xyCoordinateOfMouseClick.hasEqualX(xyCoordinateOfPlayer))) {
            if (xyCoordinateOfMouseClick.hasYLesser(xyCoordinateOfPlayer)) {
                movePlayer(TO_UP);
            } else if (xyCoordinateOfMouseClick.hasYGreater(xyCoordinateOfPlayer)) {
                movePlayer(TO_DOWN);
            }
        }
    }

    public void movePlayerByKeyCode(KeyCode keyCode) {
        if (keyCode == KeyCode.LEFT) {
            movePlayer(TO_LEFT);
        } else if (keyCode == KeyCode.RIGHT) {
            movePlayer(TO_RIGHT);
        } else if (keyCode == KeyCode.UP) {
            movePlayer(TO_UP);
        } else if (keyCode == KeyCode.DOWN) {
            movePlayer(TO_DOWN);
        }
    }

    @Override
    public GameMatchOfSokoban getGameMatch() {
        return (GameMatchOfSokoban) super.getGameMatch();
    }
}

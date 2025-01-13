package timmax.tilegame.sokoban.model.placement.placementstate;

import javafx.scene.input.KeyCode;
import timmax.tilegame.basemodel.exception.GameObjectAlreadyExistsException;
import timmax.tilegame.basemodel.placement.gameobject.GameObject;
import timmax.tilegame.basemodel.placement.placementstate.GameObjectsPlacementStateAutomaton;
import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;
import timmax.tilegame.basemodel.protocol.server.GameMatch;

import timmax.tilegame.sokoban.model.GameMatchOfSokoban;
import timmax.tilegame.sokoban.model.gameevent.GameEventOneTileSokobanChangeable;
import timmax.tilegame.sokoban.model.placement.gameobject.*;
import timmax.tilegame.sokoban.model.placement.primitives.SokobanXYOffset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

import static timmax.tilegame.sokoban.model.placement.gameobject.WhoMovableInTile.*;
import static timmax.tilegame.sokoban.model.placement.gameobject.WhoPersistentInTile.*;
import static timmax.tilegame.sokoban.model.placement.primitives.SokobanXYOffset.*;

public class SokobanPlacementStateAutomaton extends GameObjectsPlacementStateAutomaton {
    public SokobanPlacementStateAutomaton(
            GameMatch gameMatch
            , Path levels
            //  ToDo:   Следующий параметр завернуть в
            //              Map<String, Integer> paramsOfModelValueMap
            //          и тогда getLevel() здесь и в другом классе будет с одинаковым количеством параметров.
            , int level) {
        super(gameMatch);

        //  ToDo:   Работу с этими счетчиками можно не делать в этом методе, но реализовать валидацию правильно.
        int countOfBoxes = 0;
        int countOfHome = 0;
        int countOfPlayers = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(levels.toFile()))) {
            int readLevel = 0;

            int y = 0;
            boolean isLevelMap = false;
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains("Maze:")) {
                    readLevel = Integer.parseInt(line.split(" ")[1]);
                    continue;
                }
                if (readLevel != level) {
                    continue;
                }
                if (line.length() == 0) {
                    isLevelMap = !isLevelMap;
                    if (isLevelMap) {
                        continue;
                    } else {
                        break;
                    }
                }

                char[] chars = line.toCharArray();
                int x = 0;
                for (char c : chars) {
                    XYCoordinate xyCoordinate = new XYCoordinate(x, y);
                    if (c == 'X') {
                        add(new SGOStateAutomaton(new SGOWall(xyCoordinate.toString(), this, xyCoordinate)));
                    } else if (c == '*') {
                        countOfBoxes++;
                        add(new SGOStateAutomaton(new SGOBox(xyCoordinate.toString(), this, xyCoordinate)));
                    } else if (c == '.') {
                        countOfHome++;
                        add(new SGOStateAutomaton(new SGOHome(xyCoordinate.toString(), this, xyCoordinate)));
                    } else if (c == '&') {
                        countOfBoxes++;
                        add(new SGOStateAutomaton(new SGOBox(xyCoordinate.toString(), this, xyCoordinate)));
                        countOfHome++;
                        add(new SGOStateAutomaton(new SGOHome(xyCoordinate.toString(), this, xyCoordinate)));
                    } else if (c == '@') {
                        countOfPlayers++;
                        add(new SGOStateAutomaton(new SGOPlayer(xyCoordinate.toString(), this, xyCoordinate)));
                    }
                    x++;
                }
                y++;
            }
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
            // throw e;
        }

        //  ToDo:   Переместить вызов этого метода валидации отсюда в turnOnVerifable(...).
        //  ToDo:   Да и сам метод нужно сделать как реализацию абстрактного метода валидации.
        validate(countOfPlayers, countOfBoxes, countOfHome);

        turnOnVerifable(0);
    }

    private static void validate(int countOfPlayers, int countOfBoxes, int countOfHome) {
        StringBuilder errMessage = new StringBuilder();
        boolean isError = false;
        if (countOfPlayers != 1) {
            isError = true;
            errMessage.append("countOfPlayers <> 1!");
        }
        if (countOfBoxes != countOfHome) {
            isError = true;
            errMessage.append(" countOfBoxes <> countOfHome!");
        }
        if (isError) {
            throw new RuntimeException(errMessage.toString());
        }
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
        //  ToDo:   Вместо
        //          getGameObjectStateAutomaton().getGameObject().getGameObjectsPlacement().getGameMatch().getRemoteClientStateAutomaton().getSenderOfEventOfServer()
        //          сделать getSenderOfEventOfServer(), который будет доставаться сразу из свойств сервера.
        //  Warning:(151, 9) Unchecked call to 'sendGameEventToAllViews(GameEvent, Map<String, Class<? extends View>>)' as a member of raw type 'timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton'
        getGameMatch().getRemoteClientStateAutomaton().getSenderOfEventOfServer().sendGameEventToAllViews(
                getGameMatch().getMatchPlayerList(),
                new GameEventOneTileSokobanChangeable(xyCoordinate, whoPersistentInTileBefore, whoMovableInTile),
                //  Warning:(155, 17) Unchecked assignment: 'java.util.Map' to 'java.util.Map<java.lang.String,java.lang.Class<? extends timmax.tilegame.baseview.View>>'. Reason: 'getGameMatch().getGameType()' has raw type, so result of getViewName_ViewClassMap is erased
                getGameMatch().getGameType().getViewName_ViewClassMap()
        );
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

    public boolean isGameOver() {
        int countBoxesOnHomes = 0;
        int countBoxes = 0;
        for (GameObject gameObject : getGameObjectSetFilteredByGameObjectClass(SGOBox.class)) {
            countBoxes++;
            if (gameObject instanceof SGOBox sgoBox1) {
                countBoxesOnHomes += sgoBox1.countOnHome();
            }
        }
        return countBoxesOnHomes > 0 && countBoxes == countBoxesOnHomes;
    }

    @Override
    public GameMatchOfSokoban getGameMatch() {
        return (GameMatchOfSokoban) super.getGameMatch();
    }
}

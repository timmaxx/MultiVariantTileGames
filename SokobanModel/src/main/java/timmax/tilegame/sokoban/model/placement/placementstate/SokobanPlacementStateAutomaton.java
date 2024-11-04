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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

import static timmax.tilegame.sokoban.model.placement.gameobject.WhoMovableInTile.*;
import static timmax.tilegame.sokoban.model.placement.gameobject.WhoPersistentInTile.*;
import static timmax.tilegame.sokoban.model.placement.primitives.SokobanXYOffset.*;

public class SokobanPlacementStateAutomaton extends GameObjectsPlacementStateAutomaton {
    //  Warning:(13, 43) Raw use of parameterized class 'GameMatch'
    public SokobanPlacementStateAutomaton(
            GameMatch gameMatch
            , Path levels
            //  ToDo:   Следующий параметр завернуть в
            //              Map<String, Integer> paramsOfModelValueMap
            //          и тогда getLevel() здесь и в другом классе будет с одинаковым количеством параметров.
            , int level) {
        super(gameMatch);

        int countOfBoxes = 0;
        int countOfHome = 0;
        int countOfPlayers = 0;

        // ToDo:    Нужно высчитать ширину и высоту, исходя из карты уровня (определив число строк и максимальный столбец).
        //          Сейчас берутся явно из файла.
        //  int width = 0;
        //  int height = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(levels.toFile()))) {
            int readLevel = 0;

            int y = 0;
            boolean isLevelMap = false;
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains("Maze:")) {
                    readLevel = Integer.parseInt(line.split(" ")[1]);
                    continue;
                } else if (line.contains("Size X:")) {
                    //  ToDo:   Убрать из файла уровней размеры по ширине и высоте.
                    //  Warning:(55, 41) Result of 'Integer.parseInt()' is ignored
                    /*width = */
                    Integer.parseInt(line.split(" ")[2]);
                    continue;
                } else if (line.contains("Size Y:")) {
                    //  ToDo:   Убрать из файла уровней размеры по ширине и высоте.
                    //  Warning:(59, 42) Result of 'Integer.parseInt()' is ignored
                    /*height = */
                    Integer.parseInt(line.split(" ")[2]);
                    continue;
                }
                if (readLevel == level) {
                    if (line.length() == 0) {
                        boolean isEnd = isLevelMap;
                        isLevelMap = !isLevelMap;
                        if (isEnd) {
                            break;
                        } else {
                            continue;
                        }
                    }
                    if (!isLevelMap) {
                        continue;
                    }

                    char[] chars = line.toCharArray();
                    int x = 0;
                    for (char c : chars) {
                        XYCoordinate xyCoordinate = new XYCoordinate(x, y);
                        if (c == 'X') {
                            SGOWall sgoWall = new SGOWall(xyCoordinate.toString(), this, xyCoordinate);
                            SGOStateAutomaton sgoSAutomatonWall = new SGOStateAutomaton(sgoWall);
                            add(sgoSAutomatonWall);
                        } else if (c == '*') {
                            countOfBoxes++;
                            SGOBox sgoBox = new SGOBox(xyCoordinate.toString(), this, xyCoordinate);
                            SGOStateAutomaton sgoSAutomatonBox = new SGOStateAutomaton(sgoBox);
                            add(sgoSAutomatonBox);
                        } else if (c == '.') {
                            countOfHome++;
                            SGOHome sgoHome = new SGOHome(xyCoordinate.toString(), this, xyCoordinate);
                            SGOStateAutomaton sgoSAutomatonHome = new SGOStateAutomaton(sgoHome);
                            add(sgoSAutomatonHome);
                        } else if (c == '&') {
                            countOfBoxes++;
                            SGOBox sgoBox = new SGOBox(xyCoordinate.toString(), this, xyCoordinate);
                            SGOStateAutomaton sgoSAutomatonBox = new SGOStateAutomaton(sgoBox);
                            add(sgoSAutomatonBox);

                            countOfHome++;
                            SGOHome sgoHome = new SGOHome(xyCoordinate.toString(), this, xyCoordinate);
                            SGOStateAutomaton sgoSAutomatonHome = new SGOStateAutomaton(sgoHome);
                            add(sgoSAutomatonHome);
                        } else if (c == '@') {
                            countOfPlayers++;
                            SGOPlayer sgoPlayer = new SGOPlayer(xyCoordinate.toString(), this, xyCoordinate);
                            SGOStateAutomaton sgoSAutomatonPlayer = new SGOStateAutomaton(sgoPlayer);
                            add(sgoSAutomatonPlayer);
                        }
                        x++;
                    }
                    y++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
    // Warning:(229, 12) Raw use of parameterized class 'GameMatchOfSokoban'
    public GameMatchOfSokoban getGameMatch() {
        // Warning:(231, 12) Raw use of parameterized class 'GameMatchOfSokoban'
        return (GameMatchOfSokoban) super.getGameMatch();
    }
}

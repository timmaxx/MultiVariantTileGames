package timmax.tilegame.game.sokoban.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

import timmax.tilegame.basemodel.gameobject.XYCoordinate;
import timmax.tilegame.basemodel.protocol.server.GameMatch;
import timmax.tilegame.game.sokoban.model.gameobject.*;

//  ToDo:   Классы LevelLoader для Сокобан и LevelGenerator для Сапёра увязать в одну иерархию.
//          Т.к. они имеют метод getLevel, который возвращает размещение.
//          Но с другой стороны, один из них генерирует размещение, а другой считывает его из файла.
//          Нужно учесть и это!
//  ToDo:   А может перенести функционал getLevel() в SokobanPlacement в конструктор или в статический метод?
public class LevelLoader {
    private final Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    //  ToDo:   GameMatch gameMatch удалить, т.к. он нужен только для вызова конструктора.
    public SokobanPlacement getLevel(GameMatch gameMatch, int level) {

        int countOfBoxes = 0;
        int countOfHome = 0;
        int countOfPlayers = 0;

        SokobanPlacementNotVerified sokobanPlacementNotVerified = new SokobanPlacementNotVerified(gameMatch);

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
                    /*width = */Integer.parseInt(line.split(" ")[2]);
                    continue;
                } else if (line.contains("Size Y:")) {
                    /*height = */Integer.parseInt(line.split(" ")[2]);
                    continue;
                }
                if (readLevel == level) {
                    if (line.length() == 0) {
                        boolean isEnd = isLevelMap;

                        isLevelMap = !isLevelMap;

/*
                        if (isEnd && !isLevelMap) {
                            break;
                        } else {
                            continue;
                        }
*/
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
                            SGOWall sgoWall = new SGOWall(xyCoordinate.toString(), sokobanPlacementNotVerified, xyCoordinate);
                            SGOStateAutomaton sgoSAutomatonWall = new SGOStateAutomaton(sgoWall);
                            sokobanPlacementNotVerified.add(sgoSAutomatonWall);
                        } else if (c == '*') {
                            countOfBoxes++;
                            SGOBox sgoBox = new SGOBox(xyCoordinate.toString(), sokobanPlacementNotVerified, xyCoordinate);
                            SGOStateAutomaton sgoSAutomatonBox = new SGOStateAutomaton(sgoBox);
                            sokobanPlacementNotVerified.add(sgoSAutomatonBox);
                        } else if (c == '.') {
                            countOfHome++;
                            SGOHome sgoHome = new SGOHome(xyCoordinate.toString(), sokobanPlacementNotVerified, xyCoordinate);
                            SGOStateAutomaton sgoSAutomatonHome = new SGOStateAutomaton(sgoHome);
                            sokobanPlacementNotVerified.add(sgoSAutomatonHome);
                        } else if (c == '&') {
                            countOfBoxes++;
                            SGOBox sgoBox = new SGOBox(xyCoordinate.toString(), sokobanPlacementNotVerified, xyCoordinate);
                            SGOStateAutomaton sgoSAutomatonBox = new SGOStateAutomaton(sgoBox);
                            sokobanPlacementNotVerified.add(sgoSAutomatonBox);

                            countOfHome++;
                            SGOHome sgoHome = new SGOHome(xyCoordinate.toString(), sokobanPlacementNotVerified, xyCoordinate);
                            SGOStateAutomaton sgoSAutomatonHome = new SGOStateAutomaton(sgoHome);
                            sokobanPlacementNotVerified.add(sgoSAutomatonHome);
                        } else if (c == '@') {
                            countOfPlayers++;
                            SGOPlayer sgoPlayer = new SGOPlayer(xyCoordinate.toString(), sokobanPlacementNotVerified, xyCoordinate);
                            SGOStateAutomaton sgoSAutomatonPlayer = new SGOStateAutomaton(sgoPlayer);
                            sokobanPlacementNotVerified.add(sgoSAutomatonPlayer);
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

        return new SokobanPlacement(sokobanPlacementNotVerified);
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
}

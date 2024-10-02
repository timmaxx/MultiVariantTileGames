package timmax.tilegame.game.minesweeper.model.gameobject;

import timmax.tilegame.basemodel.gameobject.OneTileGameObject;
import timmax.tilegame.basemodel.gameobject.WidthHeightSizes;
import timmax.tilegame.basemodel.gameobject.XYCoordinate;
import timmax.tilegame.game.minesweeper.model.GameTypeOfMinesweeper;

public class LevelGenerator {
    private final GameTypeOfMinesweeper gameTypeOfMinesweeper;

    public LevelGenerator(GameTypeOfMinesweeper gameTypeOfMinesweeper) {
        this.gameTypeOfMinesweeper = gameTypeOfMinesweeper;
    }

    public OneTileGameObjectsPlacementOfMinesweeper getLevel(
            WidthHeightSizes widthHeightSizes,
            int restOfMineInstallationInPercents) {

        //  Создаём новую пустую расстановку для Сапёра
        OneTileGameObjectsPlacementOfMinesweeper oneTileGameObjectsPlacementOfMinesweeper =
                new OneTileGameObjectsPlacementOfMinesweeper(
                        gameTypeOfMinesweeper,
                        widthHeightSizes,
                        0
                );

        int countMinesOnField = 0;

        //  Расставим мины
        do {
            XYCoordinate xyCoordinate = XYCoordinate.getRandom(widthHeightSizes);
            if (oneTileGameObjectsPlacementOfMinesweeper.getOneTileGameObjectStateAutomatonSetInXYCoordinate(xyCoordinate).size() == 1) {
                continue;
            }
            MinesweeperGameObjectStateAutomaton mine =
                    new MinesweeperGameObjectStateAutomaton(
                            new OneTileGameObject(xyCoordinate.toString(), oneTileGameObjectsPlacementOfMinesweeper, xyCoordinate),
                            true
                    );
            try {
                oneTileGameObjectsPlacementOfMinesweeper.add(mine);
            } catch (RuntimeException rte) {
            }
            System.out.println("OneTileGameObjectsPlacementOfMinesweeper :: OneTileGameObjectsPlacementOfMinesweeper getLevel2(WidthHeightSizes widthHeightSizes, int restOfMineInstallationInPercents)");
            System.out.println("  mine = " + xyCoordinate);
            countMinesOnField++;
        } while (countMinesOnField < widthHeightSizes.getSquare() * restOfMineInstallationInPercents / 100);

        //  Расставим не мины
        for (int y = 0; y < widthHeightSizes.height(); y++) {
            for (int x = 0; x < widthHeightSizes.width(); x++) {
                XYCoordinate xyCoordinate = new XYCoordinate(x, y);
                if (oneTileGameObjectsPlacementOfMinesweeper.getOneTileGameObjectStateAutomatonSetInXYCoordinate(xyCoordinate).size() == 1) {
                    continue;
                }

                MinesweeperGameObjectStateAutomaton noMine =
                        new MinesweeperGameObjectStateAutomaton(
                                new OneTileGameObject(xyCoordinate.toString(), oneTileGameObjectsPlacementOfMinesweeper, xyCoordinate),
                                false
                        );
                try {
                    oneTileGameObjectsPlacementOfMinesweeper.add(noMine);
                } catch (RuntimeException rte) {
                    System.out.println("LevelGenerator :: OneTileGameObjectsPlacementOfMinesweeper getLevel2(WidthHeightSizes widthHeightSizes, int restOfMineInstallationInPercents). Before 'Расставим не мины'");
                    System.out.println("  catch (RuntimeException rte)");
                }
            }
        }

        return oneTileGameObjectsPlacementOfMinesweeper;
    }
}

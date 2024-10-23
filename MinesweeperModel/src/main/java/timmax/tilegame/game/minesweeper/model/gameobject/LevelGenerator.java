package timmax.tilegame.game.minesweeper.model.gameobject;

import timmax.tilegame.basemodel.gameobject.GameObject;
import timmax.tilegame.basemodel.gameobject.WidthHeightSizes;
import timmax.tilegame.basemodel.gameobject.XYCoordinate;
import timmax.tilegame.basemodel.protocol.server.GameMatch;

//  ToDo:   Классы LevelLoader для Сокобан и LevelGenerator для Сапёра увязать в одну иерархию.
//          Т.к. они имеют метод getLevel, который возвращает размещение.
//          Но с другой стороны, один из них генерирует размещение, а другой считывает его из файла.
//          Нужно учесть и это!
//  ToDo:   А может перенести функционал getLevel() в MinesweeperPlacement в конструктор или в статический метод?
public class LevelGenerator {
    //  ToDo:   GameMatch gameMatch удалить, т.к. он нужен только для вызова конструктора.
    public MinesweeperPlacement getLevel(
            GameMatch gameMatch,
            //  ToDo:   Следующие два параметра завернуть в
            //              Map<String, Integer> paramsOfModelValueMap
            //          и тогда getLevel() здесь и в другом классе будет с одинаковым количеством параметров.
            WidthHeightSizes widthHeightSizes,
            int restOfMineInstallationInPercents) {

        //  Создаём новую пустую не верифицированную расстановку для Сапёра
        MinesweeperPlacementNotVerified minesweeperPlacementNotVerified =
                new MinesweeperPlacementNotVerified(
                        gameMatch,
                        widthHeightSizes
                );

        int countMinesOnField = 0;

        //  Расставим мины
        do {
            XYCoordinate xyCoordinate = XYCoordinate.getRandom(widthHeightSizes);
            if (minesweeperPlacementNotVerified.getGameObjectStateAutomatonSetFilteredXYCoordinate(xyCoordinate).size() == 1) {
                continue;
            }
            MGOStateAutomaton mine =
                    new MGOStateAutomaton(
                            new GameObject(xyCoordinate.toString(), minesweeperPlacementNotVerified, xyCoordinate),
                            true
                    );
            try {
                minesweeperPlacementNotVerified.add(mine);
            } catch (RuntimeException rte) {
            }
            countMinesOnField++;
        } while (countMinesOnField < widthHeightSizes.getSquare() * restOfMineInstallationInPercents / 100);

        //  Расставим не мины
        for (int y = 0; y < widthHeightSizes.getHeight(); y++) {
            for (int x = 0; x < widthHeightSizes.getWidth(); x++) {
                XYCoordinate xyCoordinate = new XYCoordinate(x, y);
                if (minesweeperPlacementNotVerified.getGameObjectStateAutomatonSetFilteredXYCoordinate(xyCoordinate).size() == 1) {
                    continue;
                }

                MGOStateAutomaton noMine =
                        new MGOStateAutomaton(
                                new GameObject(xyCoordinate.toString(), minesweeperPlacementNotVerified, xyCoordinate),
                                false
                        );
                try {
                    minesweeperPlacementNotVerified.add(noMine);
                } catch (RuntimeException rte) {
                }
            }
        }

        return new MinesweeperPlacement(minesweeperPlacementNotVerified);
    }
}

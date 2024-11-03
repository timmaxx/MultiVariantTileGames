package timmax.tilegame.minesweeper.model.placement.placementstate;

import timmax.tilegame.basemodel.exception.GameObjectAlreadyExistsException;
import timmax.tilegame.basemodel.placement.gameobject.GameObject;
import timmax.tilegame.basemodel.placement.placementstate.GameObjectsPlacementStateAutomaton;
import timmax.tilegame.basemodel.placement.primitives.WidthHeightSizes;
import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;
import timmax.tilegame.basemodel.protocol.server.GameMatch;
import timmax.tilegame.minesweeper.model.placement.gameobject.MGOStateAutomaton;

//  ToDo:   Классы LevelLoader для Сокобан и LevelGenerator для Сапёра увязать в одну иерархию.
//          Т.к. они имеют метод getLevel, который возвращает размещение.
//          Но с другой стороны, один из них генерирует размещение, а другой считывает его из файла.
//          Нужно учесть и это!
//  ToDo:   А может перенести функционал getLevel() в MinesweeperPlacement в конструктор или в статический метод?
public class LevelGenerator {
    //  ToDo:   GameMatch gameMatch удалить, т.к. он нужен только для вызова конструктора.
    public GameObjectsPlacementStateAutomaton getLevel(
            GameMatch gameMatch,
            //  ToDo:   Следующие два параметра завернуть в
            //              Map<String, Integer> paramsOfModelValueMap
            //          и тогда getLevel() здесь и в другом классе будет с одинаковым количеством параметров.
            WidthHeightSizes widthHeightSizes,
            int restOfMineInstallationInPercents) {

        //  Создаём новую пустую не верифицированную расстановку для Сапёра
        GameObjectsPlacementStateAutomaton minesweeperPlacement = new GameObjectsPlacementStateAutomaton(gameMatch);

        int countMinesOnField = 0;

        //  Расставим мины
        do {
            XYCoordinate xyCoordinate = XYCoordinate.getRandom(widthHeightSizes);
            if (minesweeperPlacement.getGameObjectStateAutomatonSetFilteredByXYCoordinate(xyCoordinate).size() == 1) {
                continue;
            }

            try {
                minesweeperPlacement.add(new MGOStateAutomaton(
                        new GameObject(xyCoordinate.toString(), minesweeperPlacement, xyCoordinate),
                        true
                ));
            }
            //  Warning:(45, 15) Empty 'catch' block
            catch (GameObjectAlreadyExistsException ignored) {
            }
            countMinesOnField++;
        } while (countMinesOnField < widthHeightSizes.getSquare() * restOfMineInstallationInPercents / 100);

        //  Расставим не мины
        for (int y = 0; y < widthHeightSizes.getHeight(); y++) {
            for (int x = 0; x < widthHeightSizes.getWidth(); x++) {
                XYCoordinate xyCoordinate = new XYCoordinate(x, y);
                if (minesweeperPlacement.getGameObjectStateAutomatonSetFilteredByXYCoordinate(xyCoordinate).size() == 1) {
                    continue;
                }

                try {
                    minesweeperPlacement.add(new MGOStateAutomaton(
                            new GameObject(xyCoordinate.toString(), minesweeperPlacement, xyCoordinate),
                            false
                    ));
                }
                //  Warning:(65, 19) Empty 'catch' block
                catch (GameObjectAlreadyExistsException ignored) {
                }
            }
        }
        minesweeperPlacement.turnOnVerifable(0);
        return minesweeperPlacement;
    }
}

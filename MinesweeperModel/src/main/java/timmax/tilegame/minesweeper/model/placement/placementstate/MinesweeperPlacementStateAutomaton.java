package timmax.tilegame.minesweeper.model.placement.placementstate;

import timmax.tilegame.basemodel.exception.GameObjectAlreadyExistsException;
import timmax.tilegame.basemodel.placement.gameobject.GameObject;
import timmax.tilegame.basemodel.placement.placementstate.GameObjectsPlacementStateAutomaton;
import timmax.tilegame.basemodel.placement.primitives.WidthHeightSizes;
import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;
import timmax.tilegame.basemodel.protocol.server.GameMatch;
import timmax.tilegame.minesweeper.model.placement.gameobject.MGOStateAutomaton;

public class MinesweeperPlacementStateAutomaton extends GameObjectsPlacementStateAutomaton {
    //  Warning:(12, 47) Raw use of parameterized class 'GameMatch'
    public MinesweeperPlacementStateAutomaton(GameMatch gameMatch,
                                              //  ToDo:   Следующие два параметра завернуть в
                                              //              Map<String, Integer> paramsOfModelValueMap
                                              //          и тогда getLevel() здесь и в другом классе будет с одинаковым количеством параметров.
                                              WidthHeightSizes widthHeightSizes,
                                              int restOfMineInstallationInPercents) {
        super(gameMatch);

        int countMinesOnField = 0;

        //  Расставим мины
        do {
            XYCoordinate xyCoordinate = XYCoordinate.getRandom(widthHeightSizes);
            if (getGameObjectStateAutomatonSetFilteredByXYCoordinate(xyCoordinate).size() == 1) {
                continue;
            }

            try {
                add(new MGOStateAutomaton(
                        new GameObject(xyCoordinate.toString(), this, xyCoordinate),
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
                if (getGameObjectStateAutomatonSetFilteredByXYCoordinate(xyCoordinate).size() == 1) {
                    continue;
                }

                try {
                    add(new MGOStateAutomaton(
                            new GameObject(xyCoordinate.toString(), this, xyCoordinate),
                            false
                    ));
                }
                //  Warning:(65, 19) Empty 'catch' block
                catch (GameObjectAlreadyExistsException ignored) {
                }
            }
        }
        turnOnVerifable(0);
    }
}

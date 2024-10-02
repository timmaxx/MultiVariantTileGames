package timmax.tilegame.game.minesweeper.model.gameobject;

import timmax.tilegame.basemodel.exception.XYCoordinateIsOutOfRangeException;
import timmax.tilegame.basemodel.gameobject.OneTileGameObject;
import timmax.tilegame.basemodel.gameobject.OneTileGameObjectStateAutomaton;
import timmax.tilegame.basemodel.gameobject.XYCoordinate;
import timmax.tilegame.basemodel.gameobject.XYOffset;

import java.util.HashSet;
import java.util.Set;

//  ToDo:   Разложить класс TileOfMinesweeper на несколько и в т.ч. перенести сюда часть его функционала.
//          А для этой модели базовым должен стать этот класс.
//  ToDo:   После полного отказа от класса TileOfMinesweeper, удалить его.
public class MinesweeperGameObjectStateAutomaton extends OneTileGameObjectStateAutomaton implements IMinesweeperGameObjectState {
    protected Set<MinesweeperGameObjectStateAutomaton> neighbourSet; // Соседние плитки
    protected int countOfMinesInNeighbours;

    final MGOSMineIsNotOpenedWithFlag mineIsNotOpenedWithFlag;
    final MGOSMineIsNotOpenedWithoutFlag mineIsNotOpenedWithoutFlag;
    final MGOSMineIsOpened mineIsOpened;
    final MGOSNoMineIsNotOpenedWithFlag noMineIsNotOpenedWithFlag;
    final MGOSNoMineIsNotOpenedWithoutFlag noMineIsNotOpenedWithoutFlag;
    final MGOSNoMineIsOpened noMineIsOpened;

    private MinesweeperGameObjectStateAutomaton(OneTileGameObject oneTileGameObject) {
        super(oneTileGameObject);

        mineIsNotOpenedWithFlag = new MGOSMineIsNotOpenedWithFlag(this);
        mineIsNotOpenedWithoutFlag = new MGOSMineIsNotOpenedWithoutFlag(this);
        mineIsOpened = new MGOSMineIsOpened(this);
        noMineIsNotOpenedWithFlag = new MGOSNoMineIsNotOpenedWithFlag(this);
        noMineIsNotOpenedWithoutFlag = new MGOSNoMineIsNotOpenedWithoutFlag(this);
        noMineIsOpened = new MGOSNoMineIsOpened(this);
    }

    public MinesweeperGameObjectStateAutomaton(OneTileGameObject oneTileGameObject, boolean isMine) {
        this(oneTileGameObject);
        if (isMine) {
            setCurrentState(mineIsNotOpenedWithoutFlag);
        } else {
            setCurrentState(noMineIsNotOpenedWithoutFlag);
        }
    }

    protected void initNeighbourSet() {
        neighbourSet = new HashSet<>();
        countOfMinesInNeighbours = 0;
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                if (dx == 0 && dy == 0) {
                    continue;
                }
                XYCoordinate xyCoordinateOfNeighbour;
                try {
                    xyCoordinateOfNeighbour =
                            getXyCoordinate()
                                    .getXYCoordinateByOffset(
                                            new XYOffset(dx, dy),
                                            getOneTileGameObject()
                                                    .getOneTileGameObjectsPlacement()
                                                    .getWidthHeightSizes()
                                    )
                    ;
                } catch (XYCoordinateIsOutOfRangeException e) {
                    continue;
                }
                //  ToDo:   По координатам "соседа" (xyCoordinateOfNeighbour) нужно найти объект
                //          (т.е. Set с одним элементом)
                Set<OneTileGameObjectStateAutomaton> oneTileGameObjectStateAutomatonSet =
                        getOneTileGameObject()
                                .getOneTileGameObjectsPlacement()
                                .getOneTileGameObjectStateAutomatonSetInXYCoordinate(xyCoordinateOfNeighbour);
                if (oneTileGameObjectStateAutomatonSet.size() != 1) {
                    throw new RuntimeException("Должен быть один элемент во множестве oneTileGameObjectStateAutomatonSet, но oneTileGameObjectStateAutomatonSet = " + oneTileGameObjectStateAutomatonSet);
                }

                MinesweeperGameObjectStateAutomaton neighbour = (MinesweeperGameObjectStateAutomaton) (oneTileGameObjectStateAutomatonSet.stream().findFirst().orElse(null));

                neighbourSet.add(neighbour);
                countOfMinesInNeighbours += neighbour.getOneOrZeroMines();
            }
        }
    }

    protected Set<MinesweeperGameObjectStateAutomaton> getNeighbourSet() {
        if (neighbourSet == null) {
            initNeighbourSet();
        }
        return neighbourSet;
    }

    @Override
    public MinesweeperGameObjectState getOneTileGameObjectState() {
        return (MinesweeperGameObjectState) super.getOneTileGameObjectState();
    }

    @Override
    public int getOneOrZeroMines() {
        return getOneTileGameObjectState().getOneOrZeroMines();
    }

    @Override
    public void open() {
        getOneTileGameObjectState().open();
    }

    @Override
    public void inverseFlag() {
        getOneTileGameObjectState().inverseFlag();
    }
}

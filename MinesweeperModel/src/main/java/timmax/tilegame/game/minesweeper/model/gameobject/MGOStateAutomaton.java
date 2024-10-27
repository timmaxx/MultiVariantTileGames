package timmax.tilegame.game.minesweeper.model.gameobject;

import timmax.tilegame.basemodel.exception.XYCoordinateIsOutOfRangeException;
import timmax.tilegame.basemodel.gameobject.GameObject;
import timmax.tilegame.basemodel.gameobject.GameObjectStateAutomaton;
import timmax.tilegame.basemodel.gameobject.XYCoordinate;
import timmax.tilegame.basemodel.gameobject.XYOffset;

import java.util.HashSet;
import java.util.Set;

//  ToDo:   Разложить класс TileOfMinesweeper на несколько и в т.ч. перенести сюда часть его функционала.
//          А для этой модели базовым должен стать этот класс.
//  ToDo:   После полного отказа от класса TileOfMinesweeper, удалить его.
public class MGOStateAutomaton extends GameObjectStateAutomaton implements MGOState {
    protected Set<MGOStateAutomaton> neighbourSet; // Соседние плитки
    protected int countOfMinesInNeighbours;

    final MGOSMineIsNotOpenedWithFlag mineIsNotOpenedWithFlag;
    final MGOSMineIsNotOpenedWithoutFlag mineIsNotOpenedWithoutFlag;
    final MGOSMineIsOpened mineIsOpened;
    final MGOSNoMineIsNotOpenedWithFlag noMineIsNotOpenedWithFlag;
    final MGOSNoMineIsNotOpenedWithoutFlag noMineIsNotOpenedWithoutFlag;
    final MGOSNoMineIsOpened noMineIsOpened;

    private MGOStateAutomaton(GameObject gameObject) {
        super(gameObject);

        mineIsNotOpenedWithFlag = new MGOSMineIsNotOpenedWithFlag(this);
        mineIsNotOpenedWithoutFlag = new MGOSMineIsNotOpenedWithoutFlag(this);
        mineIsOpened = new MGOSMineIsOpened(this);
        noMineIsNotOpenedWithFlag = new MGOSNoMineIsNotOpenedWithFlag(this);
        noMineIsNotOpenedWithoutFlag = new MGOSNoMineIsNotOpenedWithoutFlag(this);
        noMineIsOpened = new MGOSNoMineIsOpened(this);
    }

    public MGOStateAutomaton(GameObject gameObject, boolean isMine) {
        this(gameObject);
        if (isMine) {
            setCurrentState(mineIsNotOpenedWithoutFlag);
        } else {
            setCurrentState(noMineIsNotOpenedWithoutFlag);
        }
    }

    protected void setCurrentStateMineIsNotOpenedWithoutFlag() {
        setCurrentState(mineIsNotOpenedWithoutFlag);
    }

    protected void setCurrentStateMineIsOpened() {
        setCurrentState(mineIsOpened);
    }

    protected void setCurrentStateMineIsNotOpenedWithFlag() {
        setCurrentState(mineIsNotOpenedWithFlag);
    }

    protected void setCurrentStateNoMineIsNotOpenedWithoutFlag() {
        setCurrentState(noMineIsNotOpenedWithoutFlag);
    }

    protected void setCurrentStateNoMineIsOpened() {
        setCurrentState(noMineIsOpened);
    }

    protected void setCurrentStateNoMineIsNotOpenedWithFlag() {
        setCurrentState(noMineIsNotOpenedWithFlag);
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
                                            getGameObject()
                                                    .getGameObjectsPlacement()
                                                    .getWidthHeightSizes()
                                    )
                    ;
                } catch (XYCoordinateIsOutOfRangeException e) {
                    continue;
                }
                Set<GameObjectStateAutomaton> gameObjectStateAutomatonSet =
                        getGameObject()
                                .getGameObjectsPlacement()
                                .getGameObjectStateAutomatonSetFilteredByXYCoordinate(xyCoordinateOfNeighbour);
                if (gameObjectStateAutomatonSet.size() != 1) {
                    throw new RuntimeException("Должен быть один элемент во множестве gameObjectStateAutomatonSet, но gameObjectStateAutomatonSet = " + gameObjectStateAutomatonSet);
                }

                MGOStateAutomaton neighbour = (MGOStateAutomaton) (gameObjectStateAutomatonSet.stream().findFirst().orElse(null));

                neighbourSet.add(neighbour);
                countOfMinesInNeighbours += neighbour.getOneOrZeroMines();
            }
        }
    }

    protected Set<MGOStateAutomaton> getNeighbourSet() {
        if (neighbourSet == null) {
            initNeighbourSet();
        }
        return neighbourSet;
    }

    @Override
    public AbstractMGOState getGameObjectState() {
        return (AbstractMGOState) super.getGameObjectState();
    }

    @Override
    public int getOneOrZeroMines() {
        return getGameObjectState().getOneOrZeroMines();
    }

    @Override
    public void open() {
        getGameObjectState().open();
    }

    @Override
    public void inverseFlag() {
        getGameObjectState().inverseFlag();
    }
}

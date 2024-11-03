package timmax.tilegame.basemodel.placement.placementstate;

import timmax.tilegame.basemodel.placement.gameobject.GameObject;
import timmax.tilegame.basemodel.placement.gameobject.GameObjectStateAutomaton;
import timmax.tilegame.basemodel.placement.primitives.WidthHeightSizes;
import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;
import timmax.tilegame.basemodel.protocol.server.GameMatch;
import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

//  Расположение игровых объектов (матча).
//  Является базовым классом (хранящим данные и содержащим методы, но при этом не наследуемым)
//  как для верифицируемого, так и для не верифицируемой Расстановки.
public final class GameObjectsPlacementBaseInstance {
    private final GameMatch gameMatch;

    //  Множество всех конкретных объектов расстановки как состояний этих объектов.
    private final Set<GameObjectStateAutomaton> gameObjectStateAutomatonSet;

    //  Ширина и высота поля.
    private final WidthHeightSizes widthHeightSizes;

    private GameObjectsPlacementBaseInstance(
            GameMatch gameMatch,
            WidthHeightSizes widthHeightSizes,
            Set<GameObjectStateAutomaton> gameObjectStateAutomatonSet) {
        this.gameMatch = gameMatch;
        this.widthHeightSizes = widthHeightSizes;
        this.gameObjectStateAutomatonSet = gameObjectStateAutomatonSet;
    }

    //  При таком конструкторе, WidthHeightSizes поступает как параметр и не может быть изменён в процессе заполнения.
    public GameObjectsPlacementBaseInstance(
            GameMatch gameMatch,
            WidthHeightSizes widthHeightSizes) {
        this(gameMatch, widthHeightSizes, new HashSet<>());
    }

    //  При таком конструкторе, WidthHeightSizes будет вычисляться каждый раз при добавлении объекта.
    public GameObjectsPlacementBaseInstance(
            GameMatch gameMatch) {
        this(gameMatch, new WidthHeightSizes(1, 1));
    }

    public GameMatch getGameMatch() {
        return gameMatch;
    }

    public GameType getGameType() {
        return gameMatch.getGameType();
    }

    public WidthHeightSizes getWidthHeightSizes() {
        return widthHeightSizes;
    }

    public Set<GameObjectStateAutomaton> getGameObjectStateAutomatonSet() {
        return gameObjectStateAutomatonSet;
    }

    public Set<GameObjectStateAutomaton> getGameObjectStateAutomatonSetFilteredByXYCoordinate(XYCoordinate xyCoordinate) {
        return gameObjectStateAutomatonSet
                .stream()
                .filter(gosa -> gosa.getXyCoordinate().equals(xyCoordinate))
                .collect(Collectors.toSet());
    }

    public Set<GameObject> getGameObjectSetFilteredByXYCoordinate(XYCoordinate xyCoordinate) {
        return gameObjectStateAutomatonSet
                .stream()
                .filter(gosa -> gosa.getXyCoordinate().equals(xyCoordinate))
                .map(GameObjectStateAutomaton::getGameObject)
                .collect(Collectors.toSet());
    }

    public Set<GameObject> getGameObjectSetFilteredByGameObjectClass(Class<? extends GameObject> gameObjectClass) {
        return gameObjectStateAutomatonSet
                .stream()
                .map(GameObjectStateAutomaton::getGameObject)
                .filter(gameObject -> gameObject.getClass().equals(gameObjectClass))
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "GameObjectsPlacementAbstract{" +
                "gameMatch=" + gameMatch +
                ", gameObjectStateAutomatonSet=" + gameObjectStateAutomatonSet +
                ", widthHeightSizes=" + widthHeightSizes +
                '}';
    }
}

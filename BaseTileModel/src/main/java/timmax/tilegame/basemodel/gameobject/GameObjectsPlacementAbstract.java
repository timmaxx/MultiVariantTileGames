package timmax.tilegame.basemodel.gameobject;

import timmax.tilegame.basemodel.protocol.server.GameMatch;
import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

//  Расположение игровых объектов (матча).
//  Является базовым родительским классом как для верифицированного, так и для не верифицированного размещения.
public abstract class GameObjectsPlacementAbstract {
    private final GameMatch gameMatch;

    //  Множество всех конкретных объектов расстановки как состояний этих объектов.
    //      Например, для Шахмат:
    //          Король, ферзь1, слон1, слон2, конь1, конь2, ладья1, ладья2, пешка1, ... пешка8.
    //      Например, для Шашек:
    //          Шашка1, ... шашка12, дамка1, ... дамкаN.
    //      Например, для Сапёра:
    //          Закрытое поле1, флаг1, открытое поле 1 (без мины), мина1.
    //      Например, для Сокобан:
    //          Игрок, коробка1, стена1, дом1.
    protected final Set<GameObjectStateAutomaton> gameObjectStateAutomatonSet;

    //  Ширина и высота поля.
    private final WidthHeightSizes widthHeightSizes;

    protected GameObjectsPlacementAbstract(GameMatch gameMatch, WidthHeightSizes widthHeightSizes, Set<GameObjectStateAutomaton> gameObjectStateAutomatonSet) {
        this.gameMatch = gameMatch;
        this.widthHeightSizes = widthHeightSizes;
        this.gameObjectStateAutomatonSet = gameObjectStateAutomatonSet;
    }

    //  При таком конструкторе, WidthHeightSizes поступает как параметр и не может быть изменён в процессе заполнения.
    public GameObjectsPlacementAbstract(GameMatch gameMatch, WidthHeightSizes widthHeightSizes) {
        this(gameMatch, widthHeightSizes, new HashSet<>());
    }

    //  При таком конструкторе, WidthHeightSizes будет вычисляться каждый раз при добавлении объекта.
    public GameObjectsPlacementAbstract(GameMatch gameMatch) {
        this(gameMatch, new WidthHeightSizes(1, 1));
    }

    public final GameMatch getGameMatch() {
        return gameMatch;
    }

    public final GameType getGameType() {
        return gameMatch.getGameType();
    }

    public final WidthHeightSizes getWidthHeightSizes() {
        return widthHeightSizes;
    }

    public final Set<GameObjectStateAutomaton> getGameObjectStateAutomatonSetFilteredXYCoordinate(XYCoordinate xyCoordinate) {
        return gameObjectStateAutomatonSet
                .stream()
                .filter(gosa -> gosa.getXyCoordinate().equals(xyCoordinate))
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

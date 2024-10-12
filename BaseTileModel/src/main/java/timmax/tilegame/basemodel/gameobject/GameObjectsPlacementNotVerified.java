package timmax.tilegame.basemodel.gameobject;

import timmax.tilegame.basemodel.protocol.server.GameMatch;
import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.HashSet;
import java.util.Set;

//  Класс нужен для первичной инициализации размещения, когда:
//  - размер поля при инициализации может быть не известен.
//      (например, для игр у которых карта может иметь различные размеры -
//      тогда размеры поля будут динамически определены после расстановки всех объектов).
//  - не выполняется целостность расстановки, т.е. не может быть начальной расстановкой для старта матча:
//      - пустое поле (для некоторых типов игр).
//          Например, для Шахмат, Шашек, Сапёра, Сокобан.
//      - расстановка, заполненная объектами, не удовлетворяет правилам типа игры.
//          Например, для Шахмат:
//          - расстановка, на которой нет короля для хотя-бы одной из сторон.
//          - расстановка, на которой есть два и более короля для хотя-бы одной из сторон.
//  В экземпляре этого класса можно произвольно добавлять, удалять или изменять игровой объект, не взирая на правила.

//  Расположение игровых объектов (матча) без проверки целостности.
public class GameObjectsPlacementNotVerified {
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
    private final Set<GameObjectStateAutomaton> gameObjectStateAutomatonSet;

    //  Ширина и высота поля.
    private final WidthHeightSizes widthHeightSizes;

    public GameObjectsPlacementNotVerified(GameMatch gameMatch, WidthHeightSizes widthHeightSizes) {
        this.gameMatch = gameMatch;
        this.gameObjectStateAutomatonSet = new HashSet<>();
        this.widthHeightSizes = widthHeightSizes;
    }

    public GameMatch getGameMatch() {
        return gameMatch;
    }

    public GameType getGameType() {
        return gameMatch.getGameType();
    }

    public Set<GameObjectStateAutomaton> getGameObjectStateAutomatonSet() {
        return gameObjectStateAutomatonSet;
    }

    public WidthHeightSizes getWidthHeightSizes() {
        return widthHeightSizes;
    }

    public void add(GameObjectStateAutomaton gameObjectStateAutomaton) {
        if (!gameObjectStateAutomatonSet.add(gameObjectStateAutomaton)) {
            throw new RuntimeException("You cannot add gameObjectStateAutomaton if there is the same one.");
        }
    }
}

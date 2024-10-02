package timmax.tilegame.basemodel.gameobject;

import timmax.tilegame.basemodel.protocol.server.GameMatch;
import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.HashSet;
import java.util.Set;

//  Расположение игровых объектов (матча)
public class OneTileGameObjectsPlacement {
    //  Тип игры
    private final GameType gameType;

    //  Ширина и высота поля.
    private final WidthHeightSizes widthHeightSizes;

    //  Множество всех конкретных объектов расстановки как состояний этих объектов.
    //      Например, для Шахмат:
    //          Король, ферзь1, слон1, слон2, конь1, конь2, ладья1, ладья2, пешка1, ... пешка8.
    //      Например, для Шашек:
    //          Шашка1, ... шашка12, дамка1, ... дамкаN.
    //      Например, для Сапёра:
    //          Закрытое поле1, флаг1, открытое поле 1 (без мины), мина1.
    //      Например, для Сокобан:
    //          Игрок, коробка1, стена1, дом1.
    private final Set<OneTileGameObjectStateAutomaton> oneTileGameObjectStateAutomatonSet;

    private final int playerIndexOfCurrentMove;
    private GameMatch gameMatch;

    private MatchStatus matchStatus;

    public OneTileGameObjectsPlacement(
            GameType gameType,
            WidthHeightSizes widthHeightSizes,
            int playerIndexOfCurrentMove,
            Set<OneTileGameObjectStateAutomaton> oneTileGameObjectStateAutomatonSet
    ) {
        //  ToDo:   Нужно проверить, а соответствует-ли WidthHeightSizes и GameType.
        this.gameType = gameType;
        this.widthHeightSizes = widthHeightSizes;
        this.oneTileGameObjectStateAutomatonSet = new HashSet<>();

        if (playerIndexOfCurrentMove < 0 || playerIndexOfCurrentMove >= gameTypeCountOfGamers()) {
            //  ToDo:   Отдельный класс исключения сделать?
            throw new RuntimeException("Wrong playerIndexOfCurrentMove.");
        }
        this.playerIndexOfCurrentMove = playerIndexOfCurrentMove;

        this.matchStatus = new MatchStatus0Undefined();
        /*
        //  ToDo:   Нужно реализовать проверку на:
        //          - полный обзор всех объектов, относительно других объектов,
        //          - учитывать индекс игрока, чей ход следующий.
        //  ToDo:   Не забыть и проверку на возможно обязательное наличие каких-либо типов объектов - особенно,
        //          если в расстановке их нет.
        matchStatus = verifyAllGameObjects();
        */
    }

    public OneTileGameObjectsPlacement(
            GameType gameType,
            WidthHeightSizes widthHeightSizes,
            int playerIndexOfCurrentMove
    ) {
        this(gameType, widthHeightSizes, playerIndexOfCurrentMove, new HashSet<>());
    }

    //  Warning:(69, 12) Copy constructor does not copy field 'gameMatch'
    public OneTileGameObjectsPlacement(OneTileGameObjectsPlacement oneTileGameObjectsPlacement) {
        this(oneTileGameObjectsPlacement.gameType,
                oneTileGameObjectsPlacement.widthHeightSizes,
                oneTileGameObjectsPlacement.playerIndexOfCurrentMove,
                oneTileGameObjectsPlacement.oneTileGameObjectStateAutomatonSet
        );
    }

    public GameMatch getGameMatch() {
        return gameMatch;
    }

    public void setGameMatch(GameMatch gameMatch) {
        this.gameMatch = gameMatch;
    }

    public WidthHeightSizes getWidthHeightSizes() {
        return widthHeightSizes;
    }

    protected GameType getGameType() {
        return gameType;
    }

    protected int getPlayerIndexOfCurrentMove() {
        return playerIndexOfCurrentMove;
    }

    protected MatchStatus getMatchStatus() {
        return matchStatus;
    }

    protected void setMatchStatus(MatchStatus matchStatus) {
        this.matchStatus = matchStatus;
    }

    public int gameTypeCountOfGamers() {
        return gameType.getCountOfGamers();
    }

    public Set<OneTileGameObjectStateAutomaton> getOneTileGameObjectStateAutomatonSetInXYCoordinate(XYCoordinate xyCoordinate) {
        Set<OneTileGameObjectStateAutomaton> result = new HashSet<>();
        for (OneTileGameObjectStateAutomaton oneTileGameObjectStateAutomaton : oneTileGameObjectStateAutomatonSet) {
            if (oneTileGameObjectStateAutomaton.getXyCoordinate().equals(xyCoordinate)) {
                result.add(oneTileGameObjectStateAutomaton);
            }
        }
        return result;
    }

    public void add(OneTileGameObjectStateAutomaton oneTileGameObjectStateAutomaton) {
        if (!oneTileGameObjectStateAutomatonSet.add(oneTileGameObjectStateAutomaton)) {
            throw new RuntimeException("You cannot add oneTileGameObjectStateAutomaton if there is the same one.");
        }
    }

    //  Применить один игровой ход:
    //  - проверить, что статус == MatchStatus1Running, если нет создать исключение, если да, то:
    //  - внести изменения в расстановку,
    //  - рассчитать статус и выдать его.
    protected MatchStatus applyGameMove(GameMove gameMove) {
        if (!(matchStatus instanceof MatchStatus1Running)) {
            throw new RuntimeException("Status is " + matchStatus + ". It is impossible to apply game move.");
        }
        return applyGameMove_(gameMove);
    }

    protected MatchStatus applyGameMove_(GameMove gameMove) {
        return new MatchStatus0Undefined();
    }

    //  Расстановка всех элементов должна удовлетворять правилам типа игры.

    //      - ограниечение на наличие/количество/взаимное расположение объектов определённого типа как
    //        на всей доске, так и на определённых координатах.
    //        Например, для Шахмат не может быть, что-бы:
    //          - хотя-бы у одной из сторон не было-бы короля или королей было-бы несколько,
    protected void verifyOneTileGameObjectClass_XYCoordinateSet(Class<OneTileGameObject> oneTileGameObjectClass, Set<XYCoordinate> xyCoordinateSet) {
    }

    //      - ограничение на перечень типов объектов, которые могут находиться на одних координатах.
    //          Например, для Шахмат не может быть, что-бы:
    //              - на одной плитке был бы больше чем один объект.
    //          Например, для Сокобан не может быть, что-бы:
    //              - на одной клетке был-бы:
    //                  - стена и стена,
    //                  - стена и дом,
    //                  - стена и ящик,
    //                  - стена и игрок,
    //                  - дом и дом,
    //                  - ящик и ящик,
    //                  - ящик и игрок,
    //                  - игрок и игрок.
    protected void verifyXYCoordinateSet_OneTileGameObjectClassSet(XYCoordinate xyCoordinate, Set<Class<OneTileGameObject>> oneTileGameObjectClassSet) {
    }

    //      - ограничения на взаимное расположение всех объектов:
    //          Например, для Шахмат не может быть, что-бы:
    //              - короли обоих противников были-бы одновременно под боем.
    //          И в т.ч. нужно вычислить статус матча и вернуть его.
    protected MatchStatus verifyAllGameObjects() {
        return new MatchStatus0Undefined();
    }

    /*
    @Override
    public String toString() {
        return "OneTileGameObjectsPlacement{" +
                super.toString() +
                "gameType=" + gameType +
                ", widthHeightSizes=" + widthHeightSizes +
                ", playerIndexOfCurrentMove=" + playerIndexOfCurrentMove +
                ", matchStatus=" + matchStatus +
                '}';
    }
    */

    @Override
    public String toString() {
        return "OneTileGameObjectsPlacement{" +
                // "gameType=" + gameType +
                // ", widthHeightSizes=" + widthHeightSizes +
                ", oneTileGameObjectStateAutomatonSet=" + oneTileGameObjectStateAutomatonSet +
                // ", playerIndexOfCurrentMove=" + playerIndexOfCurrentMove +
                // ", matchStatus=" + matchStatus +
                '}';
    }
}


//  ToDo:   Сделать четыре разных конструктора, у которых был-бы различный четвертый параметр.
//          Но можно не четыре, а хотя-бы два: 1 и 3. Но минимально и одного из четырёх будет достаточно.

//  Пример для Шахмат: (лаконичный вариант - Тип объекта -> множество координат)
//      Ладья, Set<a1, h1>
//      Конь, Set<b1, g1>
//      Слон, Set<c1, f1>
//      Ферзь, Set<d1>
//      Король, Set<e1>
//      Пешка, Set<a2, b2, c2, d2, e2, f2, g2, h2>

//  Пример для Шахмат: (так и даётся по шахматной нотации: Объект -> Координаты)
//      Король1, e1
//      Пешка1, a2
//      Пешка2, b2
//      Пешка3, c2
//      Ладья1, a1
//      Ладья2, h1

//  Пример для Шахмат: (Координаты -> Множество типов объектов)
//      a1, Set<Ладья>
//      b1, Set<Конь>
//      c1, Set<Слон>
//      d1, Set<Ферзь>
//      e1, Set<Король>
//      f1, Set<Слон>
//      g1, Set<Конь>
//      h1, Set<Ладья>
//      a2, Set<Пешка>
//      b2, Set<Пешка>
//      c2, Set<Пешка>
//      d2, Set<Пешка>
//      e2, Set<Пешка>
//      f2, Set<Пешка>
//      g2, Set<Пешка>
//      h2, Set<Пешка>

//  Пример для Шахмат: (Координаты -> Множество объектов (на этих координатах))
//      a1, <Ладья1>
//      e1, <Король1>
//      h1, <Ладья2>
//      a2, <Пешка1>
//      b2, <Пешка2>
//      c3, <Пешка3>

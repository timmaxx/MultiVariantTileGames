package timmax.tilegame.basemodel.gameobject;

import timmax.tilegame.basemodel.protocol.server.GameType;

//  Расположение игровых объектов (матча), верифицированное с правилами игры.
public class GameObjectsPlacement {
    private final GameObjectsPlacementNotVerified gameObjectsPlacementNotVerified;

    private final int playerIndexOfCurrentMove;

    private MatchStatus matchStatus;

    //  ToDo:   При вызове конструктора проверить параметр GameObjectsPlacementNotVerified
    //          на целостность и потом только инициализировать.
    public GameObjectsPlacement(
            GameObjectsPlacementNotVerified gameObjectsPlacementNotVerified,
            int playerIndexOfCurrentMove
    ) {
        //  ToDo:   Нужно проверить, а соответствует-ли WidthHeightSizes и GameType?

        this.gameObjectsPlacementNotVerified = gameObjectsPlacementNotVerified;

        if (playerIndexOfCurrentMove < 0 || playerIndexOfCurrentMove >= gameTypeCountOfGamers()) {
            //  ToDo:   Отдельный класс исключения сделать?
            throw new RuntimeException("Wrong playerIndexOfCurrentMove = " + playerIndexOfCurrentMove);
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

    public GameObjectsPlacement(GameObjectsPlacement gameObjectsPlacementNotVerified) {
        this.gameObjectsPlacementNotVerified = gameObjectsPlacementNotVerified.gameObjectsPlacementNotVerified;
        this.playerIndexOfCurrentMove = gameObjectsPlacementNotVerified.playerIndexOfCurrentMove;
        this.matchStatus = gameObjectsPlacementNotVerified.matchStatus;
    }

    public WidthHeightSizes getWidthHeightSizes() {
        return gameObjectsPlacementNotVerified.getWidthHeightSizes();
    }

    protected GameType getGameType() {
        return gameObjectsPlacementNotVerified.getGameType();
    }

    protected MatchStatus getMatchStatus() {
        return matchStatus;
    }

    protected GameObjectsPlacementNotVerified getGameObjectsPlacementNotVerified() {
        return gameObjectsPlacementNotVerified;
    }

    public int gameTypeCountOfGamers() {
        return getGameType().getCountOfGamers();
    }

    //  Применить один игровой ход и проверить, что статус == MatchStatus1Running.
    //  - Если нет создать исключение,
    //  - если да, то:
    //  -- внести изменения в расстановку,
    //  -- рассчитать статус и выдать его.
    protected MatchStatus applyGameMove(GameMove gameMove) {
        if (!(matchStatus instanceof MatchStatus1Running)) {
            throw new RuntimeException("Status is " + matchStatus + ". It is impossible to apply game move.");
        }
        return applyGameMove_(gameMove);
    }

    protected MatchStatus applyGameMove_(GameMove gameMove) {
        return new MatchStatus0Undefined();
    }

    @Override
    public String toString() {
        return "GameObjectsPlacement{" +
                "gameObjectsPlacementNotVerified=" + gameObjectsPlacementNotVerified +
                ", playerIndexOfCurrentMove=" + playerIndexOfCurrentMove +
                ", matchStatus=" + matchStatus +
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

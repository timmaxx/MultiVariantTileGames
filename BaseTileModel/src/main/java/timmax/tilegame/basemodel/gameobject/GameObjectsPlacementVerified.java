package timmax.tilegame.basemodel.gameobject;

import java.util.Set;
import java.util.stream.Collectors;

//  Расположение игровых объектов (матча) с проверкой целостности (и на этапе создания и при каждом ходе).
public class GameObjectsPlacementVerified extends GameObjectsPlacementAbstract {
    private int playerIndexOfCurrentMove;

    private MatchStatus matchStatus;

    public GameObjectsPlacementVerified(
            GameObjectsPlacementNotVerified gameObjectsPlacementNotVerified,
            int playerIndexOfCurrentMove) {
        super(gameObjectsPlacementNotVerified.getGameMatch(),
                gameObjectsPlacementNotVerified.getWidthHeightSizes(),
                gameObjectsPlacementNotVerified.gameObjectStateAutomatonSet
        );

        //  ToDo:   Нужно проверить:
        //          1. а соответствует-ли WidthHeightSizes и GameType?
        //          2. индекс игрока, делающего следующий ход должен быть от 0 до количества игроков - 1.
        //          3. что все объекты, стоят правильно:
        //              1. сами по себе
        //                  (например, для Шахмат пешки обоих игроков не могут располагаться на горизонталях 1 и 8),
        //              2. обязательное наличие каких-либо типов объектов и их количество
        //                  (например, для Шахмат:
        //                      - королей должно быть только по одному для каждой стороны).
        //              3. относительно других объектов и учитывая индекс игрока, чей ход следующий
        //                  (например, для Шахмат:
        //                      - короли каждой из сторон не могут быть под боем одновременно).
        //  ToDo:   4. Определить статус матча (идёт игра, окончена выигрешем/поражением, окончена ничьёй).

        //  2
        if (playerIndexOfCurrentMove < 0 || playerIndexOfCurrentMove >= gameTypeCountOfGamers()) {
            throw new RuntimeException("Wrong playerIndexOfCurrentMove = " + playerIndexOfCurrentMove);
        }
        this.playerIndexOfCurrentMove = playerIndexOfCurrentMove;

        //  4
        this.matchStatus = new MatchStatus0Undefined();
        // matchStatus = verifyAllGameObjects();
    }

    public int getPlayerIndexOfCurrentMove() {
        return playerIndexOfCurrentMove;
    }

    public MatchStatus getMatchStatus() {
        return matchStatus;
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

    public final Set<GameObject> getGameObjectSetFilteredByGameObjectClass(
            Class<? extends GameObject> gameObjectClass) {
        return gameObjectStateAutomatonSet
                .stream()
                .filter(gosa -> gosa.getGameObject().getClass().equals(gameObjectClass))
                .map(GameObjectStateAutomaton::getGameObject)
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return super.toString() + " | " + "GameObjectsPlacementVerified{" +
                "playerIndexOfCurrentMove=" + playerIndexOfCurrentMove +
                ", matchStatus=" + matchStatus +
                // ", gameObjectStateAutomatonSet=" + gameObjectStateAutomatonSet +
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

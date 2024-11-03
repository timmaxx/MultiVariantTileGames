package timmax.tilegame.basemodel.placement.placementstate;

import timmax.tilegame.basemodel.placement.gamemove.GameMove;
import timmax.tilegame.basemodel.placement.matchstatus.MatchStatus;
import timmax.tilegame.basemodel.placement.matchstatus.MatchStatus0Undefined;
import timmax.tilegame.basemodel.placement.matchstatus.MatchStatus1Running;

//  Расстановка игровых объектов (матча) с проверкой целостности (и на этапе создания и при каждом ходе).
public class GameObjectsPlacementVerifiedState extends GameObjectsPlacementAbstractState {
    //  Данные, доступные в состоянии "Целостное":
    private int playerIndexOfCurrentMove;
    private final MatchStatus matchStatus;

    public GameObjectsPlacementVerifiedState(GameObjectsPlacementStateAutomaton gameObjectsPlacementStateAutomaton) {
        this(gameObjectsPlacementStateAutomaton, 0);
    }

    public GameObjectsPlacementVerifiedState(
            GameObjectsPlacementStateAutomaton gameObjectsPlacementStateAutomaton,
            int playerIndexOfCurrentMove) {
        super(gameObjectsPlacementStateAutomaton);
        this.playerIndexOfCurrentMove = playerIndexOfCurrentMove;

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
        if (playerIndexOfCurrentMove < 0 || playerIndexOfCurrentMove >= getCountOfGamers()) {
            throw new RuntimeException("Wrong playerIndexOfCurrentMove = " + playerIndexOfCurrentMove);
        }
        this.playerIndexOfCurrentMove = playerIndexOfCurrentMove;

        //  4
        this.matchStatus = new MatchStatus0Undefined();
        // matchStatus = verifyAllGameObjects();
    }

    public MatchStatus getMatchStatus() {
        return matchStatus;
    }

    //  Применить один игровой ход и проверить, что статус == MatchStatus1Running.
    //  - Если нет, то создать исключение,
    //  - если да, то:
    //  -- внести изменения в расстановку,
    //  -- рассчитать статус и выдать его.
    @Override
    public MatchStatus makeGameMove(GameMove gameMove) {
        if (!(matchStatus instanceof MatchStatus1Running)) {
            throw new RuntimeException("Status is " + matchStatus + ". It is impossible to apply game move.");
        }

        //  ToDo:   !!!!!

        return matchStatus;
    }

    @Override
    public String toString() {
        return "GameObjectsPlacementStateVerified{" +
                "playerIndexOfCurrentMove=" + playerIndexOfCurrentMove +
                ", matchStatus=" + matchStatus +
                super.toString() +
                '}';
    }
}

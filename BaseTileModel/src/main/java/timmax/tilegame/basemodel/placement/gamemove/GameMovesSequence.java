package timmax.tilegame.basemodel.placement.gamemove;

import timmax.tilegame.basemodel.placement.placementstate.GameObjectsPlacementStateAutomaton;
import timmax.tilegame.basemodel.placement.matchstatus.MatchStatus;
import timmax.tilegame.basemodel.placement.matchstatus.MatchStatus1Running;
import timmax.tilegame.basemodel.placement.matchstatus.MatchStatus2GameOver;
import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.ArrayList;

//  Done:   Класс для хранения последовательности всех ходов матча:
//          - каждый ход этой последовательности должен соответствовать правилам типа игры.
//              Например, для Сокобан:
//                  - нельзя переместить игрока более чем на одну плитку по горизонтали или вертикали.
//              Например, для Шахмат:
//                  - нельзя переместить слона как коня или перепрыгнув ч/з фигуру.
//          - ход может быть принят, если он не приведёт к неправильной расстановке (примеры см. в описании предыдущего класса).
//          После каждого хода нужно проверять не достигнут-ли конец партии. А если достигнут, то зафиксировать
//          конец партии и успешность (победа/поражение) каждого игрока или ничья и не принимать следующие ходы.

//  Последовательность игровых ходов (матча) (с приложением к начальной Расстановке).
public abstract class GameMovesSequence extends GameMovesSequenceNotVerified {
    //  Расстановка после последнего хода, т.е. после того, как:
    //  1. на пустую доску расставили начальную расстановку (заданную в конструкторе),
    //  2. последовательно сделаны все ходы.
    private final GameObjectsPlacementStateAutomaton gameObjectsPlacementAfterLastMove;

    public GameMovesSequence(
            GameType gameType,
            ArrayList<GameMove[]> gameMoveArray_ListArray,
            int playerIndexOfFirstMove,
            int playerIndexOfLasstMove,
            GameObjectsPlacementStateAutomaton gameObjectsPlacementVerified) {
        super(gameType, gameMoveArray_ListArray, playerIndexOfFirstMove, playerIndexOfLasstMove);

        this.gameObjectsPlacementAfterLastMove = gameObjectsPlacementVerified;
        MatchStatus matchStatus = gameObjectsPlacementAfterLastMove.getMatchStatus();
        int indexOfGameCycle = 0;
        int indexOfPlayer = 0;
        //  ToDo:   Проверить входящий gameMoveArray_ListArray на правильность и в т.ч. "прошагать все ходы".
        for (int i = 0; i < gameMoveArray_ListArray.size(); i++) {
            for (int j = 0; j < gameTypeCountOfGamers(); j++) {
                if (!(matchStatus instanceof MatchStatus1Running)) {
                    throw new RuntimeException("MatchStatus has been " + matchStatus
                            + " since: index of game cycle = " + indexOfGameCycle + ", player = " + indexOfPlayer
                            + ". There is try to do a move after it: index of game cycle = " + i + ", player = " + j
                    );
                }
                if ((i == 0 && playerIndexOfFirstMove > j) ||
                        (j == gameTypeCountOfGamers() - 1 && getPlayerIndexOfNextAfterLastMove() < j)) {
                    continue;
                }
                GameMove gameMove = gameMoveArray_ListArray.get(i)[j];
                //  ToDo:   каждый ход должен соответствовать правилам типа игры
                //              Например, для Сокобан:
                //                  - нельзя переместить игрока более чем на одну плитку по горизонтали или вертикали.
                //              Например, для Шахмат:
                //                  - нельзя переместить слона как коня или перепрыгнув ч/з фигуру.
                matchStatus = gameObjectsPlacementAfterLastMove.makeGameMove(gameMove);
                if (matchStatus instanceof MatchStatus2GameOver) {
                    indexOfGameCycle = i;
                    indexOfPlayer = j;
                }
            }
        }
    }
}

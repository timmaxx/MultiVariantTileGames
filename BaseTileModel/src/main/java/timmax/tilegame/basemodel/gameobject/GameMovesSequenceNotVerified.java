package timmax.tilegame.basemodel.gameobject;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.ArrayList;

//  Последовательность игровых ходов (матча) (без приложения к начальной расстановке)
public abstract class GameMovesSequenceNotVerified {
    private final GameType gameType;
    //  Список массивов (1 или 2 длина массива) игровых ходов.
    //  Длина массива должна быть равной количеству игроков матча
    //  (для игр с одним игроком - длина массива 1, для игр с двумя игроками - длина массива - 2).
    private final ArrayList<GameMove[]> gameMoveArray_ListArray;
    //  Индекс игрока, чей ход первый (т.е. возможно, что первым ходит второй игрок).
    private final int playerIndexOfFirstMove;
    //  Индекс игрока, чей ход следующий после последнего хода (т.е. возможно, что первый ход сделал, а второй не сделал).
    private int playerIndexOfNextAfterLastMove;

    public GameMovesSequenceNotVerified(
            GameType gameType,
            ArrayList<GameMove[]> gameMoveArray_ListArray,
            int playerIndexOfFirstMove,
            int playerIndexOfNextAfterLastMove) {
        this.gameType = gameType;
        //  Проверить количество игроков в gameType: должно быть такое-же,
        //  как и длина массива GameMove[] для каждого элемента ArrayList.
        for (GameMove[] gameMoveArray : gameMoveArray_ListArray) {
            if (gameTypeCountOfGamers() != gameMoveArray.length) {
                throw new RuntimeException(
                        "Count of gamers is " + gameTypeCountOfGamers() + "," +
                                " but gameMoveArray.length is " + gameMoveArray.length + " for one or more elements.");
            }
        }
        this.gameMoveArray_ListArray = gameMoveArray_ListArray;
        this.playerIndexOfFirstMove = playerIndexOfFirstMove;
        this.playerIndexOfNextAfterLastMove = playerIndexOfNextAfterLastMove;
    }

    public GameType getGameType() {
        return gameType;
    }

    public ArrayList<GameMove[]> getGameMoveArray_ListArray() {
        return gameMoveArray_ListArray;
    }

    public int getPlayerIndexOfFirstMove() {
        return playerIndexOfFirstMove;
    }

    public int getPlayerIndexOfNextAfterLastMove() {
        return playerIndexOfNextAfterLastMove;
    }

    public int gameTypeCountOfGamers() {
        return gameType.getCountOfGamers();
    }
}

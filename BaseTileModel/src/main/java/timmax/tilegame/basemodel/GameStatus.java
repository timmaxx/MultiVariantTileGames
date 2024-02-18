package timmax.tilegame.basemodel;

// Текущий статус игры
public enum GameStatus {
    GAME,    // Идёт игра
    VICTORY, // Игра окончена выигрышем
    DEFEAT,  // Игра окончена проигрышем
    FORCE_RESTART_OR_CHANGE_LEVEL // Рестарт или досрочный переход на другой уровень
}

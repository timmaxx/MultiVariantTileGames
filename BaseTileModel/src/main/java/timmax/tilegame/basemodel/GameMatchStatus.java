package timmax.tilegame.basemodel;

//  Текущий статус матча относительно того стартовал он или нет, на паузе или нет, достигнут конец матча -
//  т.е. в работе-ли на сервере этот матч и можно-ли применять к нему игровые события, которые поступают
//  от контролов игровых клиентов.
//  Также смотри timmax.tilegame.basemodel.gameobject.MatchStatus
public enum GameMatchStatus {
    NOT_STARTED,    //  Не стартовал
    GAME,           //  Матч в процессе игры
    PAUSE,          //  Пауза

    //  ToDo:   Создать GAME_OVER, удалить VICTORY и DEFEAT.
    //          Для игр с одним игроком можно было-бы и оставить варианты VICTORY и DEFEAT, но для двух игроков
    //          статус матча (без применения к игроку) будет только GAME_OVER.
    VICTORY,        //  Игра окончена выигрышем
    DEFEAT,         //  Игра окончена проигрышем

    //  ToDo:   Этот статус не похож на правильный. Он скорее для автоматизации перевода статуса.
    FORCE_RESTART_OR_CHANGE_LEVEL // Рестарт или досрочный переход на другой уровень
}
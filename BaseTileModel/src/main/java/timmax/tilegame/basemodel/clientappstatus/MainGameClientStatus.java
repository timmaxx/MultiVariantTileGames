package timmax.tilegame.basemodel.clientappstatus;

public enum MainGameClientStatus {
    NO_CONNECT,             // 1. Запущен, подключения к серверу нет совсем.
    CONNECT_NON_IDENT,      // 2. Подключение есть, но сервер не идентифицировал, не аутентифицировал и не авторизовал пользователя.
    CONNECT_AUTHORIZED,     // 3. Сервер идентифицировал, аутентифицировал и авторизовал пользователя.
    /*
    GAME_TYPE_SELECTED,     // 4. Игра (как тип игры) выбрана.
    GAME_SERIES_SELECTED,   // 5. Выбрана серия партий (доигрываем ранее созданную или играем новую).
    GAME_MATCH_SELECTED,    // 6. Выбрана конкретная партия в серии из доступных.
    GAME_IS_PLAYING         // 7. Игра.
    */
}
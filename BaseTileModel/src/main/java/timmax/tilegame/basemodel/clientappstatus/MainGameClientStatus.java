package timmax.tilegame.basemodel.clientappstatus;

public enum MainGameClientStatus {
    NO_CONNECT,             //  Запущен, подключения к серверу нет совсем.
    CONNECT_NON_IDENT,      //  Подключение есть, но сервер не идентифицировал, не аутентифицировал и не авторизовал пользователя.
    CONNECT_AUTHORIZED,     //  Сервер идентифицировал, аутентифицировал и авторизовал пользователя.
    GET_GAME_TYPE_SET,      //  Сервер передал клиенту перечень типов игр.
    GAME_TYPE_SELECT,       //  Игра (как тип игры) выбрана.
    //GAME_SERIES_SELECTED,   //  Выбрана серия партий (доигрываем ранее созданную или играем новую).
    GAME_MATCH_SELECTED,    //  Выбрана конкретная партия в серии из доступных.
    GAME_IS_PLAYING         //  Игра.
}
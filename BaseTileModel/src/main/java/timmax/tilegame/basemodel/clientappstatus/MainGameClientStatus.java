package timmax.tilegame.basemodel.clientappstatus;

public enum MainGameClientStatus {
    NO_CONNECT,             //  Запущен, подключения к серверу нет совсем.
    CONNECT_NON_IDENT,      //  Подключение есть, но сервер не идентифицировал, не аутентифицировал и не авторизовал пользователя.
    CONNECT_AUTHORIZED,     //  Сервер идентифицировал, аутентифицировал и авторизовал пользователя.
    GAME_TYPE_SET_SELECTED, //  Выбран перечень типов игр.
    GAME_TYPE_SELECTED,     //  Выбран тип игры.
    GAME_MATCH_SET_SELECTED,//  Выбрана перечень партий (доигрываем ранее созданную или играем новую).
    GAME_MATCH_SELECTED,    //  Выбрана партия (создан экземпляр класса модели и он в сотоянии настройка).
    GAME_IS_PLAYING         //  Игра (экземпляр класса модели в сотоянии игра).
}

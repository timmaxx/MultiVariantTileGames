package timmax.tilegame.basemodel.protocol;

// ToDo: Удалить перечисление.
public enum TypeOfEvent {
    CLOSE,
    OPEN,
    LOGOUT,
    LOGIN,
    FORGET_GAME_TYPE_SET,
    GET_GAME_TYPE_SET,
    FORGET_GAME_TYPE,
    SELECT_GAME_TYPE,/*
    CREATE_GAME_SERIES,
    GAME_SERIES_MAP,
    SELECT_GAME_SERIES,
    GAME_MATCH_MAP,
    SELECT_GAME_MATCH,
    PLAYER_SIDE_MAP,
    SELECT_PLAYER_SIDE,
    DECLARE_READINESS,
    READINESS_MAP,
*/
    ADD_VIEW,
    CREATE_NEW_GAME,
    GAME_EVENT
}
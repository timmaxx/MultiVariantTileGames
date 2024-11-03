package timmax.tilegame.basemodel.placement.placementstate;

import timmax.tilegame.basemodel.placement.gameobject.GameObjectStateAutomaton;
import timmax.tilegame.basemodel.placement.primitives.WidthHeightSizes;
import timmax.tilegame.basemodel.placement.gamemove.GameMove;
import timmax.tilegame.basemodel.placement.matchstatus.MatchStatus;

//  Интерфейс перечисляет методы, доступные для Расстановки.
public interface GameObjectsPlacementState {
    //  Методы в состоянии "Не целостное":

    //  Добавить игровой объект в расстановку (без проверки целостности).
    //  ToDo:   Может бросать исключения, если объект будет ставиться по координатам:
    //          - вне поля,
    //          - другого объекта такого-же типа,
    //          - другого объекта, если их типы конфликтуют.
    //          Такие исключения относятся к примитивным проверкам на целостность.
    void add(GameObjectStateAutomaton gameObjectStateAutomaton);

    //  Сделать проверку целостности расстановки на данный момент и включить её после каждого хода.
    //  ToDo:   Может бросать исключения, если проверка на примитивную целостность не успешна.
    //  ToDo:   Может бросать исключения, если проверка на целостность не успешна.
    //  ToDo:   Следует сделать возвращающим MatchStatus.
    void turnOnVerifable(int playerIndexOfCurrentMove);

    //  Сделать проверку целостности расстановки на данный момент и включить её после каждого хода.
    //  С введением альтернативной ширины и высоты поля.
    //  (ширина и/или высота должны быть не меньше, чем уже получилось при добавлении объектов ранее).
    //  ToDo:   Может бросать исключения, если проверка на примитивную целостность не успешна.
    //  ToDo:   Может бросать исключения, если проверка на целостность не успешна.
    //  ToDo:   Следует сделать возвращающим MatchStatus.
    void turnOnVerifable(int playerIndexOfCurrentMove, WidthHeightSizes widthHeightSizes);


    //  Методы в состоянии "Целостное":

    //  ToDo:   Может бросать исключения, если проверка на примитивную целостность не успешна.
    //  ToDo:   Может бросать исключения, если проверка на целостность не успешна.
    //  ToDo:   Может бросать исключения, если матч сыгран в ничью или победа/проигрыш.
    MatchStatus makeGameMove(GameMove gameMove);
}

package timmax.tilegame.basemodel.gameobject;

//  Интерфейс перечисляет методы, доступные для Расстановки.
public interface GameObjectsPlacementState {
    //  Методы в состоянии "Не целостное":

    //  Добавляется игровой объект в расстановку
    void add(GameObjectStateAutomaton gameObjectStateAutomaton);

    //  Сделать расстановку целостной на данный момент и после каждого хода.
    void turnOnVerifable(int playerIndexOfCurrentMove);

    //  Сделать расстановку целостной на данный момент и после каждого хода.
    //  С введением альтернативной ширины и высоты поля.
    //  (ширина и/или высота должны быть не меньше, чем уже получилось при расстановке).
    void turnOnVerifable(int playerIndexOfCurrentMove, WidthHeightSizes widthHeightSizes);


    //  Методы в состоянии "Целостное":

    MatchStatus makeGameMove(GameMove gameMove);
}

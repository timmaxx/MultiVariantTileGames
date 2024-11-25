package timmax.tilegame.minesweeper.model.placement.gameobject;

//  Интерфейс определяет какие действия можно делать с отдельным объетом игры Сапёр.
public interface MGOState {
    int getOneOrZeroMines();

    void open();

    void inverseFlag();
}

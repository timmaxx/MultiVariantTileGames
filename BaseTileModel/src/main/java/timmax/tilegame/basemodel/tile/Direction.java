package timmax.tilegame.basemodel.tile;

// Направление смещения
public enum Direction {
    UNKNOWN,
    LEFT,
    RIGHT,
    UP,
    DOWN;

    // Дать противоположное направление
    public Direction not() {
        if (this == DOWN) {
            return UP;
        } else if (this == UP) {
            return DOWN;
        } else if (this == LEFT) {
            return RIGHT;
        } else if (this == RIGHT) {
            return LEFT;
        } else return UNKNOWN;
    }

    // Направление, преобразованное в смещение с условием только на одну ячейку только в одну из сторон
    public DxDy getDxDy() {
        int dx = 0;
        int dy = 0;
        if (this == UP) {
            dy--;
        } else if (this == DOWN) {
            dy++;
        } else if (this == LEFT) {
            dx--;
        } else if (this == RIGHT) {
            dx++;
        }
        return new DxDy(dx, dy);
    }
}
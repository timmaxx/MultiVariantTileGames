package timmax.tilegame.basemodel.tile;

// Смещение для объекта игры за один шаг.
//  ToDo    Удалить, т.к. есть класс XYOffset.
public class DxDy {
    protected int dx;
    protected int dy;

    public DxDy(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}

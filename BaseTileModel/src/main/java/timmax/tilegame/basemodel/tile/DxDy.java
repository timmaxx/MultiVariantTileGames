package timmax.tilegame.basemodel.tile;

// Смещение для объекта игры за один шаг.
public class DxDy {
    protected int x;
    protected int y;


    public DxDy( int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX( ) {
        return x;
    }

    public int getY( ) {
        return y;
    }
}
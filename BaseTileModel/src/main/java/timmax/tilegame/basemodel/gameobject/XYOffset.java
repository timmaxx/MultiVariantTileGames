package timmax.tilegame.basemodel.gameobject;

//  Смещение на поле
public class XYOffset {
    public static XYOffset TO_UP = new XYOffset(0, -1);
    public static XYOffset TO_DOWN = new XYOffset(0, 1);
    public static XYOffset TO_LEFT = new XYOffset(-1, 0);
    public static XYOffset TO_RIGHT = new XYOffset(1, 0);

    private final int dx;
    private final int dy;

    public XYOffset(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public XYOffset getOpposite() {
        return new XYOffset(-dx, -dy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XYOffset xyOffset = (XYOffset) o;

        if (dx != xyOffset.dx) return false;
        return dy == xyOffset.dy;
    }

    @Override
    public int hashCode() {
        int result = dx;
        result = 31 * result + dy;
        return result;
    }

    @Override
    public String toString() {
        return "XYOffset{" +
                "dx=" + dx +
                ", dy=" + dy +
                '}';
    }
}

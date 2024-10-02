package timmax.tilegame.basemodel.gameobject;

import java.util.Random;

//  xy-координаты
public final class XYCoordinate {
    private static final Random random = new Random();
    private final int x;
    private final int y;

    public XYCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public XYCoordinate getXYCoordinateByOffset(XYOffset XYOffset, WidthHeightSizes widthHeightSizes) {
        if (XYOffset.getDx() == 0 && XYOffset.getDy() == 0) {
            return this;
        }
        XYCoordinate xyCoordinate = new XYCoordinate(x + XYOffset.getDx(), y + XYOffset.getDy());
        widthHeightSizes.validateXYCoordinate(xyCoordinate);
        return xyCoordinate;
    }

    public static XYCoordinate getRandom(WidthHeightSizes widthHeightSizes)
    {
        int x = random.nextInt(widthHeightSizes.width());
        int y = random.nextInt(widthHeightSizes.height());
        return new XYCoordinate(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XYCoordinate that = (XYCoordinate) o;

        if (x != that.x) return false;
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "XYCoordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

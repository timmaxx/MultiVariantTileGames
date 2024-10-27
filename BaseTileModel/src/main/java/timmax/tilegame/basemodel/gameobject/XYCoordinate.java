package timmax.tilegame.basemodel.gameobject;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Random;

import static timmax.tilegame.basemodel.gameobject.XYOffset.NO_OFFSET;

//  ToDo:   Отделить функциональность DTO и основных функций в разные классы.
//          Класс реализует Externalizable для того, чтобы быть DTO.
//  xy-координаты
public final class XYCoordinate implements Externalizable {
    private static final Random random = new Random();

    private int x;
    private int y;

    public XYCoordinate() {
    }

    public XYCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //  В методе
    //  ViewMainFieldJfx :: GameStackPane getCellByGameEventOneTile(GameEventOneTile gameEventOneTile)
    //  используется метод, поэтому public.
    public int getX() {
        return x;
    }

    //  В методе
    //  ViewMainFieldJfx :: GameStackPane getCellByGameEventOneTile(GameEventOneTile gameEventOneTile)
    //  используется метод, поэтому public.
    public int getY() {
        return y;
    }

    public XYCoordinate getXYCoordinateByOffset(XYOffset xyOffset, WidthHeightSizes widthHeightSizes) {
        if (xyOffset.equals(NO_OFFSET)) {
            return this;
        }

        XYCoordinate xyCoordinate = new XYCoordinate(x + xyOffset.getDx(), y + xyOffset.getDy());
        widthHeightSizes.validateXYCoordinate(xyCoordinate);
        return xyCoordinate;
    }

    public static XYCoordinate getRandom(WidthHeightSizes widthHeightSizes) {
        return new XYCoordinate(random.nextInt(widthHeightSizes.getWidth()), random.nextInt(widthHeightSizes.getHeight()));
    }

    public boolean hasEqualX(XYCoordinate xyCoordinate) {
        return x == xyCoordinate.x;
    }

    public boolean hasEqualY(XYCoordinate xyCoordinate) {
        return y == xyCoordinate.y;
    }

    public boolean hasXLesser(XYCoordinate xyCoordinate) {
        return x < xyCoordinate.x;
    }

    public boolean hasXGreater(XYCoordinate xyCoordinate) {
        return x > xyCoordinate.x;
    }

    public boolean hasYLesser(XYCoordinate xyCoordinate) {
        return y < xyCoordinate.y;
    }

    public boolean hasYGreater(XYCoordinate xyCoordinate) {
        return y > xyCoordinate.y;
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(x);
        out.writeInt(y);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        x = in.readInt();
        y = in.readInt();
    }
}

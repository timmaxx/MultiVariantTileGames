package timmax.tilegame.basemodel.placement.primitives;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Random;

import static timmax.tilegame.basemodel.placement.primitives.XYOffset.NO_OFFSET;

//  ToDo:   Разделить функциональность DTO и основных функций в разные классы.
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

    //  В методах
    //  WidthHeightSizes :: void recalc(XYCoordinate xyCoordinate)
    //  ViewMainFieldJfx :: GameStackPane getCellByGameEventOneTile(GameEventOneTile gameEventOneTile)
    //  GameStackPane :: GameStackPane(XYCoordinate xyCoordinate, int cellSize, boolean showGrid, boolean showCoordinates)
    //  используется метод, поэтому public.
    //  Нужно стараться не использовать этот метод!
    public int getX() {
        return x;
    }

    //  В методах
    //  WidthHeightSizes :: void recalc(XYCoordinate xyCoordinate)
    //  ViewMainFieldJfx :: GameStackPane getCellByGameEventOneTile(GameEventOneTile gameEventOneTile)
    //  GameStackPane :: GameStackPane(XYCoordinate xyCoordinate, int cellSize, boolean showGrid, boolean showCoordinates)
    //  используется метод, поэтому public.
    //  Нужно стараться не использовать этот метод!
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
        return hasEqualX(xyCoordinate.x);
    }

    public boolean hasEqualX(int x) {
        return this.x == x;
    }

    public boolean hasEqualY(XYCoordinate xyCoordinate) {
        return hasEqualY(xyCoordinate.y);
    }

    public boolean hasEqualY(int y) {
        return this.y == y;
    }

    public boolean hasXLesser(XYCoordinate xyCoordinate) {
        return hasXLesser(xyCoordinate.x);
    }

    public boolean hasXLesser(int x) {
        return this.x < x;
    }

    public boolean hasXGreater(XYCoordinate xyCoordinate) {
        return hasXGreater(xyCoordinate.x);
    }

    public boolean hasXGreater(int x) {
        return this.x > x;
    }

    public boolean hasYLesser(XYCoordinate xyCoordinate) {
        return hasYLesser(xyCoordinate.y);
    }

    public boolean hasYLesser(int y) {
        return this.y < y;
    }

    public boolean hasYGreater(XYCoordinate xyCoordinate) {
        return hasYGreater(xyCoordinate.y);
    }

    public boolean hasYGreater(int y) {
        return this.y > y;
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
        return
                XYCoordinate.class.getSimpleName()
                        // getClass().getSimpleName()
                        + "{" +
                        (super.toString().equals(getClass().getName() + "@" + Integer.toHexString(hashCode()))
                                ? ""
                                : ("{" + super.toString() + "}, ")
                        ) +
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

package timmax.tilegame.basemodel.placement.primitives;

import timmax.tilegame.basemodel.exception.WidthHeightIsNotAllowedRecalcException;
import timmax.tilegame.basemodel.exception.XYCoordinateIsOutOfRangeException;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;

//  Ширина и высота (пока предполагается применять для главного поля,
//  но возможно позже можно будет рассмотреть и для многоплиточных объектов).
public final class WidthHeightSizes implements Externalizable {
    //  ToDo:   Вероятно эти переменные лучше переместить в GameType - что-бы у разных типов игр
    //          было можно по разному выставлять ограничения на размеры досок.
    //  ToDo:   Также рассмотреть и возможность хранения для типа игры перечня допустимых размеров.
    //              Например, для Го есть три основных варианта размеров (из Википедии):
    //              Стандартная доска имеет разлиновку 19×19 линий. Для обучения и коротких неофициальных игр могут
    //              применяться доски меньших размеров: обычно 13×13 или 9×9 линий, намного реже — 11×11, 15×15, 17×17
    //              линий, но, теоретически, ничто не мешает использовать произвольную прямоугольную доску.
    //              На интернет-серверах иногда играют на досках нестандартного, в том числе намного большего
    //              размера (например, 37×37 линий).
    public final static int MIN_WIDTH = 1;
    public final static int MIN_HEIGHT = 1;
    public final static int MAX_WIDTH = 20;
    public final static int MAX_HEIGHT = 20;

    private int width;
    private int height;

    //  Можно-ли пересчитать (в большую сторону).
    private boolean mayBeRecalc;

    public WidthHeightSizes() {
        mayBeRecalc = true;
    }

    public WidthHeightSizes(int width, int height) {
        this();
        if (width < MIN_WIDTH || width > MAX_WIDTH ||
                height < MIN_HEIGHT || height > MAX_HEIGHT) {
            throw new RuntimeException("Wrong width and/or height. You cannot set them into (width < MIN_WIDTH || width > MAX_WIDTH || height < MIN_HEIGHT || height > MAX_HEIGHT).");
        }
        this.width = width;
        this.height = height;
    }

    public WidthHeightSizes(int width, int height, boolean mayBeRecalc) {
        this(width, height);
        this.mayBeRecalc = mayBeRecalc;
    }

    public boolean mayBeRecalc() {
        return mayBeRecalc;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSquare() {
        return width * height;
    }

    public void setMayBeReaclFalse() {
        mayBeRecalc = false;
    }

    public void validateXYCoordinate(XYCoordinate xyCoordinate) {
        if (xyCoordinate.hasXLesser(0)
                || xyCoordinate.hasXGreater(width - 1)
                || xyCoordinate.hasYLesser(0)
                || xyCoordinate.hasYGreater(height - 1)
        ) {
            throw new XYCoordinateIsOutOfRangeException(xyCoordinate);
        }
    }

    public void recalc(XYCoordinate xyCoordinate) {
        throwExceptionIfNotMayBeRecalc();

        if (xyCoordinate.hasXGreater(width - 1)) {
            width = xyCoordinate.getX() + 1;
        }
        if (xyCoordinate.hasYGreater(height - 1)) {
            height = xyCoordinate.getY() + 1;
        }
    }

    public void recalc(WidthHeightSizes widthHeightSizes) {
        throwExceptionIfNotMayBeRecalc();

        boolean thereIsError = false;
        if (width < widthHeightSizes.width) {
            width = widthHeightSizes.width;
        } else {
            thereIsError = true;
        }
        if (height < widthHeightSizes.height) {
            height = widthHeightSizes.height;
        } else {
            thereIsError = true;
        }
        if (thereIsError) {
            throw new RuntimeException("Current = " + this + ", but new one is = " + widthHeightSizes + ". You cannot do this.");
        }
    }

    private void throwExceptionIfNotMayBeRecalc() {
        if (!mayBeRecalc) {
            throw new WidthHeightIsNotAllowedRecalcException();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (WidthHeightSizes) obj;
        return this.width == that.width &&
                this.height == that.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }

    @Override
    public String toString() {
        return "WidthHeightSizes{" +
                "width=" + width +
                ", height=" + height +
                ", mayBeRecalc=" + mayBeRecalc +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(width);
        out.writeInt(height);
        out.writeBoolean(mayBeRecalc);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        width = in.readInt();
        height = in.readInt();
        mayBeRecalc = in.readBoolean();
    }
}

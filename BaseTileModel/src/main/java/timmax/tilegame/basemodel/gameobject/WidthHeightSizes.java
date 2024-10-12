package timmax.tilegame.basemodel.gameobject;

import timmax.tilegame.basemodel.exception.XYCoordinateIsOutOfRangeException;

import java.util.Objects;

//  Ширина и высота
public final class WidthHeightSizes {
    //  ToDo:   Вероятно эти переменные лучше переместить в GameType - что-бы у разных типов игр
    //          было можно по разному выставлять ограничения на размеры досок.
    //  ToDo:   Также рассмотреть и возможность хранения для типа игры перечня допустимых размеров.
    //              Например, для Го есть три основных варианта размеров (из википедии):
    //              Стандартная доска имеет разлиновку 19×19 линий. Для обучения и коротких неофициальных игр могут
    //              применяться доски меньших размеров: обычно 13×13 или 9×9 линий, намного реже — 11×11, 15×15, 17×17
    //              линий, но, теоретически, ничто не мешает использовать произвольную прямоугольную доску.
    //              На интернет-серверах иногда играют на досках нестандартного, в том числе намного большего
    //              размера (например, 37×37 линий).
    public final static int MIN_WIDTH = 1;
    public final static int MIN_HEIGHT = 1;
    public final static int MAX_WIDTH = 20;
    public final static int MAX_HEIGHT = 20;

    private final int width;
    private final int height;

    public WidthHeightSizes(int width, int height) {
        if (width < MIN_WIDTH || width > MAX_WIDTH ||
                height < MIN_HEIGHT || height > MAX_HEIGHT) {
            throw new RuntimeException("Wrong width and/or height. You cannot set them into (width < MIN_WIDTH || width > MAX_WIDTH || height < MIN_HEIGHT || height > MAX_HEIGHT).");
        }
        this.width = width;
        this.height = height;
    }

    public int getSquare() {
        return width * height;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public void validateXYCoordinate(XYCoordinate xyCoordinate) {
        if (xyCoordinate.getX() < 0
                || xyCoordinate.getX() >= width
                || xyCoordinate.getY() < 0
                || xyCoordinate.getY() >= height
        ) {
            throw new XYCoordinateIsOutOfRangeException(); //("xyCoordinate is wrong. xyCoordinate = " + xyCoordinate + ". WidthHeightSizes = " + this + ".");
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
                '}';
    }
}

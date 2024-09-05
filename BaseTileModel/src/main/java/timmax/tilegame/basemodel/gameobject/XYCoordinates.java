package timmax.tilegame.basemodel.gameobject;

//  xy-координаты
public final class XYCoordinates {
    private final int x;
    private final int y;
    //  Расположение, внутри которого определяются координаты
    private final OneTileGameObjectsPlacement oneTileGameObjectsPlacement;

    public XYCoordinates(int x, int y, OneTileGameObjectsPlacement oneTileGameObjectsPlacement) {
        //  Проверка на то, что объекты находятся внутри доски
        if (x < 0 || x >= oneTileGameObjectsPlacement.getWidthHeightSizes().width() ||
                y < 0 || y >= oneTileGameObjectsPlacement.getWidthHeightSizes().height()) {
            throw new RuntimeException("XY-coordinates are wrong.");
        }
        this.x = x;
        this.y = y;
        this.oneTileGameObjectsPlacement = oneTileGameObjectsPlacement;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "XYCoordinates[" +
                "x=" + x + ", " +
                "y=" + y + ", " +
                "oneTileGameObjectsPlacement=" + oneTileGameObjectsPlacement + ']';
    }
}

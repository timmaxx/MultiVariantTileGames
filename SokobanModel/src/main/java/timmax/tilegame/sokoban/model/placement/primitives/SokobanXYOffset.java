package timmax.tilegame.sokoban.model.placement.primitives;

import timmax.tilegame.basemodel.placement.primitives.XYOffset;

//  Смещение на одну клетку по горизонтали или по вертикали.
public class SokobanXYOffset extends XYOffset {
    public static SokobanXYOffset TO_UP = new SokobanXYOffset(0, -1);
    public static SokobanXYOffset TO_DOWN = new SokobanXYOffset(0, 1);
    public static SokobanXYOffset TO_LEFT = new SokobanXYOffset(-1, 0);
    public static SokobanXYOffset TO_RIGHT = new SokobanXYOffset(1, 0);

    public SokobanXYOffset(int dx, int dy) {
        super(dx, dy);
    }
}

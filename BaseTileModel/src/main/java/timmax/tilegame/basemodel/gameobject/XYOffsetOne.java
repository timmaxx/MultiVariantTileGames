package timmax.tilegame.basemodel.gameobject;

public class XYOffsetOne extends XYOffset {
    public static XYOffsetOne TO_UP = new XYOffsetOne(0, -1);
    public static XYOffsetOne TO_DOWN = new XYOffsetOne(0, 1);
    public static XYOffsetOne TO_LEFT = new XYOffsetOne(-1, 0);
    public static XYOffsetOne TO_RIGHT = new XYOffsetOne(1, 0);

    public XYOffsetOne(int dx, int dy) {
        super(dx, dy);
    }
}

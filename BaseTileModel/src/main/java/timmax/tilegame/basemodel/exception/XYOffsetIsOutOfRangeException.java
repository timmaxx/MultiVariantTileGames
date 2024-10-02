package timmax.tilegame.basemodel.exception;

public class XYOffsetIsOutOfRangeException extends RuntimeException {
    public XYOffsetIsOutOfRangeException() {
        super("dx and/or dy is out of range.");
    }
}

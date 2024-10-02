package timmax.tilegame.basemodel.exception;

public class XYCoordinateIsOutOfRangeException extends RuntimeException {
    public XYCoordinateIsOutOfRangeException() {
        super("X and/or y coordinate is out of range.");
    }
}

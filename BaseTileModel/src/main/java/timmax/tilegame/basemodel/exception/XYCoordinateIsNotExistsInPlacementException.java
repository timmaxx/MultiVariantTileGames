package timmax.tilegame.basemodel.exception;

public class XYCoordinateIsNotExistsInPlacementException extends RuntimeException {
    public XYCoordinateIsNotExistsInPlacementException() {
        super("X and/or y coordinate is out of range.");
    }
}

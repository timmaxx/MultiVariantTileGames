package timmax.tilegame.basemodel.exception;

public class XYCoordinateAlreadyExistsExeption extends RuntimeException {
    public XYCoordinateAlreadyExistsExeption() {
        super("XYCoordinate already exists in this placement.");
    }
}

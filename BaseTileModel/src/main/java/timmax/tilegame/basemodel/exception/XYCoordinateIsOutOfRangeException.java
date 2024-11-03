package timmax.tilegame.basemodel.exception;

import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;

public class XYCoordinateIsOutOfRangeException extends RuntimeException {
    public XYCoordinateIsOutOfRangeException(XYCoordinate xyCoordinate) {
        super("X and/or y coordinate is out of range " + xyCoordinate);
    }
}

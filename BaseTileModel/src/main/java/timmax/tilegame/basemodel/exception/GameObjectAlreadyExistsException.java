package timmax.tilegame.basemodel.exception;

import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;

public class GameObjectAlreadyExistsException extends RuntimeException {
    public GameObjectAlreadyExistsException(XYCoordinate xyCoordinate) {
        super("Game object already exists in this x and y coordinate " + xyCoordinate);
    }
}

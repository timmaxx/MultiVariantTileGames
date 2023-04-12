package timmax.sokoban.model.route;

import timmax.basetilemodel.Direction;

public class Step {
    private final Direction direction;
    private final boolean isBoxMoved;

    public Step( Direction direction, boolean isBoxMoved) {
        this.direction = direction;
        this.isBoxMoved = isBoxMoved;
    }

    @Override
    public String toString( ) {
        return "Step{ " +
                "direction = " + direction +
                ", isBoxMoved = " + isBoxMoved +
                "}";
    }

    @Override
    public boolean equals( Object obj) {
        if ( obj == null) {
            return false;
        }
        if ( this == obj) {
            return true;
        }
        if ( !( obj instanceof Step step)) {
            return false;
        }

        return this.isBoxMoved == step.isBoxMoved && direction.equals( step.direction);
    }

    public Step oppositeStep( ) {
        return new Step( direction.not( ), isBoxMoved);
    }

    public Direction oppositeStepDirection( ) {
        return oppositeStep( ).direction;
    }

    public boolean isBoxMoved( ) {
        return isBoxMoved;
    }

    public Direction getDirection( ) {
        return direction;
    }
}
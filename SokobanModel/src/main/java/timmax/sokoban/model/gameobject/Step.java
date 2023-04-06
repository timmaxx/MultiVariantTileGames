package timmax.sokoban.model.gameobject;

public class Step {
    private Direction   direction;
    private boolean     isBoxMoved;

    public Step( Direction direction, boolean isBoxMoved) {
        this.direction = direction;
        this.isBoxMoved = isBoxMoved;
    }

    @Override
    public String toString() {
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
        if ( !( obj instanceof Step)) {
            return false;
        }
        Step step = ( Step) obj;

        return this.isBoxMoved == step.isBoxMoved && direction.equals( step.direction);
    }

    public Step oppositeStep( ) {
        return new Step( direction.not( ), isBoxMoved);
    }

    public Direction oppositeStepDirrection( ) {
        return oppositeStep( ).direction;
    }

    public boolean isBoxMoved( ) {
        return isBoxMoved;
    }

    public Direction getDirection( ) {
        return direction;
    }
}
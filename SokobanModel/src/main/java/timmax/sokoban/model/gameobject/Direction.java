package timmax.sokoban.model.gameobject;

import timmax.basetilemodel.XY;

public enum Direction {
    UNKNOWN,
    LEFT,
    RIGHT,
    UP,
    DOWN;

    public Direction not( ) {
        Direction result; // = null;
        switch ( this) {
            case DOWN  -> result = UP;
            case UP    -> result = DOWN;
            case LEFT  -> result = RIGHT;
            case RIGHT -> result = LEFT;
            //default    -> throw new IllegalStateException( "Unexpected value: " + this);
            default    -> result = UNKNOWN;
        }
        return result;
    }

    public XY getDxDy( ) {
        int dx = 0;
        int dy = 0;
        if        ( this == Direction.UP)    { dy--;
        } else if ( this == Direction.DOWN)  { dy++;
        } else if ( this == Direction.LEFT)  { dx--;
        } else if ( this == Direction.RIGHT) { dx++;
        }
        return new XY( dx, dy);
    }
}
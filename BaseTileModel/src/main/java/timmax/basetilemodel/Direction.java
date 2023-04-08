package timmax.basetilemodel;

public enum Direction {
    UNKNOWN,
    LEFT,
    RIGHT,
    UP,
    DOWN;

    public Direction not( ) {
        if        ( this == DOWN)  { return UP;
        } else if ( this == UP)    { return DOWN;
        } else if ( this == LEFT)  { return RIGHT;
        } else if ( this == RIGHT) { return LEFT;
        } else return UNKNOWN;
    }

    public XY getDxDy( ) {
        int dx = 0;
        int dy = 0;
        if        ( this == UP)    { dy--;
        } else if ( this == DOWN)  { dy++;
        } else if ( this == LEFT)  { dx--;
        } else if ( this == RIGHT) { dx++;
        }
        return new XY( dx, dy);
    }
}
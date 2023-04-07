package timmax.basetilemodel;

public class XY {
    private final int x;
    private final int y;

    public XY( int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX( ) {
        return x;
    }

    public int getY( ) {
        return y;
    }

    public XY add( Direction direction) {
        return add( direction.getDxDy( ));
    }

    public XY add( XY xy) {
        return new XY( x + xy.x, y + xy.y);
    }
}
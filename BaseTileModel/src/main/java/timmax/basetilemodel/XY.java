package timmax.basetilemodel;

public /*abstract*/ class XY {
    protected int x;
    protected int y;

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
        DxDy dXdy = direction.getDxDy();
        return add( dXdy);
    }

    public XY add( DxDy xy) {
        return new XY( x + xy.x, y + xy.y);
    }

    @Override
    public boolean equals( Object o) {
        if ( this == o) return true;
        if ( o == null || getClass( ) != o.getClass( )) return false;

        XY xy = ( XY) o;

        if ( x != xy.x) return false;
        return y == xy.y;
    }

    @Override
    public int hashCode( ) {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
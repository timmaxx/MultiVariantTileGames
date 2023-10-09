package timmax.tilegame.basemodel.tile;

// Плитка игрового поля
public /*abstract*/ class Tile {
    protected int x;
    protected int y;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX( ) {
        return x;
    }

    public int getY( ) {
        return y;
    }

    public Tile add( Direction direction) {
        return add( direction.getDxDy());
    }

    public Tile add( DxDy dxDy) {
        return new Tile( x + dxDy.x, y + dxDy.y);
    }

    @Override
    public boolean equals( Object o) {
        if ( this == o) return true;
        if ( o == null || getClass( ) != o.getClass( )) return false;

        Tile tile = (Tile) o;

        if ( x != tile.x) return false;
        return y == tile.y;
    }

    @Override
    public int hashCode( ) {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
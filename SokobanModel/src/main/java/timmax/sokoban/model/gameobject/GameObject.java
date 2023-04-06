package timmax.sokoban.model.gameobject;

abstract public class GameObject {
    protected int x;
    protected int y;

    public GameObject( int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX( ) {
        return x;
    }

    public int getY( ) {
        return y;
    }

    public void setX( int x) {
        this.x = x;
    }

    public void setY( int y) {
        this.y = y;
    }

    // public boolean compareXnY( GameObject go) { return this.x == go.x && this.y == go.y; }
}
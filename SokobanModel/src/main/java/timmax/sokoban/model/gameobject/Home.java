package timmax.sokoban.model.gameobject;

import timmax.basetilemodel.tile.Tile;

public class Home extends Tile implements NonMovable {
    public Home( int x, int y) {
        super( x, y);
    }
}
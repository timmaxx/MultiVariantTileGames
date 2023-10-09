package timmax.tilegame.game.sokoban.model.gameobject;

import java.util.Set;

public class AllSokobanObjects {
    private final int width;
    private final int height;
    private final Set< Wall> walls;
    private final Set< Box> boxes;
    private final Set< Home> homes;
    private final Player player;
    private final int countOfHomesBoxes;


    public AllSokobanObjects( int width, int height, Set<Wall> walls, Set< Box> boxes, Set< Home> homes, Player player, int countOfHomesBoxes) {
        this.width = width;
        this.height = height;
        this.walls = walls;
        this.boxes = boxes;
        this.homes = homes;
        this.player = player;
        this.countOfHomesBoxes = countOfHomesBoxes;
    }

    public int getWidth( ) {
        return width;
    }

    public int getHeight( ) {
        return height;
    }

    public Set< Box> getBoxes( ) {
        return boxes;
    }

    public Player getPlayer( ) {
        return player;
    }

    public Set< Wall> getWalls( ) {
        return walls;
    }

    public Set< Home> getHomes( ) {
        return homes;
    }

    public int getCountOfHomesBoxes( ) {
        return countOfHomesBoxes;
    }

    public WhoPersistentInTile getWhoPersistentInTile( int x, int y) {
        for ( Wall wall: walls) {
            if ( wall.getX( ) == x && wall.getY( ) == y) {
                return WhoPersistentInTile.IS_WALL;
            }
        }
        for ( Home home: homes) {
            if ( home.getX( ) == x && home.getY( ) == y) {
                return WhoPersistentInTile.IS_HOME;
            }
        }
        return WhoPersistentInTile.IS_EMPTY;
    }

    public WhoMovableInTile getWhoMovableInTile( int x, int y) {
        for ( Box box: boxes) {
            if ( box.getX( ) == x && box.getY( ) == y) {
                return WhoMovableInTile.IS_BOX;
            }
        }
        if ( player.getX( ) == x && player.getY( ) == y) {
            return WhoMovableInTile.IS_PLAYER;
        }
        return WhoMovableInTile.IS_NOBODY;
    }
}
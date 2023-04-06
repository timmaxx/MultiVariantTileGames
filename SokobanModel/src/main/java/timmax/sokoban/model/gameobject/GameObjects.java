package timmax.sokoban.model.gameobject;

import java.util.ArrayList;
import java.util.Set;

public class GameObjects {
    private Set< Wall> walls;
    private Set< Box> boxes;
    private Set< Home> homes;
    private Player player;

    private int maxX;
    private int maxY;

    public GameObjects( Set< Wall> walls, Set< Box> boxes, Set< Home> homes, Player player) {
        this.walls = walls;
        this.boxes = boxes;
        this.homes = homes;
        this.player = player;
    }

    public Set< Wall> getWalls( ) {
        return walls;
    }

    public Set< Box> getBoxes( ) {
        return boxes;
    }

    public Set< Home> getHomes( ) {
        return homes;
    }

    public Player getPlayer( ) {
        return player;
    }

    public ArrayList< GameObject> getAll( ) {
        ArrayList< GameObject> gameObjects = new ArrayList< >( ); // = new HashSet< >( walls);
        gameObjects.addAll( walls);
        gameObjects.addAll( homes);
        gameObjects.addAll( boxes);
        gameObjects.add( player);
        return gameObjects;
    }

    public int getMaxX( ) {
        if ( maxX > 0) {
            return maxX;
        }

        // int maxX = 0;
        for( GameObject go: getAll( )) {
            if ( go.getX( ) > maxX) {
                maxX = go.getX( );
            }
        }
        return maxX;
    }

    public int getMaxY( ) {
        if ( maxY > 0) {
            return maxY;
        }

        // int maxY = 0;
        for( GameObject go: getAll( )) {
            if ( go.getY( ) > maxY) {
                maxY = go.getY( );
            }
        }
        return maxY;
    }
}
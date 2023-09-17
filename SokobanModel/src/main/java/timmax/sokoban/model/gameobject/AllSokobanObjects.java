package timmax.sokoban.model.gameobject;

import timmax.basetilemodel.tile.Tile;
import java.util.ArrayList;
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

    public ArrayList< Tile> getAll( ) {
        ArrayList< Tile> gameObjects = new ArrayList< >( );
        gameObjects.addAll( walls);
        gameObjects.addAll( homes);
        gameObjects.addAll( boxes);
        gameObjects.add( player);

        return gameObjects;
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
}
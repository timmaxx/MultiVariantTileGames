package timmax.sokoban.model.gameobject;

import timmax.basetilemodel.XY;

import java.util.ArrayList;
import java.util.Set;

public class SokobanGameObjects {
    private final int width;
    private final int height;
    private final Set< Home> homes;
    private final Set< Box> boxes;
    private final Set< Wall> walls;

    private final Player player;

    private final int countOfHomesBoxes;

    public SokobanGameObjects(int width, int height,
                              Set< Wall> walls, Set< Box> boxes, Set< Home> homes, Player player,
                              int countOfHomesBoxes) {
        this.width = width;
        this.height = height;
        this.walls = walls;
        this.boxes = boxes;
        this.homes = homes;
        this.player = player;
        this.countOfHomesBoxes = countOfHomesBoxes;
    }

    public Player getPlayer( ) {
        return player;
    }

    public int getCountOfHomesBoxes( ) {
        return countOfHomesBoxes;
    }

    public Set< Home> getHomes( ) {
        return homes;
    }

    public Set< Box> getBoxes( ) {
        return boxes;
    }

    public Set< Wall> getWalls( ) {
        return walls;
    }

    public ArrayList< XY> getAll( ) {
        ArrayList< XY> gameObjects = new ArrayList< >( ); // = new HashSet< >( walls);
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
}
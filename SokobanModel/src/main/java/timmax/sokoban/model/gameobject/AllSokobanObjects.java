package timmax.sokoban.model.gameobject;

import timmax.basetilemodel.tile.Tile;

import java.util.ArrayList;
import java.util.Set;

public record AllSokobanObjects(
        int width,
        int height,
        Set< Wall> walls,
        Set< Box> boxes,
        Set< Home> homes,
        Player player,
        int countOfHomesBoxes) {

    public ArrayList<Tile> getAll() {
        ArrayList<Tile> gameObjects = new ArrayList< >( ); // = new HashSet< >( walls);
        gameObjects.addAll( walls);
        gameObjects.addAll( homes);
        gameObjects.addAll( boxes);
        gameObjects.add( player);
        return gameObjects;
    }
}
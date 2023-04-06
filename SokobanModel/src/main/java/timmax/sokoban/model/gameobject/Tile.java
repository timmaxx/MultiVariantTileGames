package timmax.sokoban.model.gameobject;

import java.util.Objects;

public class Tile {
    private boolean isWall;
    private boolean isBox;
    private boolean isHome;
    private boolean isPlayer;

    public Tile( boolean isWall, boolean isBox, boolean isHome, boolean isPlayer) {
        StringBuilder errMessage = new StringBuilder( );
        if ( isWall && ( isBox || isHome || isPlayer)) {
            errMessage.append("isWall && ( isBox || isHome || isPlayer)");
        }
        // ...
        if ( !Objects.equals(errMessage.toString(), "")) {
            throw new RuntimeException( String.valueOf( errMessage));
        }
        this.isWall = isWall;
        this.isBox = isBox;
        this.isHome = isHome;
        this.isPlayer = isPlayer;
    }

/*
    void setAllFields( boolean isWall, boolean isBox, boolean isHome, boolean isPlayer) {
        StringBuilder errMessage = new StringBuilder( );
        if ( isWall && ( isBox || isHome || isPlayer)) {
            errMessage.append("isWall && ( isBox || isHome || isPlayer)");
        }
        // ...
        if ( !errMessage.equals( "")) {
            throw new RuntimeException( String.valueOf( errMessage));
        }
        this.isWall = isWall;
        this.isBox = isBox;
        this.isHome = isHome;
        this.isPlayer = isPlayer;
    }
*/

    public boolean isWall( ) {
        return isWall;
    }

    public boolean isBox( ) {
        return isBox;
    }

    public boolean isHome( ) {
        return isHome;
    }

    public boolean isPlayer( ) {
        return isPlayer;
    }

    public boolean isNotWallAndNotBox( ) { return !isWall && !isBox; }

    public void setPlayer( boolean isPlayer) {
        this.isPlayer = isPlayer;
    }

    public void setBox( boolean isBox) { this.isBox = isBox; }
}
package timmax.sokoban.model;

import timmax.sokoban.model.gameobject.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class LevelLoader {
    private final Path levels;

    public LevelLoader( Path levels) {
        this.levels = levels;
    }

    public Tile[ ][ ] getLevel( int level) {
        Tile[ ][ ] tiles = new Tile[ 0][ ];

        int countOfBoxes = 0;
        int countOfHome = 0;
        int countOfPlayers = 0;

        try ( BufferedReader reader = new BufferedReader( new FileReader( levels.toFile( )))) {
            int readLevel = 0;
            int x;
            int y = 0;
            boolean isLevelMap = false;
            int width = 0;
            int height = 0;

            String line;
            while ( ( line = reader.readLine( )) != null) {
                if ( line.contains( "Maze:")) {
                    readLevel = Integer.parseInt( line.split( " ")[ 1]);
                    continue;
                } else if ( line.contains( "Size X:")) {
                    width = Integer.parseInt( line.split( " ")[ 2]);
                    if ( width > 0 && height > 0) {
                        tiles = new Tile[ height][ width];
                    }
                    continue;
                } else if ( line.contains( "Size Y:")) {
                    height = Integer.parseInt( line.split( " ")[ 2]);
                    if ( width > 0 && height > 0) {
                        tiles = new Tile[ height][ width];
                    }
                    continue;
                }
                if ( readLevel == level) {
                    if ( line.length( ) == 0) {
                        boolean isEnd = isLevelMap;

                        isLevelMap = !isLevelMap;

                        if ( isEnd && !isLevelMap) {
                            break;
                        } else {
                            continue;
                        }
                    }
                    if ( !isLevelMap) {
                        continue;
                    }

                    char[ ] chars = line.toCharArray( );
                    tiles[ y] = new Tile[ width];
                    x = 0;
                    for ( char c : chars) {
                        boolean isWall = false;
                        boolean isBox = false;
                        boolean isHome = false;
                        boolean isPlayer = false;
                        switch ( c) {
                            case 'X':
                                isWall = true;
                                break;
                            case '*':
                                isBox = true;
                                countOfBoxes++;
                                break;
                            case '.':
                                isHome = true;
                                countOfHome++;
                                break;
                            case '&':
                                isBox = true;
                                countOfBoxes++;
                                isHome = true;
                                countOfHome++;
                                break;
                            case '@':
                                isPlayer = true;
                                countOfPlayers++;
                        }
                        tiles[ y][ x] = new Tile( isWall, isBox, isHome, isPlayer);
                        x++;
                    }
                    y++;
                }
            }
        } catch ( IOException e) {
            e.printStackTrace( );
        }

        validate( countOfPlayers, countOfBoxes, countOfHome);

        return tiles;
    }

    private static void validate( int countOfPlayers, int countOfBoxes, int countOfHome) {
        StringBuilder errMessage = new StringBuilder( );
        boolean isError = false;
        if (countOfPlayers != 1) {
            isError = true;
            errMessage.append( "countOfPlayers <> 1!");
        }
        if ( countOfBoxes != countOfHome) {
            isError = true;
            errMessage.append( " countOfBoxes <> countOfHome!");
        }
        if ( isError) {
            throw new RuntimeException( errMessage.toString( ));
        }
    }
}
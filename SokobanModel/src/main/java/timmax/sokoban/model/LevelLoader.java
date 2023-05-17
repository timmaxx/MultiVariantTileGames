package timmax.sokoban.model;

import timmax.sokoban.model.gameobject.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader {
    private final Path levels;

    public LevelLoader( Path levels) {
        this.levels = levels;
    }

    public AllSokobanObjects getLevel(int level) {

        int countOfBoxes = 0;
        int countOfHome = 0;
        int countOfPlayers = 0;

        Set< Wall> walls = new HashSet<>();
        Set< Box> boxes = new HashSet<>();
        Set< Home> homes = new HashSet<>();
        Player player = null;

        // ToDo: Сейчас берутся явно из файла.
        //  Но можно было-бы высчитать их, исходя из карты уровня (определив число строк и максимальный столбец).
        int width = 0;
        int height = 0;

        try ( BufferedReader reader = new BufferedReader( new FileReader( levels.toFile( )))) {
            int readLevel = 0;
            int x;
            int y = 0;
            boolean isLevelMap = false;

            String line;
            while ( ( line = reader.readLine( )) != null) {
                if ( line.contains( "Maze:")) {
                    readLevel = Integer.parseInt( line.split( " ")[ 1]);
                    continue;
                } else if ( line.contains( "Size X:")) {
                    width = Integer.parseInt( line.split( " ")[ 2]);
                    continue;
                } else if ( line.contains( "Size Y:")) {
                    height = Integer.parseInt( line.split( " ")[ 2]);
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
                    x = 0;
                    for ( char c : chars) {
                        if ( c == 'X') {
                            walls.add( new Wall( x, y));
                        } else if ( c == '*') {
                            countOfBoxes++;
                            boxes.add( new Box( x, y));
                        } else if ( c == '.') {
                            countOfHome++;
                            homes.add( new Home( x, y));
                        } else if ( c == '&') {
                            countOfBoxes++;
                            boxes.add( new Box( x, y));

                            countOfHome++;
                            homes.add( new Home( x, y));

                        } else if (c == '@') {
                            countOfPlayers++;
                            player = new Player( x, y);
                        }
                        x++;
                    }
                    y++;
                }
            }
        } catch ( IOException e) {
            e.printStackTrace( );
        }

        validate( countOfPlayers, countOfBoxes, countOfHome);

        return new AllSokobanObjects( width, height, walls, boxes, homes, player,  countOfBoxes);
    }

    private static void validate( int countOfPlayers, int countOfBoxes, int countOfHome) {
        StringBuilder errMessage = new StringBuilder( );
        boolean isError = false;
        if ( countOfPlayers != 1) {
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
package timmax.tilegame.basemodel.gamecommand;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

// В этом модуле применил аннотации Jackson.
// ToDo: Но лучше было-бы не использовать их (аннотации Jackson) здесь, а только в транспорте!
//       Но тогда там нужно в модулях транспорта использовать не аннотации, а вызовы методов.
public abstract class GameCommandOneTile extends GameCommand {
    private final int x;
    private final int y;


    @JsonCreator( mode = PROPERTIES)
    public GameCommandOneTile(
            @JsonProperty( "x") int x,
            @JsonProperty( "y") int y) {
        this.x = x;
        this.y = y;
    }

    public int getX( ) {
        return x;
    }

    public int getY( ) {
        return y;
    }
}
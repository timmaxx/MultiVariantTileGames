package timmax.tilegame.game.sokoban.model.gamecommand;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.gamecommand.GameCommand;
import timmax.tilegame.basemodel.tile.Direction;

import timmax.tilegame.game.sokoban.model.SokobanModel;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

public class GameCommandSokobanMove extends GameCommand {
    private final Direction direction;


    @JsonCreator( mode = PROPERTIES)
    public GameCommandSokobanMove(
            @JsonProperty( "direction") Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection( ) {
        return direction;
    }

    @Override
    public void execute( BaseModel baseModel) {
        ( ( SokobanModel)baseModel).move( direction);
    }
}
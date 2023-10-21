package timmax.tilegame.game.minesweeper.model.gamecommand;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.gamecommand.GameCommandOneTile;

import timmax.tilegame.game.minesweeper.model.MinesweeperModel;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

public class GameCommandMinesweeperOpen extends GameCommandOneTile {
    @JsonCreator( mode = PROPERTIES)
    public GameCommandMinesweeperOpen(
            @JsonProperty( "x") int x,
            @JsonProperty( "y") int y) {
        super( x, y);
    }

    @Override
    public void execute( BaseModel baseModel) {
        ( ( MinesweeperModel) baseModel).open( getX( ), getY( ));
    }
}
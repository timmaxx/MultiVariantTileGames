package timmax.tilegame.game.sokoban.model.gamecommand;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.gamecommand.GameCommand;
import timmax.tilegame.basemodel.tile.Direction;

import timmax.tilegame.game.sokoban.model.SokobanModel;

public class GameCommandSokobanMove extends GameCommand {
    private final Direction direction;

    public GameCommandSokobanMove( Direction direction) {
        this.direction = direction;
    }

    @Override
    public void execute( BaseModel baseModel) {
        ( ( SokobanModel)baseModel).move( direction);
    }
}
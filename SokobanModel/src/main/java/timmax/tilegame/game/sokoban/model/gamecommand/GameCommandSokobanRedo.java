package timmax.tilegame.game.sokoban.model.gamecommand;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.gamecommand.GameCommand;

import timmax.tilegame.game.sokoban.model.SokobanModel;

public class GameCommandSokobanRedo extends GameCommand {
    @Override
    public void execute( BaseModel baseModel) {
        ( ( SokobanModel)baseModel).moveRedo( );
    }
}
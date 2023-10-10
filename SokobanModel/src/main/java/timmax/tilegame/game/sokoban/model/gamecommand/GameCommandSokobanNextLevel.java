package timmax.tilegame.game.sokoban.model.gamecommand;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.gamecommand.GameCommandNextLevel;

import timmax.tilegame.game.sokoban.model.SokobanModel;

public class GameCommandSokobanNextLevel extends GameCommandNextLevel {
    @Override
    public void execute( BaseModel baseModel) {
        ( ( SokobanModel) baseModel).nextLevel( );
    }
}
package timmax.tilegame.game.sokoban.model;

import timmax.tilegame.basemodel.protocol.server.GameType;

public class GameTypeOfSokoban extends GameType {
    public GameTypeOfSokoban() throws ClassNotFoundException, NoSuchMethodException {
        super("Sokoban", /*1,*/ GameMatchOfSokoban.class);
    }
}

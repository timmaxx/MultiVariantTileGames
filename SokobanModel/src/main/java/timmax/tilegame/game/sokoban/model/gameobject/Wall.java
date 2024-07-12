package timmax.tilegame.game.sokoban.model.gameobject;

public class Wall extends CollisionObject implements NonMovable {
    public Wall(int x, int y) {
        super(x, y);
    }
}

package timmax.tilegame.basemodel.exception;

public class GameObjectAlreadyExistsExeption extends RuntimeException {
    public GameObjectAlreadyExistsExeption() {
        super("One tile game object already exists in this placement.");
    }
}

package timmax.tilegame.basemodel.exception;

public class OneTileGameObjectAlreadyExistsExeption extends RuntimeException {
    public OneTileGameObjectAlreadyExistsExeption() {
        super("One tile game object already exists in this placement.");
    }
}

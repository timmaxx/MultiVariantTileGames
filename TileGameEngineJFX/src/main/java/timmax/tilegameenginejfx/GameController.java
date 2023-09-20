package timmax.tilegameenginejfx;

import javafx.scene.input.KeyCode;
import timmax.basetilemodel.BaseModel;

// Этот класс как-бы привязан к сцене (Scene), т.е. если фокус на сцене,
// то события должны быть применены с учётом этого (например ширина и высота сцены, размеры ячеек).
// Возможно он должен быть, либо наследником GameScene, либо включать в себя переменную типа GameScene.

// Возможно стоит создать контроллер, который НЕ будет привязан к сцене, а напрямую к модели.
// В качестве примера:
// - контроллер, с помощью которого можно подавать текстовые команды модели (как с терминала) - типа поток ввода.
// - но тогда и модель могла-бы отвечать такой консоли текстом - типа поток вывода.
public abstract class GameController {
    protected final BaseModel baseModel;
    // ToDo: game убрать из контроллера!
    protected final Game game;


    public GameController( BaseModel baseModel, Game game) {
        this.baseModel = baseModel;
        this.game = game;
    }

    public void onMouseLeftClick( int x, int y) {
    }

    public void onMouseRightClick( int x, int y) {
    }

    public void onKeyPress( KeyCode keyCode) {
    }

    public void onKeyReleased( KeyCode keyCode) {
    }
}
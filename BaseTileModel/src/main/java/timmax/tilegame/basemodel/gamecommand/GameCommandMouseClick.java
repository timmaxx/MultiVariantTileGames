package timmax.tilegame.basemodel.gamecommand;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.scene.input.MouseButton;

public class GameCommandMouseClick extends GameCommandOneTile {
    private MouseButton mouseButton;

    public GameCommandMouseClick() {
        super();
    }
    public GameCommandMouseClick(int x, int y, MouseButton mouseButton) {
        super(x, y);
        this.mouseButton = mouseButton;
    }

    public MouseButton getMouseButton() {
        return mouseButton;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeObject(mouseButton);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        mouseButton = (MouseButton) in.readObject();
    }
}

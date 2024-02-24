package timmax.tilegame.basemodel.gamecommand;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.scene.input.MouseButton;

import timmax.tilegame.basemodel.protocol.server.IModelOfServer;

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
    public void executeOnServer(IModelOfServer modelOfServer) {
        System.out.println("class GameCommandMouseClick. method executeOnServer.");
        System.out.println("  mouseButton = " + mouseButton + ", x = " + getX() + ", y = " + getY());

        // 1. Есть-ли допуски у клиента, откуда пришла команда:
        // 1.1. к модели?
        // 1.2. к передаче команд (т.е. является ли он игроком и его-ли сейчас ход)?
        //      (или же он является только наблюдателем).

        // 2. В найденную модель отправить команду.
        modelOfServer.executeMouseCommand(this);
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

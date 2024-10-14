package timmax.tilegame.basemodel.gamecommand;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.scene.input.MouseButton;

import timmax.tilegame.basemodel.gameobject.XYCoordinate;
import timmax.tilegame.basemodel.protocol.server.IGameMatch;

public class GameCommandMouseClick extends GameCommandOneTile {
    private MouseButton mouseButton;

    public GameCommandMouseClick() {
        super();
    }

    public GameCommandMouseClick(XYCoordinate xyCoordinate, MouseButton mouseButton) {
        super(xyCoordinate);
        this.mouseButton = mouseButton;
    }

    public MouseButton getMouseButton() {
        return mouseButton;
    }

    @Override
    public void executeOnServer(IGameMatch iGameMatch) {
        // 1. Есть-ли допуски у клиента, откуда пришла команда:
        // 1.1. к модели?
        // 1.2. к передаче команд (т.е. является ли он игроком и его-ли сейчас ход)?
        //      (или же он является только наблюдателем).

        // 2. В найденную модель отправить команду.
        iGameMatch.executeMouseCommand(this);
    }

    @Override
    public String toString() {
        return super.toString() +
                "GameCommandMouseClick{" +
                "mouseButton=" + mouseButton +
                '}';
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

package timmax.tilegame.basemodel.gamecommand;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.scene.input.KeyCode;

import timmax.tilegame.basemodel.protocol.server.IModelOfServer;

public class GameCommandKeyPressed extends GameCommand {
    private KeyCode keyCode;

    public GameCommandKeyPressed() {
    }

    public GameCommandKeyPressed(KeyCode keyCode) {
        this.keyCode = keyCode;
    }

    public KeyCode getKeyCode() {
        return keyCode;
    }

    @Override
    public void executeOnServer(IModelOfServer modelOfServer) {
        logger.info("KeyPressed");
        logger.info("  keyCode = {}", keyCode);

        // 1. Есть-ли допуски у клиента, откуда пришла команда:
        // 1.1. к модели?
        // 1.2. к передаче команд (т.е. является ли он игроком и его-ли сейчас ход)?
        //      (или же он является только наблюдателем).

        // 2. В найденную модель отправить команду.
        modelOfServer.executeKeyboardCommand(this);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeObject(keyCode);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        keyCode = (KeyCode) in.readObject();
    }
}

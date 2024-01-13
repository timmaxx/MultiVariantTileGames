package timmax.tilegame.basemodel.gamecommand;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.scene.input.KeyCode;

import timmax.tilegame.basemodel.protocol.server.IModelOfServer;
import timmax.tilegame.transport.TransportOfServer;

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
    public <T> void executeOnServer(TransportOfServer<T> transportOfServer, T clientId) {
        System.out.println("class GameCommandKeyPressed. method executeOnServer.");
        System.out.println("  keyCode = " + keyCode);

        // 1. По clientId определить модель, для которой пришла команда.
        IModelOfServer<T> modelOfServer = transportOfServer.getModelByClientId(clientId);

        // 2. Есть-ли допуски у клиента, откуда пришла команда:
        // 2.1. к модели?
        // 2.2. к передаче команд (т.е. является ли он игроком и его-ли сейчас ход)?
        //      (или же он является только наблюдателем).

        // 3. В найденную модель отправить команду.
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

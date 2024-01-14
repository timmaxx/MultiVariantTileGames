package timmax.tilegame.basemodel.gamecommand;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.scene.input.MouseButton;

import timmax.tilegame.basemodel.protocol.server.IModelOfServer;
import timmax.tilegame.transport.TransportOfServer;

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
    public <ClienId> void executeOnServer(TransportOfServer<ClienId> transportOfServer, ClienId clientId) {
        System.out.println("class GameCommandMouseClick. method executeOnServer.");
        System.out.println("  mouseButton = " + mouseButton + ", x = " + getX() + ", y = " + getY());

        // 1. По clientId определить модель, для которой пришла команда.
        IModelOfServer<ClienId> modelOfServer = transportOfServer
                .getRemoteClientStateByClientId(clientId)
                .getServerBaseModel();

        // 2. Есть-ли допуски у клиента, откуда пришла команда:
        // 2.1. к модели?
        // 2.2. к передаче команд (т.е. является ли он игроком и его-ли сейчас ход)?
        //      (или же он является только наблюдателем).

        // 3. В найденную модель отправить команду.
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

package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import timmax.tilegame.basemodel.protocol.server.ModelOfServer;
import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient31GameTypeSelect extends EventOfClient {
    private String serverBaseModelClass;

    public EventOfClient31GameTypeSelect() {
        super();
    }

    public EventOfClient31GameTypeSelect(String serverBaseModelClass) {
        this();
        this.serverBaseModelClass = serverBaseModelClass;
    }

    @Override
    public <T> void executeOnServer(TransportOfServer<T> transportOfServer, T clientId) {
        System.out.println("  onSelectGameType");

        // ToDo: Проверить, что model одна из списка возможных моделей, которые были отправлены ранее этому клиенту.
        //       И если это не так, то отправить клиенту FORGET_GAME_TYPE.

        {
            System.out.println("  serverBaseModelClass = " + serverBaseModelClass);
            if (serverBaseModelClass == null) {
                return;
            }

            {
                // Здесь динамически выбирается класс модели и создаётся её экземпляр.
                Class<?> modelOfServerClass = null;
                try {
                    if (serverBaseModelClass.equals("MinesweeperModel.class")) {
                        modelOfServerClass = Class.forName("timmax.tilegame.game.minesweeper.model.ModelOfServerOfMinesweeper");
                    } else if (serverBaseModelClass.equals("SokobanModel.class")) {
                        modelOfServerClass = Class.forName("timmax.tilegame.game.sokoban.model.ModelOfServerOfSokoban");
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    System.exit(1);
                }

                if (modelOfServerClass == null || !Classes.isInstanceOf(modelOfServerClass, ModelOfServer.class)) {
                    return;
                }

                Constructor<?> constructor = null;
                try {
                    constructor = modelOfServerClass.getConstructor(TransportOfServer.class);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                    System.exit(1);
                }

                Object obj = null;
                try {
                    obj = constructor.newInstance(transportOfServer);
                } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                    System.exit(1);
                }

                if (obj instanceof ModelOfServer modelOfServer) {
                    transportOfServer.setModelOfServer(modelOfServer);
                } else {
                    return;
                }
            }

            System.out.println("    modelOfServer = " + transportOfServer.getModelOfServer());
            if (transportOfServer.getModelOfServer() == null) {
                return;
            }
        }

        transportOfServer.send(clientId, new EventOfServer31GameTypeSelect(serverBaseModelClass));
    }

    @Override
    public String toString() {
        return "EventOfClient31GameTypeSelect{" +
                "serverBaseModelClass='" + serverBaseModelClass + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(serverBaseModelClass);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        serverBaseModelClass = (String) in.readObject();
    }
}

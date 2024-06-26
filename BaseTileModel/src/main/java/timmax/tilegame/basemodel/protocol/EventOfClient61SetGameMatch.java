package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import timmax.tilegame.basemodel.protocol.server.IModelOfServer;
import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;

public class EventOfClient61SetGameMatch<ClientId> extends EventOfClient<ClientId> {
    private InstanceIdOfModel instanceIdOfModel;

    public EventOfClient61SetGameMatch() {
        super();
    }

    public EventOfClient61SetGameMatch(InstanceIdOfModel instanceIdOfModel) {
        this();
        this.instanceIdOfModel = instanceIdOfModel;
    }

    // ToDo: Вероятно нужно переработать код executeOnServer(...) для двух классов (см. ниже) и вероятно перестать
    //       использовать в них clientId.
    //       Комментарий относится:
    //       - в первую очередь к классам:
    //       -- EventOfClient31SetGameTypeSet,
    //       -- EventOfClient61SetGameMatch,
    //       - во вторых:
    //       -- к EventOfClient,
    //       - в третьих:
    //       -- ко всем остальным EventOfClientХХ...
    //       clientId используется в executeOnServer(...) в классах-наследниках EventOfClient только в:
    //       - EventOfClient31SetGameTypeSet
    //       -- используется с рефлексией,
    //       - EventOfClient61SetGameMatch (используется с рефлексией)
    //       -- используется с рефлексией,
    //       -- явно вызывается sendEventOfServer, в которую передаётся clientId.
    @Override
    public void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton, ClientId clientId) {
        // ToDo: Исправить Warning:(33, 9) Raw use of parameterized class 'IModelOfServer'
        IModelOfServer iModelOfServer = null;
        if (instanceIdOfModel.getId() == null) {
            remoteClientStateAutomaton.getTransportOfServer().sendEventOfServer(
                    clientId,
                    new EventOfServer60ForgetGameMatch()
            );
            return;
        }
        if (instanceIdOfModel.getId().equals("New game")) {
            // Определяем ранее выбранный тип
            GameType gameType = remoteClientStateAutomaton.getGameType();
            Constructor<? extends IModelOfServer> constructor = gameType.getConstructorOfModelOfServerClass();

            try {
                // Создаём экземпляр модели, ранее выбранного типа.
                // ToDo: Нужно минимизировать количество согласований между классами.
                //       Параметры, которые передаются в newInstance():
                //       1. Перечень параметров согласовывается с перечнем в
                //          GameType :: GameType(...)
                //          в строке
                //          constructorOfModelOfServerClass = modelOfServerClass.getConstructor(...);
                //          и там-же ниже в строке
                //          iModelOfServer = constructorOfModelOfServerClass.newInstance(...);
                //       2. Ну в т.ч. это, те-же параметры, которые поступили в executeOnServer().
                iModelOfServer = constructor.newInstance(remoteClientStateAutomaton, clientId);
            } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
                logger.error("Server cannot create object of model for {} with constructor with specific parameters.", gameType, e);
                System.exit(1);
            }
            remoteClientStateAutomaton
                    .getGameMatchSet()
                    // ToDo: Исправить Warning:(54, 26) Unchecked assignment: 'timmax.tilegame.basemodel.protocol.server.IModelOfServer' to 'timmax.tilegame.basemodel.protocol.server.IModelOfServer<ClientId>'
                    .add(iModelOfServer);
        }
        /*
          else {
            iModelOfServer = remoteClientState
                    .getGameMatchSet()
                    .stream()
                    .filter(x -> x.toString().equals(instanceIdOfModel.getId()))
                    .findAny()
                    .orElse(null)
            ;

            if (iModelOfServer == null) {
                logger.error("There is not model '" + instanceIdOfModel.getId() + "'");
                remoteClientState.forgetServerBaseModel();
                return;
            }
        }
        */
        // ToDo: Исправить Warning:(72, 87) Unchecked assignment: 'timmax.tilegame.basemodel.protocol.server.IModelOfServer' to 'timmax.tilegame.basemodel.protocol.server.IModelOfServer<ClientId>'
        remoteClientStateAutomaton.setGameMatch(iModelOfServer);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "instanceIdOfModel=" + instanceIdOfModel +
                '}';
    }

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(instanceIdOfModel);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        instanceIdOfModel = (InstanceIdOfModel) in.readObject();
    }
}

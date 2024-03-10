package timmax.tilegame.basemodel.protocol;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server.RemoteClientState;

public class EventOfClient41SetGameType extends EventOfClient {
    private ModelOfServerDescriptor modelOfServerDescriptor;

    public EventOfClient41SetGameType() {
        super();
    }

    public EventOfClient41SetGameType(ModelOfServerDescriptor modelOfServerDescriptor) {
        this();
        this.modelOfServerDescriptor = modelOfServerDescriptor;
    }

    @Override
    public <ClientId> void executeOnServer(RemoteClientState<ClientId> remoteClientState) {
        logger.debug("  onGameTypeSelect");
        logger.debug("  modelOfServerDescriptor = {}", modelOfServerDescriptor);
        if (modelOfServerDescriptor == null) {
            logger.error("Client sent empty name of model classes.");
            remoteClientState.forgetGameType();
            return;
        }
/*
        {   // ToDo: Вместо того, что в этих фигурных скобок, можно:
            //       1. Пересылать по сети только имя игры.
            //       2. Искать во множестве игр, игру с тем именем, которое пришло по сети.
            //          (И конструктор даже не вытаскивать!)
            // От клиента поступил modelOfServerDescriptor (он должен быть один из тех, которые ему направлялись множеством).
            Constructor<? extends IModelOfServer> constructor = remoteClientState
                    .getGameTypeSet()
                    .stream()
                    // В том перечне ищется modelOfServerDescriptor с таким-же именем:
                    .filter(x -> x.getGameName().equals(modelOfServerDescriptor.getGameName()))
                    .findAny()
                    // И берётся его конструктор:
                    .map(ModelOfServerDescriptor::getConstructorOfModelOfServerClass).orElse(null);

            // ToDo: условие (constructor == null) не должно случаться никогда, т.к. он был определён при создании каждого
            //       modelOfServerDescriptor ещё ранее на сервере, когда формировался перечень типов игр.
            //       Поэтому можно удалить if () {}.
            if (constructor == null) {
                logger.error("{} was not found in list model classes.", modelOfServerDescriptor);
                remoteClientState.forgetGameType();
                return;
            }
            modelOfServerDescriptor.setConstructor(constructor);
        }
*/
        ModelOfServerDescriptor modelOfServerDescriptor1 = remoteClientState
                .getGameTypeSet()
                .stream()
                // В том перечне ищется modelOfServerDescriptor с таким-же именем:
                .filter(x -> x.getGameName().equals(modelOfServerDescriptor.getGameName()))
                .findAny()
                .orElse(null);

        remoteClientState.setGameType(modelOfServerDescriptor1);
    }

    @Override
    public String toString() {
        return "EventOfClient41SetGameType{" +
                "modelOfServerDescriptor='" + modelOfServerDescriptor + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(modelOfServerDescriptor);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        modelOfServerDescriptor = (ModelOfServerDescriptor) in.readObject();
    }
}

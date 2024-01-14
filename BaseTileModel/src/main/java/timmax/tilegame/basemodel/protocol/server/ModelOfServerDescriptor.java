package timmax.tilegame.basemodel.protocol.server;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.EventOfServer;
import timmax.tilegame.transport.TransportOfServer;

public class ModelOfServerDescriptor {
    private final Constructor<? extends ModelOfServer<?>> constructorOfModelOfServerClass;
    private final String gameName;

    public ModelOfServerDescriptor(String modelOfServerString) throws ClassNotFoundException, NoSuchMethodException {
        Class<? extends ModelOfServer<?>> modelOfServerClass = (Class<? extends ModelOfServer<?>>) Class.forName(modelOfServerString);
        this.constructorOfModelOfServerClass = modelOfServerClass.getConstructor(TransportOfServer.class);

        ModelOfServer<?> obj;
        try {
            // Создаётся экземпляр. После работы в этом конструкторе он будет не нужен.
            // obj = constructorOfModelOfServerClass.newInstance(null); // Не получилось передать null.
            // Пришлось передать ему TransportOfServer - заглушку.
            obj = constructorOfModelOfServerClass.newInstance(new TransportOfServer<>() {
                @Override
                public void sendGameEventToRemoteView(RemoteView<Object> remoteView, GameEvent gameEvent) {
                }

                @Override
                public void sendEventOfServer(Object clientId, EventOfServer transportPackageOfServer) {
                }

                @Override
                public RemoteClientState<Object> getRemoteClientStateByClientId(Object clientId) {
                    return null;
                }

                @Override
                public Collection<ModelOfServerDescriptor> getCollectionOfModelOfServerDescriptor() {
                    return null;
                }
            });
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            System.err.println("Server cannot make object of model for " + modelOfServerString + " with concrete constructor.");
            e.printStackTrace();
            throw new RuntimeException("Server cannot make object of model for " + modelOfServerString + " with concrete constructor.");
        }

        // Читается у созданного экземпляра имя типа игры (но возможно и другие характеристики класса...)
        // Как-то нужно учесть, что имя игры может быть передано разным клиентам на разных языках.
        // Т.к. obj ничему не присваивается, то он уйдёт в небытие по окончанию работы конструктора.
        this.gameName = obj.getGameName();
    }

    public Constructor<? extends ModelOfServer<?>> getConstructorOfModelOfServerClass() {
        return constructorOfModelOfServerClass;
    }

    public String getGameName() {
        return gameName;
    }
}

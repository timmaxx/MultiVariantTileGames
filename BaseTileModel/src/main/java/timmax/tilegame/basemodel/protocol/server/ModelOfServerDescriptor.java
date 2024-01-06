package timmax.tilegame.basemodel.protocol.server;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.EventOfServer;
import timmax.tilegame.transport.TransportOfServer;

public class ModelOfServerDescriptor {
    private final Class<? extends ModelOfServer> modelOfServerClass;
    private final Constructor<? extends ModelOfServer> constructorOfModelOfServerClass;
    private final String gameName;

    public ModelOfServerDescriptor(String modelOfServerString) throws ClassNotFoundException, NoSuchMethodException {
        this.modelOfServerClass = (Class<? extends ModelOfServer>) Class.forName(modelOfServerString);
        this.constructorOfModelOfServerClass = modelOfServerClass.getConstructor(TransportOfServer.class);

        this.gameName = "Some game name";
/*
        Object obj;
        try {
            // Создаётся экземпляр. После работы в этом конструкторе он будет не нужен.
            // obj = constructorOfModelOfServerClass.newInstance(null);
            obj = constructorOfModelOfServerClass.newInstance(new TransportOfServer<Object>() {
                @Override
                public void sendGameEvent(GameEvent gameEvent) {
                }

                @Override
                public void sendGameEvent(RemoteView<Object> remoteView, GameEvent gameEvent) {
                }

                @Override
                public void send(Object clientId, EventOfServer transportPackageOfServer) {
                }

                @Override
                public ModelOfServer<Object> getModelOfServer() {
                    return null;
                }

                @Override
                public void setModelOfServer(ModelOfServer<Object> modelOfServer) {
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

        // Читаются у созданного экземпляра имя игры (но возможно и другие характеристики класса...)
        // Т.к. obj ничему не присваивается, то он уйдёт в небытие по окончанию работы конструктора.
        this.gameName = ((ModelOfServer)(obj)).getGameName();
        */
    }

    public Class<? extends ModelOfServer> getModelOfServerClass() {
        return modelOfServerClass;
    }

    public Constructor<? extends ModelOfServer> getConstructorOfModelOfServerClass() {
        return constructorOfModelOfServerClass;
    }

    public String getGameName() {
        return gameName;
    }
}

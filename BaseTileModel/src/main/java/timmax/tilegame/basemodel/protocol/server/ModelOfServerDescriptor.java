package timmax.tilegame.basemodel.protocol.server;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import timmax.tilegame.basemodel.protocol.EventOfServer;
import timmax.tilegame.basemodel.protocol.IModelOfServerDescriptor;
import timmax.tilegame.transport.TransportOfServer;

public class ModelOfServerDescriptor implements IModelOfServerDescriptor, Externalizable {
    // ToDo: Это поле нужно вынести в класс-наследник.
    private Constructor<? extends IModelOfServer<?>> constructorOfModelOfServerClass;

    // ToDo: Эти поля можно оставить в базовом (этом) классе:
    private String gameName;
    private int countOfGamers;
    // private String[] ; // тогда в этом массиве строк можно хранить имя роли каждого из игроков
    // (например для шашек: "Белые", "Черные"; для многих игр для двух игроков: "Первый", "Второй"; для одного: "Игрок").
    // И количество игроков по длине массива будет определено.

    public ModelOfServerDescriptor() {
    }

    public ModelOfServerDescriptor(String modelOfServerString) throws ClassNotFoundException, NoSuchMethodException {
        // ToDo: Избавиться от "Warning:(27, 64) Unchecked cast: 'java.lang.Class<capture<?>>' to 'java.lang.Class<? extends timmax.tilegame.basemodel.protocol.server.ModelOfServer<?>>'"
        Class<? extends IModelOfServer<?>> modelOfServerClass = (Class<? extends IModelOfServer<?>>) Class.forName(modelOfServerString);
        this.constructorOfModelOfServerClass = modelOfServerClass.getConstructor(TransportOfServer.class);

        IModelOfServer<?> iModelOfServer;
        try {
            // Создаётся экземпляр. После работы в этом конструкторе он будет не нужен.
            // iModelOfServer = constructorOfModelOfServerClass.newInstance(null); // Не получилось передать null.
            // Пришлось передать ему TransportOfServer - заглушку.
            iModelOfServer = constructorOfModelOfServerClass.newInstance(new TransportOfServer<>() {
                @Override
                public void sendEventOfServer(Object clientId, EventOfServer transportPackageOfServer) {
                }

                @Override
                public RemoteClientState<Object> getRemoteClientStateByClientId(Object clientId) {
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
        // Т.к. iModelOfServer ничему не присваивается, то он уйдёт в небытие по окончанию работы конструктора.
        gameName = iModelOfServer.getGameName();
        countOfGamers = iModelOfServer.getCountOfGamers();
        // this.otherField = obj.getOtherField();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModelOfServerDescriptor that = (ModelOfServerDescriptor) o;

        if (countOfGamers != that.countOfGamers) return false;
        return gameName.equals(that.gameName);
    }

    @Override
    public int hashCode() {
        int result = gameName.hashCode();
        result = 31 * result + countOfGamers;
        return result;
    }

    @Override
    public String toString() {
        return "ModelOfServerDescriptor{" +
                "constructorOfModelOfServerClass=" + constructorOfModelOfServerClass +
                ", gameName='" + gameName + '\'' +
                ", countOfGamers=" + countOfGamers +
                '}';
    }

    // Overriden methods from interface IModelOfServerDescriptor
    @Override
    public String getGameName() {
        return gameName;
    }

    @Override
    public int getCountOfGamers() {
        return countOfGamers;
    }

    // Overriden methods from interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(gameName);
        out.writeInt(countOfGamers);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        gameName = (String) in.readObject();
        countOfGamers = in.readInt();
    }

    // Own methods
    public Constructor<? extends IModelOfServer<?>> getConstructorOfModelOfServerClass() {
        return constructorOfModelOfServerClass;
    }

    // ToDo: Временный метод (потом удалить). После разделения на два класса, этот метод удалить,
    //       а инициализацию сделать через приватный метод и вызов из конструктора с параметром или readExternal.
    public void setConstructor(Constructor<? extends IModelOfServer<?>> constructor) {
        this.constructorOfModelOfServerClass = constructor;
    }
}

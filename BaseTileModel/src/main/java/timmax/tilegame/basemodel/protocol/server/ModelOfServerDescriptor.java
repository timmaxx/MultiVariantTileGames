package timmax.tilegame.basemodel.protocol.server;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import timmax.tilegame.basemodel.protocol.IModelOfServerDescriptor;
import timmax.tilegame.baseview.View;

public class ModelOfServerDescriptor implements IModelOfServerDescriptor, Externalizable {
    protected static final Logger logger = LoggerFactory.getLogger(ModelOfServerDescriptor.class);

    // ToDo: Это поле нужно вынести в класс-наследник.
    private Constructor<? extends IModelOfServer> constructorOfModelOfServerClass;

    // ToDo: Эти поля можно оставить в базовом (этом) классе:
    private String gameName;
    private int countOfGamers;
    // private String[] ; // тогда в этом массиве строк можно хранить имя роли каждого из игроков
    // (например для шашек: "Белые", "Черные"; для многих игр для двух игроков: "Первый", "Второй"; для одного: "Игрок").
    // И количество игроков по длине массива будет определено.

    private Map<String, Class <? extends View>> mapOfViewNameViewClass;
    protected Map<String, Integer> mapOfParamsOfModel;

    public ModelOfServerDescriptor() {
    }

    public <ClientId> ModelOfServerDescriptor(String modelOfServerFullClassName,
                                   // ToDo: Возможно перечень выборок здесь и не нужен.
                                   //       Пересмотреть архитектуру и возможно удалить.
                                   //       Также см. ModelOfServerLoader
                                   Map<String, Class <? extends View>> mapOfViewNameViewClass,
                                   RemoteClientState<ClientId> remoteClientState)
            throws ClassNotFoundException, NoSuchMethodException {
        this.mapOfViewNameViewClass = mapOfViewNameViewClass;
        // ToDo: Избавиться от "Warning:(45, 62) Unchecked cast: 'java.lang.Class<capture<?>>' to 'java.lang.Class<? extends timmax.tilegame.basemodel.protocol.server.IModelOfServer>'"
        Class<? extends IModelOfServer> modelOfServerClass = (Class<? extends IModelOfServer>) Class.forName(modelOfServerFullClassName);
        this.constructorOfModelOfServerClass = modelOfServerClass.getConstructor(RemoteClientState.class);

        IModelOfServer iModelOfServer;
        try {
            // Создаётся экземпляр. После работы в этом конструкторе он будет не нужен.
            iModelOfServer = constructorOfModelOfServerClass.newInstance(remoteClientState);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            String errMessage = "Server cannot make object of model for " + modelOfServerFullClassName + " with concrete constructor.";
            logger.error(errMessage, e);
            throw new RuntimeException();
        }

        // Читается у созданного экземпляра имя типа игры (но возможно и другие характеристики класса...)
        // Как-то нужно учесть, что имя игры может быть передано разным клиентам на разных языках.
        // Т.к. iModelOfServer ничему не присваивается, то он уйдёт в небытие по окончанию работы конструктора.
        gameName = iModelOfServer.getGameName();
        countOfGamers = iModelOfServer.getCountOfGamers();
        mapOfParamsOfModel = iModelOfServer.getMapOfParamsOfModel();
        // this.otherField = obj.getOtherField();
    }

    public Map<String, Class<? extends View>> getMapOfViewNameViewClass() {
        return mapOfViewNameViewClass;
    }

    public Map<String, Integer> getMapOfParamsOfModel() {
        if (mapOfParamsOfModel == null) {
            mapOfParamsOfModel = Map.of();
        }
        return mapOfParamsOfModel;
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
                ", mapOfViewNameViewClass=" + mapOfViewNameViewClass +
                ", mapOfParamsOfModel=" + mapOfParamsOfModel +
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
        out.writeObject(mapOfViewNameViewClass);
        out.writeObject(mapOfParamsOfModel);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        gameName = (String) in.readObject();
        countOfGamers = in.readInt();
        // ToDo: Избавиться от "Warning:(132, 34) Unchecked cast: 'java.lang.Object' to 'java.util.Map<java.lang.String,java.lang.Class<? extends timmax.tilegame.baseview.View>>'"
        mapOfViewNameViewClass = (Map<String, Class<? extends View>>) in.readObject();
        // ToDo: Избавиться от "Warning:(134, 30) Unchecked cast: 'java.lang.Object' to 'java.util.Map<java.lang.String,java.lang.Integer>'"
        mapOfParamsOfModel = (Map<String, Integer>) in.readObject();
    }

    // Own methods
    public Constructor<? extends IModelOfServer> getConstructorOfModelOfServerClass() {
        return constructorOfModelOfServerClass;
    }

    // ToDo: Временный метод (потом удалить). После разделения на два класса, этот метод удалить,
    //       а инициализацию сделать через приватный метод и вызов из конструктора с параметром или readExternal.
    public void setConstructor(Constructor<? extends IModelOfServer> constructor) {
        this.constructorOfModelOfServerClass = constructor;
    }
}

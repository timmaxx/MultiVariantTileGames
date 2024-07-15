package timmax.tilegame.basemodel.protocol.server;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Constructor;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import timmax.tilegame.basemodel.protocol.IGameType;
import timmax.tilegame.baseview.View;
import timmax.tilegame.baseview.ViewMainField;

public abstract class GameType implements IGameType, Externalizable {
    protected static final Logger logger = LoggerFactory.getLogger(GameType.class);

    // ToDo: Рассмотреть вариант выделения из этого класса "String gameTypeName" в отдельный класс GameTypeName.
    //       Тогда EventOfServer31SetGameTypeSet будет передавать "Set<GameType> gameTypeSet",
    //       а EventOfClient41SetGameType будет передавать не "String gameTypeName", а "GameTypeName gameTypeName".
    //       Причины:
    //          Среди реквизитов класса, идентифицирующим (типа первичным ключём) является gameName.
    //          Поэтому, при передаче полной информации о типе игры нужно передавать все поля.
    //          А вот при передаче как-бы ссылки на тип игры, достаточно передать только gameName.
    //          И похожим образом сделано для идентификации GameMatch (см. коммент для GameMatchId)
    private String gameTypeName;

    // ToDo: Переименовать
    private Constructor<? extends IGameMatch> constructorOfGameMatch;
    private int countOfGamers;
    // private String[] ; // тогда в этом массиве строк можно хранить имя роли каждого из игроков
    // (например для шашек: "Белые", "Черные"; для многих игр для двух игроков: "Первый", "Второй"; для одного: "Игрок").
    // И количество игроков по длине массива будет определено.

    private Map<String, Class<? extends View>> viewName_ViewClassMap;
    protected Map<String, ParamOfModelDescription> paramName_paramModelDescriptionMap;

    public GameType() {
        super();
    }

    public GameType(
            String gameTypeName,
            int countOfGamers,
            Class<? extends IGameMatch> gameMatchClass)
            throws ClassNotFoundException, NoSuchMethodException {
        this();
        this.gameTypeName = gameTypeName;
        this.countOfGamers = countOfGamers;

        // ToDo: Мапу нужно инициализировать не как сейчас - константой, а в классе найти все выборки View.class, в т.ч. и ViewMainField.class.
        viewName_ViewClassMap = Map.of("MainField", ViewMainField.class);

        // ToDo: Нужно минимизировать количество согласований в методах и между классами.
        //       Параметры, которые передаются в getConstructor() и ниже newInstance(), также согласуются с параметрами в
        //       GameMatchLoader :: getCollectionOfGameType()
        //       и внутри того метода с параметрами при вызове
        //       gameType = new GameType()
        //       и там же ниже в ветке
        //       catch (NoSuchMethodException e)
        //       при логировании.
        // ToDo: Одним из типов параметров указан Object.class. Сделал так, т.к. не знаю как указать параметризированный тип.
        constructorOfGameMatch = gameMatchClass.getConstructor(RemoteClientStateAutomaton.class, Object.class);
    }

    public Map<String, Class<? extends View>> getViewName_ViewClassMap() {
        return viewName_ViewClassMap;
    }

    public Map<String, ParamOfModelDescription> getParamName_paramModelDescriptionMap() {
        if (paramName_paramModelDescriptionMap == null) {
            paramName_paramModelDescriptionMap = Map.of();
        }
        return paramName_paramModelDescriptionMap;
    }

    public Constructor<? extends IGameMatch> getConstructorOfGameMatch() {
        return constructorOfGameMatch;
    }

    // class Object
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameType that = (GameType) o;

        if (countOfGamers != that.countOfGamers) return false;
        return gameTypeName.equals(that.gameTypeName);
    }

    @Override
    public int hashCode() {
        int result = gameTypeName.hashCode();
        result = 31 * result + countOfGamers;
        return result;
    }

    @Override
    public String toString() {
        return "GameType{" +
                "constructorOfGameMatch=" + constructorOfGameMatch +
                ", gameTypeName='" + gameTypeName + '\'' +
                ", countOfGamers=" + countOfGamers +
                ", viewName_ViewClassMap=" + viewName_ViewClassMap +
                ", paramName_paramModelDescriptionMap=" + paramName_paramModelDescriptionMap +
                '}';
    }

    // interface IGameType
    @Override
    public String getGameTypeName() {
        return gameTypeName;
    }

    @Override
    public int getCountOfGamers() {
        return countOfGamers;
    }

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(gameTypeName);
        out.writeInt(countOfGamers);
        out.writeObject(viewName_ViewClassMap);
        out.writeObject(paramName_paramModelDescriptionMap);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        gameTypeName = (String) in.readObject();
        countOfGamers = in.readInt();
        // ToDo: Избавиться от "Warning:(139, 34) Unchecked cast: 'java.lang.Object' to 'java.util.Map<java.lang.String,java.lang.Class<? extends timmax.tilegame.baseview.View>>'"
        viewName_ViewClassMap = (Map<String, Class<? extends View>>) in.readObject();
        // ToDo: Избавиться от "Warning:(143, 41) Unchecked cast: 'java.lang.Object' to 'java.util.Map<java.lang.String,timmax.tilegame.basemodel.protocol.server.ParamOfModelDescription>'"
        paramName_paramModelDescriptionMap = (Map<String, ParamOfModelDescription>) in.readObject();
    }
}

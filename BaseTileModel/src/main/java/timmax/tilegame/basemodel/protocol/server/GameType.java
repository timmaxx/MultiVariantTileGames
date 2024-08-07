package timmax.tilegame.basemodel.protocol.server;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Constructor;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.EventOfServer;
import timmax.tilegame.basemodel.protocol.EventOfServer92GameEvent;
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
    private Constructor<? extends IGameMatch> gameMatchConstructor;
    // private int countOfGamers;

    // private String[] ; // тогда в этом массиве строк можно хранить имя роли каждого из игроков
    // (например для шашек: "Белые", "Черные"; для многих игр для двух игроков: "Первый", "Второй"; для одного: "Игрок").
    // И количество игроков по длине массива будет определено.

    // ToDo: Доступ к полю ч/з геттер - нехорошо. Было бы лучше цикл (из конструктора GameClientPaneJfx) перенести:
    //       - либо в этот класс (GameType),
    //       - либо в ClientStateAutomaton (но геттер сделать protected),
    //       - либо в LocalClientStateAutomaton (но геттер сделать protected) - наверное проще сюда.
    //       Поле (через геттеры в GameType, ClientStateAutomaton, LocalClientStateAutomaton)
    //       используется только в конструкторе GameClientPaneJfx.
    //       И там идёт цикл по этому множеству и генерируется множество ViewJfx.
    private Map<String, Class<? extends View>> viewName_ViewClassMap;
    protected ParamName_paramModelDescriptionMap paramName_paramModelDescriptionMap;

    public GameType() {
        super();
    }

    public GameType(
            String gameTypeName,
            //int countOfGamers,
            Class<? extends IGameMatch> gameMatchClass)
            throws ClassNotFoundException, NoSuchMethodException {
        this();
        this.gameTypeName = gameTypeName;
        //this.countOfGamers = countOfGamers;

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
        gameMatchConstructor = gameMatchClass.getConstructor(RemoteClientStateAutomaton.class, Object.class);

        paramName_paramModelDescriptionMap = new ParamName_paramModelDescriptionMap();
    }

    // ToDo: Отказаться от прямого доступа к viewName_ViewClassMap из вне класса.
    public Map<String, Class<? extends View>> getViewName_ViewClassMap() {
        return viewName_ViewClassMap;
    }

    // ToDo: Отказаться от прямого доступа к paramName_paramModelDescriptionMap из вне класса.
    public final ParamName_paramModelDescriptionMap getParamName_paramModelDescriptionMap() {
        return paramName_paramModelDescriptionMap;
    }

    public Constructor<? extends IGameMatch> getGameMatchConstructor() {
        return gameMatchConstructor;
    }

    public <ClientId> void sendGameEventToAllViews(GameEvent gameEvent, RemoteClientStateAutomaton remoteClientStateAutomaton, ClientId clientId) {
        for (String viewName : viewName_ViewClassMap.keySet()) {
            EventOfServer eventOfServer = new EventOfServer92GameEvent(viewName, gameEvent);
            remoteClientStateAutomaton.sendEventOfServer(clientId, eventOfServer);
        }
    }

    // class Object
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameType gameType = (GameType) o;

        return gameTypeName.equals(gameType.gameTypeName);
    }

    @Override
    public int hashCode() {
        return gameTypeName.hashCode();
    }

    @Override
    public String toString() {
        return "GameType{" +
                "gameMatchConstructor=" + gameMatchConstructor +
                ", gameTypeName='" + gameTypeName + '\'' +
                //", countOfGamers=" + countOfGamers +
                ", viewName_ViewClassMap=" + viewName_ViewClassMap +
                ", paramName_paramModelDescriptionMap=" + paramName_paramModelDescriptionMap +
                '}';
    }

    // interface IGameType
    @Override
    public String getGameTypeName() {
        return gameTypeName;
    }

    /*
        @Override
        public int getCountOfGamers() {
            return countOfGamers;
        }
    */

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(gameTypeName);
        // out.writeInt(countOfGamers);
        out.writeObject(viewName_ViewClassMap);
        out.writeObject(paramName_paramModelDescriptionMap);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        gameTypeName = (String) in.readObject();
        // countOfGamers = in.readInt();
        // ToDo: Избавиться от "Warning:(153, 34) Unchecked cast: 'java.lang.Object' to 'java.util.Map<java.lang.String,java.lang.Class<? extends timmax.tilegame.baseview.View>>'"
        viewName_ViewClassMap = (Map<String, Class<? extends View>>) in.readObject();
        // ToDo: Избавиться от "Warning:(155, 41) Unchecked cast: 'java.lang.Object' to 'java.util.Map<java.lang.String,timmax.tilegame.basemodel.protocol.server.ParamOfModelDescription>'"
        paramName_paramModelDescriptionMap = (ParamName_paramModelDescriptionMap) in.readObject();
    }
}

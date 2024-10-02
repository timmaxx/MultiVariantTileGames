package timmax.tilegame.basemodel.protocol.server;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import timmax.common.JFXColorWithExternalizable;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.gameobject.OneTileGameObjectStateAutomaton;
import timmax.tilegame.basemodel.protocol.EventOfServer;
import timmax.tilegame.basemodel.protocol.EventOfServer92GameEvent;
import timmax.tilegame.basemodel.protocol.IGameType;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchDto;
import timmax.tilegame.basemodel.protocol.server_client.IGameMatchX;
import timmax.tilegame.baseview.View;
import timmax.tilegame.baseview.ViewMainField;

public abstract class GameType<GameMatchX extends IGameMatchX> implements IGameType, Externalizable {
    protected static final Logger logger = LoggerFactory.getLogger(GameType.class);

    // ToDo: Рассмотреть вариант выделения из этого класса "String gameTypeName" в отдельный класс GameTypeName.
    //       Тогда EventOfServer41SeletGameTypeSet будет передавать "Set<GameType> gameTypeSet",
    //       а EventOfClient41SelectGameType будет передавать не "String gameTypeName", а "GameTypeName gameTypeName".
    //       Причины:
    //          Среди реквизитов класса, идентифицирующим (типа первичным ключём) является gameName.
    //          Поэтому, при передаче полной информации о типе игры нужно передавать все поля.
    //          А вот при передаче как-бы ссылки на тип игры, достаточно передать только gameName.
    //          И похожим образом сделано для идентификации GameMatch (см. коммент для GameMatchDto)
    private String id;
    private int countOfGamers;

    //  Множество классов объектов. (Возможно убрать это в GameType)
    //      Например, для Шахмат:
    //          Король, ферзь, слон, конь, ладья, пешка.
    //      Например, для Шашек:
    //          Шашка, дамка.
    //      Например, для Сапёра:
    //          Закрытое поле, флаг, открытое поле (без мины), мина.
    //      Например, для Сокобан:
    //          Игрок, коробка, стена, дом.
    //  ToDo:   Элементами Set должны быть только классы, являющиеся наследниками класса
    //          OneTileGameObject.
    //          Сейчас это соответствие не отслеживается в классах-наследниках GameType.
    //          Смотри конструкторы в GameTypeOfMinesweeper и в GameTypeOfSokoban.
    //          Это пример того, как хотелось-бы что-бы компилятор отреагировал при компиляции в этих конструкторах:
    //              - компилятор возражает и это хорошо:
    //                  Set<Class<? extends OneTileGameObject>> abcClassSet1 = Set.of(Object.class);
    //              - компилятор не возражает и это тоже хорошо:
    //                  Set<Class<? extends OneTileGameObject>> abcClassSet2 = Set.of(OneTileGameObject.class);
    private Set<Class<OneTileGameObjectStateAutomaton>> oneTileGameObjectStateAutomaton_Class_Set;

    private Set<GameMatchX> gameMatchXSet;

    //  ToDo:   При наличии предыдущего - этот лишний.
    private Set<GameMatchDto> gameMatchDtoSet;

    //  ToDo:   Может тоже <GameMatchX>?
    private Constructor<? extends IGameMatch> gameMatchConstructor;

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

    //  ToDo:   Поля ниже относятся к визуализации. Их нужно абстрагировать и визуальную часть вынести отсюда.
    private Color defaultCellBackgroundColor;
    private Color defaultCellTextColor;
    private String defaultCellTextValue;

    public GameType() {
        super();
    }

    public GameType(
            String id,
            int countOfGamers,
            Set<Class<OneTileGameObjectStateAutomaton>> oneTileGameObjectStateAutomaton_Class_Set,
            Class<? extends IGameMatch> gameMatchClass,
            Color defaultCellBackgroundColor,
            Color defaultCellTextColor,
            String defaultCellTextValue)
            throws ClassNotFoundException, NoSuchMethodException {
        this();
        this.id = id;
        this.countOfGamers = countOfGamers;
        this.oneTileGameObjectStateAutomaton_Class_Set = oneTileGameObjectStateAutomaton_Class_Set;
        this.defaultCellBackgroundColor = defaultCellBackgroundColor;
        this.defaultCellTextColor = defaultCellTextColor;
        this.defaultCellTextValue = defaultCellTextValue;

        // ToDo: Мапу нужно инициализировать не как сейчас - константой, а в классе (или пакете...) найти все выборки,
        //       реализующие View.class, в т.ч. и ViewMainField.class.
        viewName_ViewClassMap = Map.of(ViewMainField.class.getSimpleName(), ViewMainField.class);

        // ToDo: Нужно минимизировать количество согласований в методах и между классами.
        //       Параметры, которые передаются в getConstructor() и ниже newInstance(), также согласуются с параметрами в
        //       GameMatchLoader :: getCollectionOfGameType()
        //       и внутри того метода с параметрами при вызове
        //       gameType = new GameType()
        //       и там же ниже в ветке
        //       catch (NoSuchMethodException e)
        //       при логировании.
        gameMatchConstructor = gameMatchClass.getConstructor(RemoteClientStateAutomaton.class);

        paramName_paramModelDescriptionMap = new ParamName_paramModelDescriptionMap();
    }

    public int getCountOfGamers() {
        return countOfGamers;
    }

    public Set<GameMatchX> getGameMatchXSet() {
        return gameMatchXSet;
    }

    public Color getDefaultCellBackgroundColor() {
        return defaultCellBackgroundColor;
    }

    public Color getDefaultCellTextColor() {
        return defaultCellTextColor;
    }

    public String getDefaultCellTextValue() {
        return defaultCellTextValue;
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

    public <ClientId> void sendGameEventToAllViews(GameEvent gameEvent, RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton) {
        for (String viewName : viewName_ViewClassMap.keySet()) {
            EventOfServer eventOfServer = new EventOfServer92GameEvent(viewName, gameEvent);
            remoteClientStateAutomaton.sendEventOfServer(remoteClientStateAutomaton.getClientId(), eventOfServer);
        }
    }

    public <ClientId> void initGameMatchXSet(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton) {
        gameMatchXSet = new HashSet<>();
        // Done:
        //       1.1. При создании перечня матчей на сервере,
        //            если список не содержит ни одного матча в состоянии "Не начат",
        //            то сервер сам создаёт такой матч.
        //            В этом случае на клиент всегда будет отправляться перечень как минимум с одним матчем.
        //            Т.е. и логика в этом методе уйдёт большей частью в более ранний класс.
        //       1.2. Клиент должен работать только с таким списком, который поступил от сервера
        //            (т.е. не создавать новую запись, а только выбирать).
        //            И только для не игранного матча должна быть доступна возможность редактировать параметры матча.
        //            А для игранного (матч на паузе):
        //            - на клиенте опционально: не давать возможность редактировать в принципе.
        //            - на сервере обязательно: проверять попытку изменить параметры матча и возвращать актуальные
        //                значения на клиент.
        // ToDo:
        //       1.2.1. Однако потом нужно будет вернуться к возможности удалять или как-то скидывать в архив
        //              - начатые, но не оконченные (на паузе) партии.
        //              - начатые и оконченные партии - для возможности ознакомления с ними.

        GameMatchX gameMatchX = null;
        Constructor<? extends IGameMatch> GameMatchConstructor = getGameMatchConstructor();

        try {
            // Создаём экземпляр модели, ранее выбранного типа.
            // ToDo: Нужно минимизировать количество согласований между классами.
            //       Параметры, которые передаются в newInstance():
            //       1. Перечень параметров согласовывается с перечнем в
            //          GameType :: GameType(...)
            //          в строке
            //          GameMatchConstructor = gameMatchClass.getConstructor(...);
            //          и там-же ниже в строке
            //          iGameMatch = GameMatchConstructor.newInstance(...);
            //       2. Ну в т.ч. это, те-же параметры, которые поступили в executeOnServer().
            //  ToDo:   Приведение типа?
            //  Warning:(193, 26) Unchecked cast: 'capture<? extends timmax.tilegame.basemodel.protocol.server.IGameMatch>' to 'GameMatchX'
            gameMatchX = (GameMatchX) GameMatchConstructor.newInstance(remoteClientStateAutomaton);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            logger.error("Server cannot create object of model for {} with GameMatchConstructor with specific parameters.", this, e);
            System.exit(1);
        }
        gameMatchXSet.add(gameMatchX);
    }

    public Set<GameMatchDto> getGameMatchDtoSet() {
        return gameMatchDtoSet;
    }

    public void setGameMatchDtoSet(Set<GameMatchDto> gameMatchDtoSet) {
        this.gameMatchDtoSet = gameMatchDtoSet;
    }

    // interface IGameType
    @Override
    public String getId() {
        return id;
    }

    // interface Externalizable
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(id);
        out.writeInt(countOfGamers);
        out.writeObject(gameMatchDtoSet);
        out.writeObject(viewName_ViewClassMap);
        out.writeObject(paramName_paramModelDescriptionMap);

        // Тип Color не сереализуемый, поэтому сериализуем его через дополнительный класс:
        out.writeObject(new JFXColorWithExternalizable(defaultCellBackgroundColor));
        out.writeObject(new JFXColorWithExternalizable(defaultCellTextColor));

        out.writeObject(defaultCellTextValue);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = (String) in.readObject();
        countOfGamers = in.readInt();
        //          Warning:(236, 27) Unchecked cast: 'java.lang.Object' to 'java.util.Set<timmax.tilegame.basemodel.protocol.server_client.GameMatchDto>'
        gameMatchDtoSet = (Set<GameMatchDto>) in.readObject();
        //  ToDo:   Избавиться от "Warning:(239, 33) Unchecked cast: 'java.lang.Object' to 'java.util.Map<java.lang.String,java.lang.Class<? extends timmax.tilegame.baseview.View>>'"
        //          https://sky.pro/wiki/java/reshaem-preduprezhdenie-unchecked-cast-v-java-spring/
        viewName_ViewClassMap = (Map<String, Class<? extends View>>) in.readObject();
        paramName_paramModelDescriptionMap = (ParamName_paramModelDescriptionMap) in.readObject();

        // Тип Color не сереализуемый, поэтому десериализуем его через дополнительный класс:
        defaultCellBackgroundColor = ((JFXColorWithExternalizable) in.readObject()).getColor();
        defaultCellTextColor = ((JFXColorWithExternalizable) in.readObject()).getColor();

        defaultCellTextValue = (String) in.readObject();
    }

    // class Object
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        //  Warning:(243, 9) Raw use of parameterized class 'GameType'
        GameType gameType = (GameType) o;

        return id.equals(gameType.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "GameType{" +
                "gameMatchConstructor=" + gameMatchConstructor +
                ", gameTypeName='" + id + '\'' +
                ", countOfGamers='" + countOfGamers +
                ", gameMatchXSet=" + gameMatchXSet +
                ", gameMatchDtoSet=" + gameMatchDtoSet +
                ", viewName_ViewClassMap=" + viewName_ViewClassMap +
                ", paramName_paramModelDescriptionMap=" + paramName_paramModelDescriptionMap +
                '}';
    }
}

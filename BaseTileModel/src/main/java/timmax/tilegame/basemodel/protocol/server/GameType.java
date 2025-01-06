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

import timmax.tilegame.basemodel.protocol.IGameType;
import timmax.tilegame.basemodel.dto.GameMatchDto;
import timmax.tilegame.basemodel.protocol.server_client.GuiDefaultConstants;
import timmax.tilegame.basemodel.protocol.server_client.IGameMatchX;
import timmax.tilegame.baseview.View;
import timmax.tilegame.baseview.ViewMainField;

//  ToDo:   Не реализовывать Externalizable!
//          Отдельно должен быть один или несколько DTO, в которых реализовать Externalizable.
public class GameType implements IGameType, Externalizable {
    protected static final Logger logger = LoggerFactory.getLogger(GameType.class);

    // ToDo: Рассмотреть вариант выделения из этого класса "String gameTypeName" в отдельный класс GameTypeName.
    //       Тогда EventOfServer41SetGameTypeSet будет передавать "Set<GameType> gameTypeSet",
    //       а EventOfClient41SetGameType будет передавать не "String gameTypeName", а "GameTypeName gameTypeName".
    //       Причины:
    //          Среди реквизитов класса, идентифицирующим (типа первичным ключём) является gameName.
    //          Поэтому, при передаче полной информации о типе игры нужно передавать все поля.
    //          А вот при передаче как-бы ссылки на тип игры, достаточно передать только gameName.
    //          И похожим образом сделано для идентификации GameMatch (см. коммент для GameMatchDto)
    private String id;
    private int countOfGamers;

    private Set<IGameMatchX> gameMatchXSet;

    //  ToDo:   При наличии предыдущего - этот лишний.
    private Set<GameMatchDto> gameMatchDtoSet;

    //  ToDo:   Может тоже <GameMatchX>?
    private Constructor<? extends IGameMatchX> gameMatchConstructor;

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

    //  ToDo:   Поле относится к визуализации. Его нужно абстрагировать и вынести отсюда.
    private GuiDefaultConstants guiDefaultConstants;

    public GameType() {
        super();
    }

    public GameType(
            String id,
            int countOfGamers,
//            Set<Class<? extends GameObjectStateAutomaton>> gameObjectStateAutomaton_Class_Set,
            Class<? extends IGameMatchX> gameMatchClass,
            GuiDefaultConstants guiDefaultConstants)
            throws ClassNotFoundException, NoSuchMethodException {
        this();
        this.id = id;
        this.countOfGamers = countOfGamers;
/*
        //  Множество классов объектов.
        //      Например, для Шахмат:
        //          Король, ферзь, слон, конь, ладья, пешка.
        //      Например, для Шашек:
        //          Шашка, дамка.
        //      Например, для Сапёра:
        //          Закрытое поле, флаг, открытое поле (без мины), мина.
        //      Например, для Сокобан:
        //          Игрок, коробка, стена, дом.
        //  ToDo:   Элементами Set должны быть только классы, являющиеся наследниками класса
        //          GameObject.
        //          Сейчас это соответствие не отслеживается в классах-наследниках GameType.
        //          Смотри конструкторы в GameTypeOfMinesweeper и в GameTypeOfSokoban.
        //          Это пример того, как хотелось-бы что-бы компилятор отреагировал при компиляции в этих конструкторах:
        //              - компилятор возражает и это хорошо:
        //                  Set<Class<? extends GameObject>> abcClassSet1 = Set.of(Object.class);
        //              - компилятор не возражает и это тоже хорошо:
        //                  Set<Class<? extends GameObject>> abcClassSet2 = Set.of(GameObject.class);
*/

        this.guiDefaultConstants = guiDefaultConstants;

        //  ToDo:   Мапу нужно инициализировать не как сейчас - константой, а в классе (или пакете...) найти все выборки,
        //          реализующие View.class, в т.ч. и ViewMainField.class.
        viewName_ViewClassMap = Map.of(ViewMainField.class.getSimpleName(), ViewMainField.class);

        //  ToDo:   Нужно минимизировать количество согласований в методах и между классами.
        //          Параметры, которые передаются в getConstructor() и ниже newInstance(), также согласуются с параметрами в
        //          GameMatchLoader :: getCollectionOfGameType()
        //          и внутри того метода с параметрами при вызове
        //          gameType = new GameType()
        //          и там же ниже в ветке
        //          catch (NoSuchMethodException e)
        //          при логировании.
        gameMatchConstructor = gameMatchClass.getConstructor(RemoteClientStateAutomaton.class);

        paramName_paramModelDescriptionMap = new ParamName_paramModelDescriptionMap();
    }

    public int getCountOfGamers() {
        return countOfGamers;
    }

    public Set<IGameMatchX> getGameMatchXSet() {
        return gameMatchXSet;
    }

    public Color getDefaultCellBackgroundColor() {
        return guiDefaultConstants.getDefaultCellBackgroundColor();
    }

    public Color getDefaultCellTextColor() {
        return guiDefaultConstants.getDefaultCellTextColor();
    }

    public String getDefaultCellTextValue() {
        return guiDefaultConstants.getDefaultCellTextValue();
    }

    //  ToDo:   Отказаться от прямого доступа к viewName_ViewClassMap извне класса.
    public Map<String, Class<? extends View>> getViewName_ViewClassMap() {
        return viewName_ViewClassMap;
    }

    //  ToDo:   Отказаться от прямого доступа к paramName_paramModelDescriptionMap извне класса.
    public final ParamName_paramModelDescriptionMap getParamName_paramModelDescriptionMap() {
        return paramName_paramModelDescriptionMap;
    }

    public Constructor<? extends IGameMatchX> getGameMatchConstructor() {
        return gameMatchConstructor;
    }

    public void initGameMatchXSet(RemoteClientStateAutomaton remoteClientStateAutomaton) {
        gameMatchXSet = new HashSet<>();
        //  Done:
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
        //  ToDo:
        //       1.2.1. Однако потом нужно будет вернуться к возможности удалять или как-то скидывать в архив
        //              - начатые, но не оконченные (на паузе) партии.
        //              - начатые и оконченные партии - для возможности ознакомления с ними.

        IGameMatchX gameMatchX = null;
        Constructor<? extends IGameMatchX> gameMatchConstructor = getGameMatchConstructor();

        try {
            // Создаём экземпляр модели, ранее выбранного типа.
            //  ToDo:   Нужно минимизировать количество согласований между классами.
            //          Параметры, которые передаются в newInstance():
            //          1. Перечень параметров согласовывается с перечнем в
            //              GameType :: GameType(...)
            //              в строке
            //              gameMatchConstructor = gameMatchClass.getConstructor(...);
            //              и там-же ниже в строке
            //              iGameMatch = gameMatchConstructor.newInstance(...);
            //          2. Ну в т.ч. это, те-же параметры, которые поступили в executeOnServer().
            gameMatchX = gameMatchConstructor.newInstance(remoteClientStateAutomaton);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            logger.error("Server cannot create object of model for {} with gameMatchConstructor with specific parameters.", this, e);
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
        out.writeObject(guiDefaultConstants);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = (String) in.readObject();
        countOfGamers = in.readInt();
        //  Warning:(225, 27) Unchecked cast: 'java.lang.Object' to 'java.util.Set<timmax.tilegame.basemodel.dto.GameMatchDto>'
        gameMatchDtoSet = (Set<GameMatchDto>) in.readObject();
        //  Warning:(228, 33) Unchecked cast: 'java.lang.Object' to 'java.util.Map<java.lang.String,java.lang.Class<? extends timmax.tilegame.baseview.View>>'
        //  https://sky.pro/wiki/java/reshaem-preduprezhdenie-unchecked-cast-v-java-spring/
        viewName_ViewClassMap = (Map<String, Class<? extends View>>) in.readObject();
        paramName_paramModelDescriptionMap = (ParamName_paramModelDescriptionMap) in.readObject();
        guiDefaultConstants = (GuiDefaultConstants) in.readObject();
    }

    // class Object
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

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
                ", guiDefaultConstants=" + guiDefaultConstants +
                ", paramName_paramModelDescriptionMap=" + paramName_paramModelDescriptionMap +
                '}';
    }
}

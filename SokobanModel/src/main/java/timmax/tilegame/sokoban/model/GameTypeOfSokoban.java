package timmax.tilegame.sokoban.model;

import javafx.scene.paint.Color;
import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.sokoban.model.placement.gameobject.SGOBox;
import timmax.tilegame.sokoban.model.placement.gameobject.SGOHome;
import timmax.tilegame.sokoban.model.placement.gameobject.SGOPlayer;
import timmax.tilegame.sokoban.model.placement.gameobject.SGOWall;

import java.util.Set;

import static javafx.scene.paint.Color.*;

//  Warning:(9, 40) Raw use of parameterized class 'GameType'
public class GameTypeOfSokoban extends GameType {
    // ToDo: Удалить это отсюда константы, описанные ниже, т.к. они относятся к визуализации.
    public static final Color WALL_BACKGROUND_COLOR = RED;
    public static final Color HOME_BACKGROUND_COLOR = WHITE;
    public static final Color EMPTY_BACKGROUND_COLOR = BLACK;

    public static final String NOBODY = "";
    public static final Color NOBODY_TEXT_COLOR = BLACK;

    public static final String PLAYER = "😀"; // "\uF9CD"; // "&";
    public static final Color PLAYER_TEXT_COLOR = GREEN;

    public static final String BOX = "█"; // "❐"; // "▉"; // "[]";
    public static final Color BOX_TEXT_COLOR = BLUE;

    public GameTypeOfSokoban() throws ClassNotFoundException, NoSuchMethodException {
        //  ToDo:   Warning:(32, 9) Unchecked call to 'GameType(String, int, Class<? extends IGameMatch>, Color, Color, String)' as a member of raw type 'timmax.tilegame.basemodel.protocol.server.GameType'
        super("Sokoban",
                1,
                //  ToDo:   Элементами Set должны быть только классы, являющиеся наследниками класса
                //          MinesweeperGameObject (который уже наследник GameObject).
                //          Сейчас это соответствие не отслеживается, например можно написать так:
                //              Set.of(Object.class),
                //          и компилятор ничего не скажет.
                Set.of(SGOPlayer.class, SGOHome.class, SGOBox.class, SGOWall.class),
                GameMatchOfSokoban.class,
                EMPTY_BACKGROUND_COLOR, NOBODY_TEXT_COLOR, NOBODY
        );

        //  Это пример того, как хотелось-бы что-бы компилятор отреагировал в предыдущих строках
        //  (в этих примерах потом заменить Tile на SokobanGameObject):
        //      - компилятор возражает и это хорошо:
        //  Set<Class<? extends Tile>> abcClassSet1 = Set.of(Player.class, Home.class, Box.class, Object.class);
        //      - компилятор не возражает и это тоже хорошо:
        //  Set<Class<? extends Tile>> abcClassSet2 = Set.of(Player.class, Home.class, Box.class);
    }
}

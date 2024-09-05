package timmax.tilegame.game.sokoban.model;

import javafx.scene.paint.Color;
import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.game.sokoban.model.gameobject.Box;
import timmax.tilegame.game.sokoban.model.gameobject.Home;
import timmax.tilegame.game.sokoban.model.gameobject.Player;
import timmax.tilegame.game.sokoban.model.gameobject.Wall;

import java.util.Set;

import static javafx.scene.paint.Color.*;

//  Warning:(9, 40) Raw use of parameterized class 'GameType'
public class GameTypeOfSokoban extends GameType {
    // ToDo: Ниже относится к визуализации. Удалить это отсюда.
    //       Константы, описанные ниже, относятся к визуализации.
    public static final Color WALL_CELL_COLOR = RED;
    public static final Color HOME_CELL_COLOR = WHITE;
    public static final Color EMPTY_CELL_COLOR = BLACK;

    public static final String PLAYER = "😀"; // "\uF9CD"; // "&";
    public static final Color PLAYER_TEXT_COLOR = GREEN;

    public static final String BOX = "█"; // "❐"; // "▉"; // "[]";
    public static final Color BOX_TEXT_COLOR = BLUE;

    public GameTypeOfSokoban() throws ClassNotFoundException, NoSuchMethodException {
        //  ToDo:   Warning:(30, 9) Unchecked call to 'GameType(String, int, Class<? extends IGameMatch>, Color, Color, String)' as a member of raw type 'timmax.tilegame.basemodel.protocol.server.GameType'
        super("Sokoban",
                1,
                //  ToDo:   Переписать GameMatchOfSokoban так, что-бы вместо
                //              class TileOfSokoban extends Tile
                //          использовались классы
                //              class Player extends MinesweeperGameObject,
                //              class Home extends MinesweeperGameObject,
                //              class Box extends MinesweeperGameObject,
                //              class Wall extends MinesweeperGameObject.
                //          04.09.2024 Создано три класса (Mine, Flag, OpenedTile), но они не задействованы в модели.
                //  ToDo:   Удалить класс Tile после решения предыдущего ToDo (и подобного в другой игре).
                //  ToDo:   Элементами Set должны быть только классы, являющиеся наследниками класса
                //          MinesweeperGameObject (который уже наследник OneTileGameObject).
                //          Сейчас это соответствие не отслеживается, например можно написать так:
                //              Set.of(Object.class),
                //          и компилятор ничего не скажет.

                Set.of(Player.class, Home.class, Box.class, Wall.class),
                GameMatchOfSokoban.class,
                BLACK, BLACK, ""
        );

        //  Это пример того, как хотелось-бы что-бы компилятор отреагировал в предыдущих строках
        //  (в этих примерах потом заменить Tile на SokobanGameObject):
        //      - компилятор возражает и это хорошо:
        //  Set<Class<? extends Tile>> abcClassSet1 = Set.of(Player.class, Home.class, Box.class, Object.class);
        //      - компилятор не возражает и это тоже хорошо:
        //  Set<Class<? extends Tile>> abcClassSet2 = Set.of(Player.class, Home.class, Box.class);
    }
}

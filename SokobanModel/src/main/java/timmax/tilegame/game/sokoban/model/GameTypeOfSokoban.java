package timmax.tilegame.game.sokoban.model;

import javafx.scene.paint.Color;
import timmax.tilegame.basemodel.protocol.server.GameType;

import static javafx.scene.paint.Color.*;

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
        super("Sokoban", /*1,*/ GameMatchOfSokoban.class, BLACK, BLACK, "");
    }
}

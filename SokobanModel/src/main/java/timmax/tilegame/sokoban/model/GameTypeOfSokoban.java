package timmax.tilegame.sokoban.model;

import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server_client.GuiDefaultConstants;

import static timmax.tilegame.sokoban.model.GameMatchOfSokoban.*;

public class GameTypeOfSokoban extends GameType {
    public GameTypeOfSokoban() throws ClassNotFoundException, NoSuchMethodException {
        super("Sokoban",
                1,
/*
                //  ToDo:   Элементами Set должны быть только классы, являющиеся наследниками класса
                //          SokobanGameObject (который уже наследник GameObject).
                //          Сейчас это соответствие не отслеживается, например можно написать так:
                //              Set.of(Object.class),
                //          и компилятор ничего не скажет.
                Set.of(SGOPlayer.class, SGOHome.class, SGOBox.class, SGOWall.class),
*/
                GameMatchOfSokoban.class,
                new GuiDefaultConstants(EMPTY_BACKGROUND_COLOR, NOBODY_TEXT_COLOR, NOBODY_TEXT)
        );

        //  Это пример того, как хотелось-бы что-бы компилятор отреагировал в предыдущих строках
        //  (в этих примерах потом заменить Tile на SokobanGameObject):
        //      - компилятор возражает и это хорошо:
        //  Set<Class<? extends Tile>> abcClassSet1 = Set.of(Player.class, Home.class, Box.class, Object.class);
        //      - компилятор не возражает и это тоже хорошо:
        //  Set<Class<? extends Tile>> abcClassSet2 = Set.of(Player.class, Home.class, Box.class);
    }
}

package timmax.sokoban.model.gameobject;

import java.util.Stack;

/**
 * Класс {@code Route} предназначен для работы с маршрутом игрока в игре Sokoban.
 *
 * @author  timmax
 * @since   ?
 */
public class Route {
    // Сейчас предусмотренно только сохранения перемещения игрока. И этого достаточно для сохранения маршрута для прокрутки вперёд.
    // Но при прокрутке назад не получится, т.к. не понятно был-ли во время хода сдвиг ящика. Достаточно сохранить true или false, о том был-ли сдвинут ящик!
    // private Stack< Direction> stackOfDirections = new Stack< >( );

    // Но для этого придётся ещё сделать класс с полями < Direction, Boolean>.
    // private Stack< Direction, Boolean> stackOfDirections2 = new Stack< >( );
    private final Stack< Step> stackOfSteps = new Stack< >( );

    /**
     * Дополняет маршрут новым шагом.
     */
    public void push( Step step) {
        stackOfSteps.push( step);
    }

    /**
     * Извлекает (и удаляет) из маршрута последний шаг.
     */
    public Step pop( ) {
        return stackOfSteps.pop();
    }

    /**
     * Размер маршрута.
     */
    public int size( ) {
        return stackOfSteps.size( );
    }
}
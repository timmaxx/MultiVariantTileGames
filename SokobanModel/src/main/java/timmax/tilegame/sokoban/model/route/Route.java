package timmax.tilegame.sokoban.model.route;

import java.util.Stack;

/**
 * Класс {@code Route} предназначен для работы с маршрутом игрока в игре Sokoban.
 *
 * @author timmax
 * @since ?
 */
public class Route {
    private final Stack<Step> stackOfSteps = new Stack<>();

/*
    */
/**
     * Дополняет маршрут новым шагом.
     *//*

    public void push(Step step) {
        stackOfSteps.push(step);
    }

    */
/**
     * Извлекает (и удаляет) из маршрута последний шаг.
     *//*

    public Step pop() {
        return stackOfSteps.pop();
    }
*/

    /**
     * Размер маршрута.
     */
    public int size() {
        return stackOfSteps.size();
    }
}

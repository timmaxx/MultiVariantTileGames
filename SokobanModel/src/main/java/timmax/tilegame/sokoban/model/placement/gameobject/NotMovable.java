package timmax.tilegame.sokoban.model.placement.gameobject;

import timmax.tilegame.sokoban.model.placement.primitives.SokobanXYOffset;

//  В интерфейсе Movable указан метод:
//      void move(SokobanXYOffset xyOffset);
//  А в интерфейсе NotMovable указан метод:
//      int move(SokobanXYOffset xyOffset);
//  Т.е. в этих интерфейсах указаны методы с одинаковыми именами и параметрами,
//  но отличающимися типами возвращаемого значения.
//  Получим, что нельзя определить класс, реализующий оба интерфейса одновременно (Movable и NotMovable).
//  И это уже будет выявлено на этапе компиляции.

//  С другой стороны, может и не нужна такая конструкция (одновременно не реализуемые интерфейсы).
//  Может быть будет достаточно обойтись тем, чтобы определить, что реализация реализует Movable, если-же не реализует,
//  то это уже что-то типа NotMovable

// For walls and homes.
public interface NotMovable {
    default int move(SokobanXYOffset xyOffset) {
        throw new RuntimeException("move() is not allowed for NotMovable!");
    }
}

package timmax.tilegame.sokoban.model.placement.gameobject;

import timmax.tilegame.basemodel.placement.gameobject.BaseGameObjectState;
import timmax.tilegame.basemodel.placement.gameobject.GameObjectStateAutomaton;

//  Базовое состояние объекта для Сокобан. От него нужно наследовать реальные состояния.
//  Для игровых объектов Сокобан не применяются состояния (каждый объект игры не меняет свою роль-состояние),
//  поэтому интерфейс не содержит методов.
public abstract class BaseSGOState extends BaseGameObjectState implements SGOState {
    public BaseSGOState(GameObjectStateAutomaton gameObjectStateAutomaton) {
        super(gameObjectStateAutomaton);
    }

    @Override
    public SGOStateAutomaton getGameObjectStateAutomaton() {
        return (SGOStateAutomaton) super.getGameObjectStateAutomaton();
    }
}

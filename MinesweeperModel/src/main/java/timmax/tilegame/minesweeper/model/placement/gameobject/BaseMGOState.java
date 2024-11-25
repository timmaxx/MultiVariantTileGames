package timmax.tilegame.minesweeper.model.placement.gameobject;

import timmax.tilegame.basemodel.placement.gameobject.BaseGameObjectState;

//  Базовое состояние объекта для Сапёра. От него нужно наследовать реальные состояния.
public abstract class BaseMGOState extends BaseGameObjectState implements MGOState {

    public BaseMGOState(MGOStateAutomaton MGOStateAutomaton) {
        super(MGOStateAutomaton);
    }

    @Override
    public MGOStateAutomaton getGameObjectStateAutomaton() {
        return (MGOStateAutomaton) super.getGameObjectStateAutomaton();
    }
}

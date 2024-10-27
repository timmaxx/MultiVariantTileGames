package timmax.tilegame.game.sokoban.model.gameobject;

import timmax.tilegame.basemodel.gameobject.GameObject;
import timmax.tilegame.basemodel.gameobject.AbstractGameObjectState;
import timmax.tilegame.basemodel.gameobject.GameObjectStateAutomaton;

public class SGOStateAutomaton extends GameObjectStateAutomaton implements SGOState {
    public SGOStateAutomaton(GameObject gameObject) {
        super(gameObject);
    }

    public SGOStateAutomaton(GameObject gameObject, AbstractGameObjectState abstractGameObjectState) {
        super(gameObject, abstractGameObjectState);
    }
}

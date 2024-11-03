package timmax.tilegame.sokoban.model.placement.gameobject;

import timmax.tilegame.basemodel.placement.gameobject.GameObject;
import timmax.tilegame.basemodel.placement.gameobject.GameObjectStateAutomaton;

public class SGOStateAutomaton extends GameObjectStateAutomaton implements SGOState {
    public SGOStateAutomaton(GameObject gameObject) {
        super(gameObject);
    }

/*
    public SGOStateAutomaton(GameObject gameObject, AbstractGameObjectState abstractGameObjectState) {
        super(gameObject, abstractGameObjectState);
    }
*/
}

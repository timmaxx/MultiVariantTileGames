package timmax.tilegame.game.sokoban.model.gameobject;

import timmax.tilegame.basemodel.gameobject.GameObject;
import timmax.tilegame.basemodel.gameobject.GameObjectState;
import timmax.tilegame.basemodel.gameobject.GameObjectStateAutomaton;

public class SGOStateAutomaton extends GameObjectStateAutomaton implements ISGOState{
    public SGOStateAutomaton(GameObject gameObject) {
        super(gameObject);
    }

    public SGOStateAutomaton(GameObject gameObject, GameObjectState gameObjectState) {
        super(gameObject, gameObjectState);
    }
}

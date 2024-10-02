package timmax.tilegame.basemodel.gameobject;

public abstract class OneTileGameObjectState implements IOneTileGameObjectState {
    private final OneTileGameObjectStateAutomaton oneTileGameObjectStateAutomaton;

    public OneTileGameObjectState(OneTileGameObjectStateAutomaton oneTileGameObjectStateAutomaton) {
        this.oneTileGameObjectStateAutomaton = oneTileGameObjectStateAutomaton;
    }

    public OneTileGameObjectStateAutomaton getOneTileGameObjectStateAutomaton() {
        return oneTileGameObjectStateAutomaton;
    }
}

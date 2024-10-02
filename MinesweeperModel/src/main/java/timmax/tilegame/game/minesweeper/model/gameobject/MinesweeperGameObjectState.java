package timmax.tilegame.game.minesweeper.model.gameobject;

import timmax.tilegame.basemodel.gameobject.OneTileGameObjectState;

public abstract class MinesweeperGameObjectState extends OneTileGameObjectState implements IMinesweeperGameObjectState {

    public MinesweeperGameObjectState(MinesweeperGameObjectStateAutomaton minesweeperGameObjectStateAutomaton) {
        super(minesweeperGameObjectStateAutomaton);
    }

    @Override
    public MinesweeperGameObjectStateAutomaton getOneTileGameObjectStateAutomaton() {
        return (MinesweeperGameObjectStateAutomaton) super.getOneTileGameObjectStateAutomaton();
    }
}

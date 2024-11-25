package timmax.tilegame.minesweeper.model.placement.gameobject;

public abstract class MGOSMine extends BaseMGOState {
    public MGOSMine(MGOStateAutomaton MGOStateAutomaton) {
        super(MGOStateAutomaton);
    }

    @Override
    public int getOneOrZeroMines() {
        return 1;
    }
}

package timmax.tilegame.minesweeper.model.placement.gameobject;

public abstract class MGOSNoMine extends AbstractMGOState {

    public MGOSNoMine(MGOStateAutomaton MGOStateAutomaton) {
        super(MGOStateAutomaton);
    }

    @Override
    public int getOneOrZeroMines() {
        return 0;
    }
}

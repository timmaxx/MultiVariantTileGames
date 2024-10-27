package timmax.tilegame.game.minesweeper.model.gameobject;

public abstract class MGOSNoMine extends AbstractMGOState {

    public MGOSNoMine(MGOStateAutomaton MGOStateAutomaton) {
        super(MGOStateAutomaton);
    }

    @Override
    public int getOneOrZeroMines() {
        return 0;
    }
}

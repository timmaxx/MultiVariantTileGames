package timmax.tilegame.game.minesweeper.model.gameobject;

public abstract class MGOSMine extends MGOState {
    public MGOSMine(MGOStateAutomaton MGOStateAutomaton) {
        super(MGOStateAutomaton);
    }

    @Override
    public int getOneOrZeroMines() {
        return 1;
    }
}

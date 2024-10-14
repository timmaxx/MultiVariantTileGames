package timmax.tilegame.game.minesweeper.model.gameobject;

public class MGOSNoMineIsOpened extends MGOSNoMine {
    public MGOSNoMineIsOpened(MGOStateAutomaton MGOStateAutomaton) {
        super(MGOStateAutomaton);
    }

    @Override
    public void open() {
        //  Ничего
    }

    @Override
    public void inverseFlag() {
        //  Ничего
    }
}

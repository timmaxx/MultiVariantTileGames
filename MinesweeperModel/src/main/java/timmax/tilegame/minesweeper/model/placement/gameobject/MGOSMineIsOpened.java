package timmax.tilegame.minesweeper.model.placement.gameobject;

public class MGOSMineIsOpened extends MGOSMine {
    public MGOSMineIsOpened(MGOStateAutomaton MGOStateAutomaton) {
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

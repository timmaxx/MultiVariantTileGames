package timmax.minesweeper.model;

import timmax.tilegame.basemodel.ConnectionToBaseModel;
import timmax.tilegame.basemodel.GameQueueForOneView;
import timmax.tilegame.baseview.ViewInterface;

public class ConnectionToMinesweeperModel implements ConnectionToBaseModel {
    // TransportModelFromModelToViews transportModelFromModelToViews
    MinesweeperModel minesweeperModel;

    public ConnectionToMinesweeperModel( MinesweeperModel minesweeperModel) {
        this.minesweeperModel = minesweeperModel;
    }

    @Override
    public GameQueueForOneView addView( ViewInterface viewInterface) {
        return null;
    }

    @Override
    public ConnectionToBaseModel createNewGame( ) {
        return null;
    }
}
package timmax.tilegame.basemodel.placement.placementstate;

import timmax.tilegame.basemodel.placement.gameobject.GameObjectStateAutomaton;
import timmax.tilegame.basemodel.placement.primitives.WidthHeightSizes;
import timmax.tilegame.basemodel.placement.gamemove.GameMove;
import timmax.tilegame.basemodel.placement.matchstatus.MatchStatus;
import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server_client.IGameMatchX;

import java.util.Set;

//  Базовое состояние Расстановки - родитель для других состояний Расстановки.
public abstract class GameObjectsPlacementAbstractState implements GameObjectsPlacementState {
    protected final GameObjectsPlacementStateAutomaton gameObjectsPlacementStateAutomaton;

    public GameObjectsPlacementAbstractState(GameObjectsPlacementStateAutomaton gameObjectsPlacementStateAutomaton) {
        this.gameObjectsPlacementStateAutomaton = gameObjectsPlacementStateAutomaton;
    }

    protected int getCountOfGamers() {
        return getGameType().getCountOfGamers();
    }

    protected GameType<IGameMatchX> getGameType() {
        return getGameObjectsPlacementBaseData().getGameType();
    }

    protected WidthHeightSizes getWidthHeightSizes() {
        return getGameObjectsPlacementBaseData().getWidthHeightSizes();
    }

    protected Set<GameObjectStateAutomaton> getGameObjectStateAutomatonSet() {
        return getGameObjectsPlacementBaseData().getGameObjectStateAutomatonSet();
    }

    private void throwWrongCallForCurrentState() {
        throw new RuntimeException("You cannot call this method in current state");
    }

    private GameObjectsPlacementCommon getGameObjectsPlacementBaseData() {
        return gameObjectsPlacementStateAutomaton.gameObjectsPlacementCommon;
    }


    @Override
    public void add(GameObjectStateAutomaton gameObjectStateAutomaton) {
        throwWrongCallForCurrentState();
    }

    @Override
    public void turnOnVerifable(int playerIndexOfCurrentMove) {
        throwWrongCallForCurrentState();
    }

    @Override
    public void turnOnVerifable(int playerIndexOfCurrentMove, WidthHeightSizes widthHeightSizes) {
        throwWrongCallForCurrentState();
    }

    @Override
    public MatchStatus makeGameMove(GameMove gameMove) {
        throwWrongCallForCurrentState();
        return null;
    }
}

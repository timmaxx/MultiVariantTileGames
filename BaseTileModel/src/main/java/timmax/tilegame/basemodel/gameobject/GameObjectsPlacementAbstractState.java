package timmax.tilegame.basemodel.gameobject;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

//  Базовое абстрактное состояние - родитель для других состояний Расстановки.
public abstract class GameObjectsPlacementAbstractState implements GameObjectsPlacementState {
    protected final GameObjectsPlacementStateAutomaton gameObjectsPlacementStateAutomaton;

    public GameObjectsPlacementAbstractState(GameObjectsPlacementStateAutomaton gameObjectsPlacementStateAutomaton) {
        this.gameObjectsPlacementStateAutomaton = gameObjectsPlacementStateAutomaton;
    }

    protected int getCountOfGamers() {
        return getGameType().getCountOfGamers();
    }

    protected GameType getGameType() {
        return getGameObjectsPlacementBaseData().getGameType();
    }
/*
    public final <GO extends GameObject> Set<GO> getGameObjectSetFilteredByGameObjectClass(
            Class<GO> gameObjectClass) {
        return getGameObjectStateAutomatonSet()
                .stream()
                .filter(gosa -> gosa.getGameObject().getClass().equals(gameObjectClass))
                //  Warning:(75, 30) Unchecked cast: 'timmax.tilegame.basemodel.gameobject.GameObject' to 'GO'
                .map(gosa -> (GO) (gosa.getGameObject()))
                .collect(Collectors.toSet());
    }
*/
    protected WidthHeightSizes getWidthHeightSizes() {
        return getGameObjectsPlacementBaseData().getWidthHeightSizes();
    }

    protected Set<GameObjectStateAutomaton> getGameObjectStateAutomatonSet() {
        return getGameObjectsPlacementBaseData().getGameObjectStateAutomatonSet();
    }

    private void throwWrongCallForCurrentState() {
        throw new RuntimeException("You cannot call this method in current state");
    }

    private GameObjectsPlacementBaseInstance getGameObjectsPlacementBaseData() {
        return gameObjectsPlacementStateAutomaton.gameObjectsPlacementBaseInstance;
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

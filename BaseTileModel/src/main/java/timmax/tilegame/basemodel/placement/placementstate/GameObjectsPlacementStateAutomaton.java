package timmax.tilegame.basemodel.placement.placementstate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timmax.tilegame.basemodel.placement.gameobject.GameObject;
import timmax.tilegame.basemodel.placement.gameobject.GameObjectStateAutomaton;
import timmax.tilegame.basemodel.placement.primitives.WidthHeightSizes;
import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;
import timmax.tilegame.basemodel.placement.gamemove.GameMove;
import timmax.tilegame.basemodel.placement.matchstatus.MatchStatus;
import timmax.tilegame.basemodel.protocol.server.GameMatch;

import java.util.Set;

//  Расстановка игровых объектов (реализован шаблон Состояние).
public class GameObjectsPlacementStateAutomaton implements GameObjectsPlacementState {
    protected static final Logger logger = LoggerFactory.getLogger(GameObjectsPlacementStateAutomaton.class);

    protected GameObjectsPlacementState gameObjectsPlacementStateCurrent;

    //  Экземпляр (с данными и методами), доступный в состоянии "Не целостное":
    private final GameObjectsPlacementNotVerifiedState notVerified;
    //  Экземпляр (с данными и методами), доступный в состоянии "Целостное":
    private final GameObjectsPlacementVerifiedState verified;


    //  Экземпляр (с данными и методами), доступный во всех состояниях:
    protected final GameObjectsPlacementBaseInstance gameObjectsPlacementBaseInstance;


    public GameObjectsPlacementStateAutomaton(GameMatch gameMatch) {
        gameObjectsPlacementBaseInstance = new GameObjectsPlacementBaseInstance(gameMatch);
        notVerified = new GameObjectsPlacementNotVerifiedState(this);
        verified = new GameObjectsPlacementVerifiedState(this);
        setCurrentState(notVerified);
    }

    public WidthHeightSizes getWidthHeightSizes() {
        return gameObjectsPlacementBaseInstance.getWidthHeightSizes();
    }
    private void setCurrentState(GameObjectsPlacementState gameObjectsPlacementStateCurrent) {
        this.gameObjectsPlacementStateCurrent = gameObjectsPlacementStateCurrent;
    }

    protected void setCurrentStateVerified() {
        this.gameObjectsPlacementStateCurrent = verified;
    }

    public final Set<GameObjectStateAutomaton> getGameObjectStateAutomatonSetFilteredByXYCoordinate(XYCoordinate xyCoordinate) {
        return gameObjectsPlacementBaseInstance.getGameObjectStateAutomatonSetFilteredByXYCoordinate(xyCoordinate);
    }

    public Set<GameObject> getGameObjectSetFilteredByXYCoordinate(XYCoordinate xyCoordinate) {
        return gameObjectsPlacementBaseInstance.getGameObjectSetFilteredByXYCoordinate(xyCoordinate);
    }

    public GameMatch getGameMatch() {
        return gameObjectsPlacementBaseInstance.getGameMatch();
    }

    public Set<GameObject> getGameObjectSetFilteredByGameObjectClass(Class<? extends GameObject> gameObjectClass) {
        return gameObjectsPlacementBaseInstance.getGameObjectSetFilteredByGameObjectClass(gameObjectClass);
    }

    public MatchStatus getMatchStatus() {
        return verified.getMatchStatus();
    }

    //  Добавляется игровой объект в расстановку
    @Override
    public void add(GameObjectStateAutomaton gameObjectStateAutomaton) {
        gameObjectsPlacementStateCurrent.add(gameObjectStateAutomaton);
    }

    //  Сделать расстановку верифицируемой.
    //  Такой вызов предполагает, что после него:
    //  - нельзя просто добавлять объект (т.е. вне хода),
    //  - можно и нужно проверять расстановку на целостность.
    @Override
    public void turnOnVerifable(int playerIndexOfCurrentMove) {
        gameObjectsPlacementStateCurrent.turnOnVerifable(playerIndexOfCurrentMove);
    }

    //  Сделать расстановку не редактируемой с введением альтернативной ширины и высоты поля
    //  (ширина и/или высота должны быть не меньше, чем уже получилось при расстановке).
    @Override
    public void turnOnVerifable(int playerIndexOfCurrentMove, WidthHeightSizes widthHeightSizes) {
        gameObjectsPlacementStateCurrent.turnOnVerifable(playerIndexOfCurrentMove, widthHeightSizes);
    }

    @Override
    public MatchStatus makeGameMove(GameMove gameMove) {
        return gameObjectsPlacementStateCurrent.makeGameMove(gameMove);
    }
}

package timmax.commons.state;

import java.util.HashSet;
import java.util.Set;

import timmax.commons.classes.Classes;

public abstract class AState implements State {
    private final StateContext stateContext;
    private final Class<? extends StateData> stateDataClass;
    protected final Set<PairDestStateAndCanSwitchWithoutParams> setOfPairDestStateAndCanSwitchWithoutParams;

    protected StateData stateData;

    public AState(StateContext stateContext, Class<? extends StateData> stateDataClass) {
        this.stateContext = stateContext;
        this.stateDataClass = stateDataClass;
        this.setOfPairDestStateAndCanSwitchWithoutParams = new HashSet<>();
    }

    public void checkPosibleToChangeState(State state, boolean isThereStateData) {
        for (PairDestStateAndCanSwitchWithoutParams pairDestStateAndCanSwitchWithoutParams : setOfPairDestStateAndCanSwitchWithoutParams) {
            if (Classes.isInstanceOf(state, pairDestStateAndCanSwitchWithoutParams.destinationStateClass())
                    && !isThereStateData
                    && pairDestStateAndCanSwitchWithoutParams.canSwitchWithoutParams()) {
                return;
            }
        }
        throw new RuntimeException("You cannot change state from '" + this + "' to '" + state + "'!");
    }

    private void setData(StateData stateData) {
        if (stateData == null) {
            throw new RuntimeException("Data for state '" + this + "' is null!");
        }
        if (!Classes.isInstanceOf(stateData, stateDataClass)) {
            throw new RuntimeException("Data for state '" + this + "' is wrong type!");
        }
        this.stateData = stateData;
    }

    private void setAsCurrent() {
        stateContext.currentState = this;
    }

    public StateContext getStateContext() {
        return stateContext;
    }

    @Override
    public void changeState(AState aState) {
        checkPosibleToChangeState(aState, false);
        aState.setAsCurrent();
    }

    @Override
    public void changeState(AState aState, StateData stateData) {
        checkPosibleToChangeState(aState, true);
        aState.setData(stateData);
        aState.setAsCurrent();
    }

    @Override
    public StateData getData() {
        return stateData;
    }
}

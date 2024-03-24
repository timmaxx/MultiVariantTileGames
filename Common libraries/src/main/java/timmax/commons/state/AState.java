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

    public void checkPosibleToChangeState(AState aState) {
        for (PairDestStateAndCanSwitchWithoutParams pairDestStateAndCanSwitchWithoutParams : setOfPairDestStateAndCanSwitchWithoutParams) {
            if (Classes.isInstanceOf(aState, pairDestStateAndCanSwitchWithoutParams.destinationStateClass())
                    && pairDestStateAndCanSwitchWithoutParams.canSwitchWithoutParams()
            ) {
                return;
            }
        }
        throw new RuntimeException("You cannot change state from '" + this + "' to '" + aState + "' without papameters!");
    }

    public void checkPosibleToChangeState(AState aState, StateData stateData) {
        for (PairDestStateAndCanSwitchWithoutParams pairDestStateAndCanSwitchWithoutParams : setOfPairDestStateAndCanSwitchWithoutParams) {
            if (Classes.isInstanceOf(aState, pairDestStateAndCanSwitchWithoutParams.destinationStateClass())
                // && !pairDestStateAndCanSwitchWithoutParams.getCanSwitchWithoutParams()
            ) {
                return;
            }
        }
        throw new RuntimeException("You cannot change state from '" + this + "' to '" + aState + "' with or without any papameters!");
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
        stateContext.currentClientState = this;
    }

    @Override
    public void changeState(AState aState) {
        checkPosibleToChangeState(aState);
        aState.setAsCurrent();
    }

    @Override
    public void changeState(AState aState, StateData stateData) {
        checkPosibleToChangeState(aState, stateData);
        aState.setData(stateData);
        aState.setAsCurrent();
    }

    @Override
    public StateData getData() {
        return stateData;
    }
}

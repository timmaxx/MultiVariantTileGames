package timmax.state;

import java.util.HashSet;
import java.util.Set;

import timmax.classes.Classes;

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
        for (PairDestStateAndCanSwitchWithoutParams pairDestStateAndCanSwitchWithoutParams : setOfPairDestStateAndCanSwitchWithoutParams) {
            if (Classes.isInstanceOf(aState, pairDestStateAndCanSwitchWithoutParams.destinationStateClass())
                    && pairDestStateAndCanSwitchWithoutParams.canSwitchWithoutParams()
            ) {
                aState.setAsCurrent();
                return;
            }
        }
        throw new RuntimeException("You cannot change state from '" + this + "' to '" + aState + "' without papameters!");
    }

    @Override
    public void changeState(AState aState, StateData stateData) {
        for (PairDestStateAndCanSwitchWithoutParams pairDestStateAndCanSwitchWithoutParams : setOfPairDestStateAndCanSwitchWithoutParams) {
            if (Classes.isInstanceOf(aState, pairDestStateAndCanSwitchWithoutParams.destinationStateClass())
                // && !pairDestStateAndCanSwitchWithoutParams.getCanSwitchWithoutParams()
            ) {
                aState.setData(stateData);
                aState.setAsCurrent();
                return;
            }
        }
        throw new RuntimeException("You cannot change state from '" + this + "' to '" + aState + "' with or without any papameters!");
    }

    @Override
    public StateData getData() {
        return stateData;
    }
}

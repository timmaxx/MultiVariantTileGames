package timmax.state;

import java.util.HashSet;
import java.util.Set;

import timmax.classes.Classes;

public abstract class AState implements State {
    private final StateContext stateContext;
    private final Class<? extends StateData> stateDataClass;
    protected final Set<PairDestinationStateAndCanSwitchWithoutParams> setOfPairDestinationStateAndCanSwitchWithoutParams;

    protected StateData stateData;

    public AState(StateContext stateContext, Class<? extends StateData> stateDataClass) {
        this.stateContext = stateContext;
        this.stateDataClass = stateDataClass;
        this.setOfPairDestinationStateAndCanSwitchWithoutParams = new HashSet<>();
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
        for (PairDestinationStateAndCanSwitchWithoutParams pairDestinationStateAndCanSwitchWithoutParams : setOfPairDestinationStateAndCanSwitchWithoutParams) {
            if (Classes.isInstanceOf(aState, pairDestinationStateAndCanSwitchWithoutParams.getDestinationStateClass())
                    && pairDestinationStateAndCanSwitchWithoutParams.getCanSwitchWithoutParams()
            ) {
                aState.setAsCurrent();
                return;
            }
        }
        throw new RuntimeException("You cannot change state from '" + this + "' to '" + aState + "' without papameters!");
    }

    @Override
    public void changeState(AState aState, StateData stateData) {
        for (PairDestinationStateAndCanSwitchWithoutParams pairDestinationStateAndCanSwitchWithoutParams : setOfPairDestinationStateAndCanSwitchWithoutParams) {
            if (Classes.isInstanceOf(aState, pairDestinationStateAndCanSwitchWithoutParams.getDestinationStateClass())
                // && !pairDestinationStateAndCanSwitchWithoutParams.getCanSwitchWithoutParams()
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


    private static class PairDestinationStateAndCanSwitchWithoutParams {
        private final Class<? extends AState> destinationStateClass;
        private final Boolean canSwitchWithoutParams;

        public PairDestinationStateAndCanSwitchWithoutParams(Class<? extends AState> destinationStateClass, Boolean canSwitchWithoutParams) {
            this.destinationStateClass = destinationStateClass;
            this.canSwitchWithoutParams = canSwitchWithoutParams;
        }

        public Class<? extends AState> getDestinationStateClass() {
            return destinationStateClass;
        }

        public Boolean getCanSwitchWithoutParams() {
            return canSwitchWithoutParams;
        }
    }
}

package timmax.commons.state;

public record PairDestStateAndCanSwitchWithoutParams(
        Class<? extends AState> destinationStateClass,
        Boolean canSwitchWithoutParams) {
}

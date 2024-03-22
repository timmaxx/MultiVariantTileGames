package timmax.state;

public record PairDestinationStateAndCanSwitchWithoutParams(
        Class<? extends AState> destinationStateClass,
        Boolean canSwitchWithoutParams) {
}

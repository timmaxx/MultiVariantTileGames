package timmax.tilegame.basemodel.protocol.server_client.state;

import timmax.commons.state.StateData;

public class StateDataOfCS08GameIsPlaying implements StateData {
    private final Boolean gameIsPlaying; // ---- 8 (Партия была начата)

    public StateDataOfCS08GameIsPlaying(Boolean gameIsPlaying) {
        this.gameIsPlaying = gameIsPlaying;
    }

    public Boolean getGameIsPlaying() {
        return gameIsPlaying;
    }
}

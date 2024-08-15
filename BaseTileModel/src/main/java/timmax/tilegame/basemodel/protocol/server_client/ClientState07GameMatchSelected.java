package timmax.tilegame.basemodel.protocol.server_client;

import java.util.Map;

public abstract class ClientState07GameMatchSelected<GameMatchX extends IGameMatchX> extends ClientState06GameMatchSetSelected<GameMatchX> {
    public ClientState07GameMatchSelected(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState07GameMatchSelected
    @Override
    public void reselectGameMatch() {
        GameMatchX gameMatchX = getClientStateAutomaton().getGameMatchX_();
        setGameMatchX(gameMatchX);
    }

    @Override
    public GameMatchX getGameMatchX() {
        return getClientStateAutomaton().getGameMatchX_();
    }

    @Override
    public void startGameMatch(Map<String, Integer> paramsOfModelValueMap) {
        getClientStateAutomaton().startGameMatch_(paramsOfModelValueMap);
    }

    @Override
    public void resumeGameMatch() {
        getClientStateAutomaton().resumeGameMatch_();
    }

    @Override
    public Boolean getGameMatchIsPlaying() {
        return getClientStateAutomaton().getGameMatchIsPlaying_();
    }
}

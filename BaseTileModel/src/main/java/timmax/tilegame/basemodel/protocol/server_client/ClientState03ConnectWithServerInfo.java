package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.dto.BaseDtoId;
import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

public abstract class ClientState03ConnectWithServerInfo extends ClientState02ConnectWithoutServerInfo {
    public ClientState03ConnectWithServerInfo(ClientStateAutomaton clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState02ConnectNonIdent
    @Override
    public Set<GameType> getGameTypeSet() {
        return getBaseStateAutomaton().getGameTypeSet_();
    }

    @Override
    public void authorizeUser(BaseDtoId userDtoId) {
        getBaseStateAutomaton().authorizeUser_(userDtoId);
    }
}

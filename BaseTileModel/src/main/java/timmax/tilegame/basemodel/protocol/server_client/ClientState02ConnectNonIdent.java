package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

public abstract class ClientState02ConnectNonIdent<GameMatchX extends IGameMatchX> extends ClientState01NoConnect<GameMatchX> {
    public ClientState02ConnectNonIdent(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState02ConnectNonIdent
    @Override
    public void authorizeUser(String userName, Set<GameType> gameTypeSet) {
        getClientStateAutomaton().authorizeUser_(userName, gameTypeSet);
    }

    // Метод переопределён чтобы вызов changeStateTo02ConnectNonIdent не приводил к исключению
    // (как это определено в классе AbstractClientState)
    @Override
    public void closeConnect() {
        getClientStateAutomaton().setCurrentState(getClientStateAutomaton().clientState01NoConnect);
    }
}

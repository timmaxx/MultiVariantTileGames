package timmax.tilegame.server.websocket;

import timmax.tilegame.basemodel.protocol.server.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

public class FabricOfRemoteClientStates<ClientId> implements IFabricOfRemoteClientStates<ClientId> {
    // ToDo: Сейчас есть параметр
    //       ClientStateAutomaton<IGameMatch> clientStateAutomaton
    //       но когда пришлось добавлять параметр clientId, то я сделал это ч/з конструктор - и сигнатуру методов
    //       интерфейса это не поменяло.
    //       Значит и первый параметр можно убрать в конструктор!
    //  Такой вариант рассматривался, но он вёл к тому, что приходилось создавать IFabricOfClientStates с пустой
    //  ClientStateAutomaton, потом создавать ClientStateAutomaton, которому в конструкторе передавал IFabricOfClientStates,
    //  и потом для IFabricOfClientStates вызывать setClientStateAutomaton.
    //  Классы получались взаимозависимы и поэтому такой приём не привёл к хорошему варианту.

    @Override
    public RemoteClientState01NoConnect getClientState01NoConnect(ClientStateAutomaton<IGameMatch> clientStateAutomaton) {
        return new RemoteClientState01NoConnect(clientStateAutomaton);
    }

    @Override
    public RemoteClientState02ConnectNonIdent<ClientId> getClientState02ConnectNonIdent(ClientStateAutomaton<IGameMatch> clientStateAutomaton) {
        return new RemoteClientState02ConnectNonIdent<>(clientStateAutomaton);
    }

    @Override
    public RemoteClientState04GameTypeSetSelected<ClientId> getClientState04GameTypeSetSelected(ClientStateAutomaton<IGameMatch> clientStateAutomaton) {
        return new RemoteClientState04GameTypeSetSelected<>(clientStateAutomaton);
    }

    @Override
    public RemoteClientState06GameMatchSetSelected<ClientId> getClientState06GameMatchSetSelected(ClientStateAutomaton<IGameMatch> clientStateAutomaton) {
        return new RemoteClientState06GameMatchSetSelected<>(clientStateAutomaton);
    }

    @Override
    public RemoteClientState07GameMatchSelected<ClientId> getClientState07GameMatchSelected(ClientStateAutomaton<IGameMatch> clientStateAutomaton) {
        return new RemoteClientState07GameMatchSelected<>(clientStateAutomaton);
    }

    @Override
    public RemoteClientState08GameMatchIsPlaying<ClientId> getClientState08GameMatchIsPlaying(ClientStateAutomaton<IGameMatch> clientStateAutomaton) {
        return new RemoteClientState08GameMatchIsPlaying<>(clientStateAutomaton);
    }
}

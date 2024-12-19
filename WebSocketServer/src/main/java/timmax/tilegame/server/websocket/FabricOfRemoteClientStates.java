package timmax.tilegame.server.websocket;

import timmax.tilegame.basemodel.protocol.server.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

public class FabricOfRemoteClientStates implements IFabricOfRemoteClientStates {
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
    public RemoteClientState02ConnectWithoutServerInfo getClientState02ConnectWithoutServerInfo(ClientStateAutomaton<IGameMatch> clientStateAutomaton) {
        return new RemoteClientState02ConnectWithoutServerInfo(clientStateAutomaton);
    }

    @Override
    public RemoteClientState03ConnectWithServerInfo getClientState03ConnectWithServerInfo(ClientStateAutomaton<IGameMatch> clientStateAutomaton) {
        return new RemoteClientState03ConnectWithServerInfo(clientStateAutomaton);
    }

    @Override
    public RemoteClientState04UserWasAuthorized getClientState04UserWasAuthorized(ClientStateAutomaton<IGameMatch> clientStateAutomaton) {
        return new RemoteClientState04UserWasAuthorized(clientStateAutomaton);
    }

    @Override
    public RemoteClientState06GameTypeWasSet getClientState06GameTypeWasSet(ClientStateAutomaton<IGameMatch> clientStateAutomaton) {
        return new RemoteClientState06GameTypeWasSet(clientStateAutomaton);
    }

    @Override
    public RemoteClientState07GameMatchWasSet getClientState07GameMatchWasSet(ClientStateAutomaton<IGameMatch> clientStateAutomaton) {
        return new RemoteClientState07GameMatchWasSet(clientStateAutomaton);
    }

    @Override
    public RemoteClientState08GameMatchIsPlaying getClientState08GameMatchIsPlaying(ClientStateAutomaton<IGameMatch> clientStateAutomaton) {
        return new RemoteClientState08GameMatchIsPlaying(clientStateAutomaton);
    }
}

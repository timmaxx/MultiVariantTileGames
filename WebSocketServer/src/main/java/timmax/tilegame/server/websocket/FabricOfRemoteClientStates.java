package timmax.tilegame.server.websocket;

import timmax.tilegame.basemodel.protocol.server.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

public class FabricOfRemoteClientStates<ClientId> implements IFabricOfRemoteClientStates<ClientId> {
    private final ClientId clientId;

    public FabricOfRemoteClientStates(ClientId clientId) {
        this.clientId = clientId;
    }

    // ToDo: Сейчас есть параметр
    //       ClientStateAutomaton<IModelOfServer> clientStateAutomaton
    //       но когда пришлось добавлять параметр clientId, то я сделал это ч/з конструктор - и сигнатуру методов
    //       интерфейса это не поменяло.
    //       Значит и первый параметр можно убрать в конструктор!
    //  Такой вариант рассматривался, но он вёл к тому, что приходилось создавать IFabricOfClientStates с пустой
    //  ClientStateAutomaton, потом создавать ClientStateAutomaton, которому в конструкторе передавал IFabricOfClientStates,
    //  и потом для IFabricOfClientStates вызывать setClientStateAutomaton.
    //  Классы получались взаимозависимы и поэтому такой приём не привёл к хорошему варианту.

    @Override
    public RemoteClientState01NoConnect<ClientId> getClientState01NoConnect(ClientStateAutomaton<IModelOfServer> clientStateAutomaton) {
        return new RemoteClientState01NoConnect<>(clientStateAutomaton, clientId);
    }

    @Override
    public RemoteClientState02ConnectNonIdent<ClientId> getClientState02ConnectNonIdent(ClientStateAutomaton<IModelOfServer> clientStateAutomaton) {
        return new RemoteClientState02ConnectNonIdent<>(clientStateAutomaton, clientId);
    }

    @Override
    public RemoteClientState03ConnectAuthorized<ClientId> getClientState03ConnectAuthorized(ClientStateAutomaton<IModelOfServer> clientStateAutomaton) {
        return new RemoteClientState03ConnectAuthorized<>(clientStateAutomaton, clientId);
    }

    @Override
    public RemoteClientState04GameTypeSetSelected<ClientId> getClientState04GameTypeSetSelected(ClientStateAutomaton<IModelOfServer> clientStateAutomaton) {
        return new RemoteClientState04GameTypeSetSelected<>(clientStateAutomaton, clientId);
    }

    @Override
    public RemoteClientState05GameTypeSelected<ClientId> getClientState05GameTypeSelected(ClientStateAutomaton<IModelOfServer> clientStateAutomaton) {
        return new RemoteClientState05GameTypeSelected<>(clientStateAutomaton, clientId);
    }

    @Override
    public RemoteClientState06GameMatchSetSelected<ClientId> getClientState06GameMatchSetSelected(ClientStateAutomaton<IModelOfServer> clientStateAutomaton) {
        return new RemoteClientState06GameMatchSetSelected<>(clientStateAutomaton, clientId);
    }

    @Override
    public RemoteClientState07GameMatchSelected<ClientId> getClientState07GameMatchSelected(ClientStateAutomaton<IModelOfServer> clientStateAutomaton) {
        return new RemoteClientState07GameMatchSelected<>(clientStateAutomaton, clientId);
    }

    @Override
    public RemoteClientState08GameMatchPlaying<ClientId> getClientState08GameMatchPlaying(ClientStateAutomaton<IModelOfServer> clientStateAutomaton) {
        return new RemoteClientState08GameMatchPlaying<>(clientStateAutomaton, clientId);
    }
}

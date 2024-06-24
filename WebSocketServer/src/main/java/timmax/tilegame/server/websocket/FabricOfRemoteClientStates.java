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
    public RemoteClientState08GameIsPlaying<ClientId> getClientState08GameIsPlaying(ClientStateAutomaton<IModelOfServer> clientStateAutomaton) {
        return new RemoteClientState08GameIsPlaying<>(clientStateAutomaton, clientId);
    }
}

//  См. коммент к IFabricOfClientStates
/*
public class FabricOfRemoteClientStates<ClientId> implements IFabricOfRemoteClientStates<ClientId> {
    private ClientStateAutomaton<IModelOfServer> clientStateAutomaton;
    private final ClientId clientId;

    public FabricOfRemoteClientStates(ClientId clientId) {
        this.clientId = clientId;
    }

    // ToDo: Сейчас есть параметр
    //       ClientStateAutomaton<IModelOfServer> clientStateAutomaton
    //       но когда пришлось добавлять параметр clientId, то я сделал это ч/з конструктор - и сигнатуру методов
    //       интерфейса это не поменяло.
    //       Значит и первый параметр можно убрать в конструктор!
    @Override
    public RemoteClientState01NoConnect<ClientId> getClientState01NoConnect() {
        return new RemoteClientState01NoConnect<>(clientStateAutomaton, clientId);
    }

    @Override
    public RemoteClientState02ConnectNonIdent<ClientId> getClientState02ConnectNonIdent() {
        return new RemoteClientState02ConnectNonIdent<>(clientStateAutomaton, clientId);
    }

    @Override
    public RemoteClientState03ConnectAuthorized<ClientId> getClientState03ConnectAuthorized() {
        return new RemoteClientState03ConnectAuthorized<>(clientStateAutomaton, clientId);
    }

    @Override
    public RemoteClientState04GameTypeSetSelected<ClientId> getClientState04GameTypeSetSelected() {
        return new RemoteClientState04GameTypeSetSelected<>(clientStateAutomaton, clientId);
    }

    @Override
    public RemoteClientState05GameTypeSelected<ClientId> getClientState05GameTypeSelected() {
        return new RemoteClientState05GameTypeSelected<>(clientStateAutomaton, clientId);
    }

    @Override
    public RemoteClientState06GameMatchSetSelected<ClientId> getClientState06GameMatchSetSelected() {
        return new RemoteClientState06GameMatchSetSelected<>(clientStateAutomaton, clientId);
    }

    @Override
    public RemoteClientState07GameMatchSelected<ClientId> getClientState07GameMatchSelected() {
        return new RemoteClientState07GameMatchSelected<>(clientStateAutomaton, clientId);
    }

    @Override
    public RemoteClientState08GameIsPlaying<ClientId> getClientState08GameIsPlaying() {
        return new RemoteClientState08GameIsPlaying<>(clientStateAutomaton, clientId);
    }

    @Override
    public void setClientStateAutomaton(ClientStateAutomaton ClientState) {
        this.clientStateAutomaton = ClientState;
    }
}
*/

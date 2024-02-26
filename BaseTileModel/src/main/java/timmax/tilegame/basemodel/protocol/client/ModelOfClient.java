package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.transport.TransportOfClient;

public class ModelOfClient implements IModelOfClient {
    private final TransportOfClient transportOfClient;
    private final LocalClientState localClientState;

    public ModelOfClient(TransportOfClient transportOfClient, LocalClientState localClientState) {
        this.transportOfClient = transportOfClient;
        this.localClientState = localClientState;
    }

    // Overriden methods from interface IModelOfClient:
    // 2
    @Override
    public void logout() {
        System.out.println("logout()");
        transportOfClient.sendEventOfClient(new EventOfClient20Logout());
    }

    @Override
    public void login(String userName, String password) {
        System.out.println("login(String, String)");
        transportOfClient.sendEventOfClient(new EventOfClient21Login(userName, password));
    }

    // 3
    @Override
    public void forgetGameTypeSet() {
        System.out.println("forgetGameTypeSet()");
        transportOfClient.sendEventOfClient(new EventOfClient30ForgetGameTypeSet());
    }

    @Override
    public void getGameTypeSet() {
        System.out.println("getGameTypeSet()");
        transportOfClient.sendEventOfClient(new EventOfClient31GiveGameTypeSet());
    }

    // 4
    @Override
    public void forgetGameType() {
        System.out.println("forgetGameType()");
        transportOfClient.sendEventOfClient(new EventOfClient40ForgetGameType());
    }

    @Override
    public void gameTypeSelect(ModelOfServerDescriptor modelOfServerDescriptor) {
        System.out.println("gameTypeSelect(String)");
        transportOfClient.sendEventOfClient(new EventOfClient41SetGameType(modelOfServerDescriptor));
    }

    // 5
    @Override
    public void forgetGameMatchSet() {
        System.out.println("forgetGameMatchSet()");
        transportOfClient.sendEventOfClient(new EventOfClient50ForgetGameMatchSet());
    }

    @Override
    public void getGameMatchSet() {
        System.out.println("getGameMatchSet()");
        transportOfClient.sendEventOfClient(new EventOfClient51GiveGameMatchSet());
    }

    // 6
    @Override
    public void forgetGameMatch() {
        System.out.println("forgetGameMatch()");
        transportOfClient.sendEventOfClient(new EventOfClient60ForgetGameMatch());
    }

    @Override
    public void gameMatchSelect(InstanceIdOfModel model) {
        System.out.println("gameMatchSelect(InstanceIdOfModel model)");
        transportOfClient.sendEventOfClient(new EventOfClient61SetGameMatch(model));
    }

    // 7
    @Override
    public void stopGameMatchPlaying() {
        System.out.println("stopPlaying()");
        transportOfClient.sendEventOfClient(new EventOfClient70StopGameMatchPlaying());
    }

    @Override
    public void startGameMatchPlaying() {
        System.out.println("startPlaying()");
        transportOfClient.sendEventOfClient(new EventOfClient71StartGameMatchPlaying());
    }

    // ---- X
    @Override
    public MainGameClientStatus getMainGameClientStatus() {
        if (!transportOfClient.isOpen() || transportOfClient.isClosed()) {
            return MainGameClientStatus.NO_CONNECT;
        }
        if (transportOfClient.isOpen()) {
            return localClientState.getMainGameClientStatus();
        }
        throw new RuntimeException("Unknown state.");
    }

    @Override
    public LocalClientState getLocalClientState() {
        return localClientState;
    }
}

package timmax.tilegame.basemodel.protocol.client;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.transport.TransportOfClient;

public class ModelOfClient<Model, ClientId> implements IModelOfClient<Model, ClientId> {
    private static final Logger logger = LoggerFactory.getLogger(ModelOfClient.class);

    private final TransportOfClient transportOfClient;
    private final ClientStateAutomaton<Model, ClientId> localClientState;

    public ModelOfClient(TransportOfClient transportOfClient, ClientStateAutomaton<Model, ClientId> localClientState) {
        this.transportOfClient = transportOfClient;
        this.localClientState = localClientState;
    }

    // Overriden methods from interface IModelOfClient:
    // 2
    @Override
    public void logout() {
        logger.debug("logout()");
        transportOfClient.sendEventOfClient(new EventOfClient20Logout());
    }

    @Override
    public void login(String userName, String password) {
        logger.debug("login(String, String)");
        transportOfClient.sendEventOfClient(new EventOfClient21Login(userName, password));
    }

    // 3
    @Override
    public void forgetGameTypeSet() {
        logger.debug("forgetGameTypeSet()");
        transportOfClient.sendEventOfClient(new EventOfClient30ForgetGameTypeSet());
    }

    @Override
    public void getGameTypeSet() {
        logger.debug("getGameTypeSet()");
        transportOfClient.sendEventOfClient(new EventOfClient31GiveGameTypeSet());
    }

    // 4
    @Override
    public void forgetGameType() {
        logger.debug("forgetGameType()");
        transportOfClient.sendEventOfClient(new EventOfClient40ForgetGameType());
    }

    @Override
    public void gameTypeSelect(ModelOfServerDescriptor modelOfServerDescriptor) {
        logger.debug("gameTypeSelect(String)");
        transportOfClient.sendEventOfClient(new EventOfClient41SetGameType(modelOfServerDescriptor.getGameName()));
    }

    // 5
    @Override
    public void forgetGameMatchSet() {
        logger.debug("forgetGameMatchSet()");
        transportOfClient.sendEventOfClient(new EventOfClient50ForgetGameMatchSet());
    }

    @Override
    public void getGameMatchSet() {
        logger.debug("getGameMatchSet()");
        transportOfClient.sendEventOfClient(new EventOfClient51GiveGameMatchSet());
    }

    // 6
    @Override
    public void forgetGameMatch() {
        logger.debug("forgetGameMatch()");
        transportOfClient.sendEventOfClient(new EventOfClient60ForgetGameMatch());
    }

    @Override
    public void gameMatchSelect(InstanceIdOfModel model) {
        logger.debug("gameMatchSelect(InstanceIdOfModel model)");
        transportOfClient.sendEventOfClient(new EventOfClient61SetGameMatch(model));
    }

    // 7
    @Override
    public void stopGameMatchPlaying() {
        logger.debug("stopPlaying()");
        transportOfClient.sendEventOfClient(new EventOfClient70StopGameMatchPlaying());
    }

    @Override
    public void startGameMatchPlaying(Map<String, Integer> mapOfParamsOfModelValue) {
        logger.debug("startPlaying()");
        transportOfClient.sendEventOfClient(new EventOfClient71StartGameMatchPlaying(mapOfParamsOfModelValue));
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
        logger.error("Unknown state.");
        throw new RuntimeException("Unknown state.");
    }

    @Override
    public ClientStateAutomaton<Model, ClientId> getLocalClientState() {
        return localClientState;
    }
}

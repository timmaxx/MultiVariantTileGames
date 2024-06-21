package timmax.tilegame.basemodel.protocol.client;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.transport.TransportOfClient;

public class ModelOfClient<ClientId> implements IModelOfClient<IModelOfClient> {
    private static final Logger logger = LoggerFactory.getLogger(ModelOfClient.class);

    private final TransportOfClient<ClientId> transportOfClient;
    private final LocalClientStateAutomaton<IModelOfClient> localClientState;

    public ModelOfClient(TransportOfClient<ClientId> transportOfClient, LocalClientStateAutomaton<IModelOfClient> localClientState) {
        this.transportOfClient = transportOfClient;
        this.localClientState = localClientState;
    }

    // interface IModelOfClient:
    // 2
    @Override
    public void login(String userName, String password) {
        logger.debug("login(String, String)");
        transportOfClient.sendEventOfClient(new EventOfClient21Login<>(userName, password));
    }

    // 3
    @Override
    public void logout() {
        logger.debug("logout()");
        transportOfClient.sendEventOfClient(new EventOfClient20Logout<>());
    }

    @Override
    public void getGameTypeSet() {
        logger.debug("getGameTypeSet()");
        transportOfClient.sendEventOfClient(new EventOfClient31GiveGameTypeSet<>());
    }

    // 4
    @Override
    public void forgetGameTypeSet() {
        logger.debug("forgetGameTypeSet()");
        transportOfClient.sendEventOfClient(new EventOfClient30ForgetGameTypeSet<>());
    }

    @Override
    public void gameTypeSelect(ModelOfServerDescriptor modelOfServerDescriptor) {
        logger.debug("gameTypeSelect(String)");
        transportOfClient.sendEventOfClient(new EventOfClient41SetGameType<>(modelOfServerDescriptor.getGameName()));
    }

    // 5
    @Override
    public void forgetGameType() {
        logger.debug("forgetGameType()");
        transportOfClient.sendEventOfClient(new EventOfClient40ForgetGameType<>());
    }

    @Override
    public void getGameMatchSet() {
        logger.debug("getGameMatchSet()");
        transportOfClient.sendEventOfClient(new EventOfClient51GiveGameMatchSet<>());
    }

    // 6
    @Override
    public void forgetGameMatchSet() {
        logger.debug("forgetGameMatchSet()");
        transportOfClient.sendEventOfClient(new EventOfClient50ForgetGameMatchSet<>());
    }

    @Override
    public void gameMatchSelect(InstanceIdOfModel model) {
        logger.debug("gameMatchSelect(InstanceIdOfModel model)");
        transportOfClient.sendEventOfClient(new EventOfClient61SetGameMatch<>(model));
    }

    // 7
    @Override
    public void forgetGameMatch() {
        logger.debug("forgetGameMatch()");
        transportOfClient.sendEventOfClient(new EventOfClient60ForgetGameMatch<>());
    }

    @Override
    public void startGameMatchPlaying(Map<String, Integer> mapOfParamsOfModelValue) {
        logger.debug("startPlaying()");
        transportOfClient.sendEventOfClient(new EventOfClient71StartGameMatchPlaying<>(mapOfParamsOfModelValue));
    }

    // 8
    @Override
    public void stopGameMatchPlaying() {
        logger.debug("stopPlaying()");
        transportOfClient.sendEventOfClient(new EventOfClient70StopGameMatchPlaying<>());
    }

    // interface IModelOfClient
    @Override
    public LocalClientStateAutomaton<IModelOfClient> getLocalClientState() {
        return localClientState;
    }

    // class Object
    @Override
    public String toString() {
        return "localClientState = " + localClientState + ". transportOfClient = " + transportOfClient;
    }
}

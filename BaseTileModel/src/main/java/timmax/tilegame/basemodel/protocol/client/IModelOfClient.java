package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.ObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;

// ToDo: Перечень методов для интерфейсов ObserverOnAbstractEvent и IModelOfClient похож.
//       Может всё свести к одному интерфесу?
public interface IModelOfClient {
    // ---- 2
    void logout();
    void login(String userName, String password);

    // ---- 3
    void forgetGameTypeSet();
    void getGameTypeSet();

    // ---- 4
    void forgetGameType();
    void gameTypeSelect(ModelOfServerDescriptor modelOfServerDescriptor);

    // ---- 5
    void forgetGameMatchSet();
    void getGameMatchSet();

    // ---- 6
    void forgetGameMatch();
    void gameMatchSelect(InstanceIdOfModel model);

    // ---- 7
    void stopGameMatchPlaying();
    void startGameMatchPlaying();

    // ---- X
    void createNewGame();

    ObserverOnAbstractEvent getHashSetOfObserverOnAbstractEvent();
    LocalClientState getLocalClientState();
}

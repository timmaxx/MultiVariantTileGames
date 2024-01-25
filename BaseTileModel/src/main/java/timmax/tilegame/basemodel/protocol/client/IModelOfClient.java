package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.ObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.baseview.View;

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
    void forgetGamePlaySet();
    void getGamePlaySet();

    // ---- 6
    void forgetGamePlay();
    void gamePlaySelect(InstanceIdOfModel model);

    // ---- 7
    void stopGameMatchPlaying();
    void startGameMatchPlaying();

    // ---- X
    void createNewGame();
    void addView(View view);

    ObserverOnAbstractEvent getHashSetOfObserverOnAbstractEvent();
}

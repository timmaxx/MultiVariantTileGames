package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.baseview.View;

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
    // void forgetGamePlaySet();
    void getGamePlaySet();

    // ---- X
    void createNewGame();
    void addView(View view);
}

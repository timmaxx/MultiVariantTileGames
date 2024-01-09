package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.baseview.View;

public interface IModelOfClient {
    void logout();
    void login(String userName, String password);

    void forgetGameTypeSet();
    void getGameTypeSet();

    void forgetGameType();
    void gameTypeSelect(String serverBaseModelClass);

    void createNewGame();
    void addView(View view);
}

package timmax.tilegame.basemodel.protocol;

public interface ObserverOnAbstractEvent {
    // 1
    void updateOnClose();
    void updateOnOpen();

    // 2
    void updateOnLogout();
    void updateOnLogin();

    // 3
    void updateOnForgetGameTypeSet();
    void updateOnGetGameTypeSet();

    // 4
    void updateOnForgetGameType();
    void updateOnSelectGameType();

    // 5
    // void updateOnForgetGamePlaySet();
    void updateOnGetGamePlaySet();

    // X
    void updateOnCreateNewGame();
    void updateOnCloseGame();
    void updateOnAddView();
    void updateOnGameEvent();
}

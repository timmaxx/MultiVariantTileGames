package timmax.tilegame.basemodel.protocol;

public interface ObserverOnAbstractEvent {
    void updateOnClose();
    void updateOnOpen();
    void updateOnLogout();
    void updateOnLogin();
    void updateOnForgetGameTypeSet();
    void updateOnGetGameTypeSet();
    void updateOnForgetGameType();
    void updateOnSelectGameType();
    void updateOnCreateNewGame();
    void updateOnCloseGame();
    void updateOnAddView();
    void updateOnGameEvent();
}

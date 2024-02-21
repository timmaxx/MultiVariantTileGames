package timmax.tilegame.basemodel.protocol;

// ToDo: Перечень методов для интерфейсов ObserverOnAbstractEvent и IModelOfClient похож.
//       Может всё свести к одному интерфесу?
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
    void updateOnForgetGameMatchSet();
    void updateOnGetGameMatchSet();

    // 6
    void updateOnForgetGameMatch();
    void updateOnSelectGameMatch();

    // 7
    void updateOnStartGameMatchPlaying();
    void updateOnStopGameMatchPlaying();
}
